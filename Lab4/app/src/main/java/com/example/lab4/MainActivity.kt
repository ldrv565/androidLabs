package com.example.lab4


import android.content.ContentValues
import android.content.Intent
import android.database.Cursor
import android.database.DatabaseUtils
import android.database.sqlite.SQLiteDatabase
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.io.OutputStreamWriter
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private val list = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val db =  baseContext.openOrCreateDatabase("app.db", MODE_PRIVATE, null)
        db.execSQL("DELETE FROM Cars;")
        db.execSQL("CREATE TABLE IF NOT EXISTS Cars (id INTEGER PRIMARY KEY ASC,type TEXT,manufacturer TEXT,model TEXT,trunkSize INTEGER,ABS TEXT,saftyPillows INTEGER,fuelConsumption INTEGER)")

        val query = db.rawQuery("SELECT * FROM Cars;", )
        if(query.count == 0) {
            initBusTable(db)
        }
        query.close()
        db.close()
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("db", "Destroying")

        val db =  baseContext.openOrCreateDatabase("app.db", MODE_PRIVATE, null)
        db.execSQL("DELETE FROM Cars;")
        db.close()
    }

    fun onListButtonClick(view : View) {
        val db =  baseContext.openOrCreateDatabase("app.db", MODE_PRIVATE, null)
        val query = db.rawQuery("SELECT * FROM Cars;", null)

        formBusList(query, "list")
        showList()
        query.close()
        db.close()
    }

    fun onSortByButtonClick(view: View) {
        val db =  baseContext.openOrCreateDatabase("app.db", MODE_PRIVATE, null)
        var orderCol = ""
        when (sortby_spinner.selectedItemPosition) {
            0 -> orderCol = "trunkSize"
            1 -> orderCol = "saftyPillows"
            2 -> orderCol = "fuelConsumption"
        }
        val query = db.rawQuery("SELECT * FROM Cars ORDER BY $orderCol ASC;", null)

        formBusList(query, "$orderCol-sorted-list")
        showList()
        query.close()
        db.close()
    }

    fun onGroupByButtonClick(view: View) {
        val db =  baseContext.openOrCreateDatabase("app.db", MODE_PRIVATE, null)
        var groupCol = ""
        when (groupby_spinner.selectedItemPosition) {
            0 -> groupCol = "manufacturer"
            1 -> groupCol = "type"
            2 -> groupCol = "model"
            3 -> groupCol = "ABS"
        }
        val query = db.rawQuery("SELECT $groupCol, COUNT(id) FROM Cars GROUP BY $groupCol;", null)

        list.clear()
        val fos = openFileOutput("$groupCol-list", MODE_PRIVATE)
        val osw = OutputStreamWriter(fos)
        while (query.moveToNext()) {
            Log.e("query", DatabaseUtils.dumpCurrentRowToString(query))
            Log.d("db", "found more")
            val attribute = query.getString(0)
            val count = query.getInt(1)
            if (count > 1) {
                list.add("$count $attribute Cars")
                osw.write("$count $attribute Cars\n")
            } else {
                list.add("$count $attribute Cars")
                osw.write("$count $attribute Cars\n")
            }

        }
        Log.d("db", "No more data")
        osw.flush()
        osw.close()
        showList()
        query.close()
        db.close()
    }

    fun onSumButtonClick(view: View) {
        val db =  baseContext.openOrCreateDatabase("app.db", MODE_PRIVATE, null)
        var sumCol = ""

        when (sum_spinner.selectedItemPosition) {
            0 -> sumCol = "trunkSize"
            1 -> sumCol = "saftyPillows"
            2 -> sumCol = "fuelConsumption"
        }
        val query = db.rawQuery("SELECT SUM($sumCol) FROM Cars;", null)
        query.moveToNext()
        Log.e("query", DatabaseUtils.dumpCurrentRowToString(query))
        list.clear()
        val fos = openFileOutput(sumCol+"sum", MODE_PRIVATE)
        val osw = OutputStreamWriter(fos)
        list.add("Sum of $sumCol is ${query.getInt(0)}")
        osw.write("Sum of $sumCol is ${query.getInt(0)}")
        osw.flush()
        osw.close()
        showList()
        query.close()
        db.close()
    }

    fun onAvgButtonClick(view: View) {
        val db =  baseContext.openOrCreateDatabase("app.db", MODE_PRIVATE, null)
        var groupCol = ""
        when (avg_spinner.selectedItemPosition) {
            0 -> groupCol = "manufacturer"
            1 -> groupCol = "type"
            2 -> groupCol = "model"
            3 -> groupCol = "ABS"
        }
        val query = db.rawQuery("SELECT $groupCol, AVG(trunkSize), AVG(saftyPillows), AVG(fuelConsumption) FROM Cars GROUP BY $groupCol;", null)

        list.clear()
        val fos = openFileOutput(groupCol+"avg", MODE_PRIVATE)
        val osw = OutputStreamWriter(fos)
        while (query.moveToNext()) {
            Log.e("query", DatabaseUtils.dumpCurrentRowToString(query))
            Log.d("db", "found more")
            val attribute = query.getString(0)


            val trunkSize = query.getString(1)
            val saftyPillows = query.getString(2)
            val fuelConsumption = query.getString(3)

            val row = "Average $attribute of Cars is trunkSize :$trunkSize     saftyPillows:$saftyPillows        fuelConsumption:$fuelConsumption"// trunkSize:$trunkSize ABS: saftyPillows:$saftyPillows fuelConsumption:$fuelConsumption"


            list.add(row)
            osw.write( "$row \n")
        }
        Log.d("db", "No more data")
        osw.flush()
        osw.close()
        showList()
        query.close()
        db.close()
    }

    fun onMaxButtonClick(view: View) {
        val db =  baseContext.openOrCreateDatabase("app.db", MODE_PRIVATE, null)
        var maxCol = ""
        when (max_spinner.selectedItemPosition) {
            0 -> maxCol = "trunkSize"
            1 -> maxCol = "saftyPillows"
            2 -> maxCol = "fuelConsumption"
        }
        val query = db.rawQuery("SELECT * FROM Cars WHERE $maxCol = (SELECT MAX($maxCol) FROM Cars);", null)

        formBusList(query, "$maxCol-max")
        showList()
        query.close()
        db.close()
    }

    fun onAboveButtonClick(view: View) {
        if(TextUtils.isEmpty(editAboveText.text)) {
            Toast.makeText(this, "Please, enter number", Toast.LENGTH_SHORT).show()
            return
        }
        val db =  baseContext.openOrCreateDatabase("app.db", MODE_PRIVATE, null)
        var col = ""
        when (above_spinner.selectedItemPosition) {
            0 -> col = "trunkSize"
            1 -> col = "saftyPillows"
            2 -> col = "fuelConsumption"
        }
        val query = db.rawQuery("SELECT * FROM Cars WHERE $col > ${editAboveText.text};", null)

        formBusList(query, "above-$col-list")
        showList()
        query.close()
        db.close()
    }

    fun onBelowAvgButtonClick(view: View) {
        val db =  baseContext.openOrCreateDatabase("app.db", MODE_PRIVATE, null)
        var col = ""
        when (belowAvg_spinner.selectedItemPosition) {
            0 -> col = "trunkSize"
            1 -> col = "saftyPillows"
            2 -> col = "fuelConsumption"
        }
        val query = db.rawQuery("SELECT * FROM Cars WHERE $col < (SELECT AVG($col) FROM Cars);", null)

        formBusList(query, "below-$col-average-list")
        showList()
        query.close()
        db.close()
    }

    fun onLargerButtonClick(view: View) {
        if(TextUtils.isEmpty(editLargerText.text)) {
            Toast.makeText(this, "Please, enter number", Toast.LENGTH_SHORT).show()
            return
        }
        val db =  baseContext.openOrCreateDatabase("app.db", MODE_PRIVATE, null)
        var col = ""
        when (larger_spinner.selectedItemPosition) {
            0 -> col = "trunkSize"
            1 -> col = "saftyPillows"
            2 -> col = "fuelConsumption"
        }
        val query = db.rawQuery("SELECT $col FROM Cars WHERE $col > ${editLargerText.text};", null)

        list.clear()
        val fos = openFileOutput("$col-larger", MODE_PRIVATE)
        val osw = OutputStreamWriter(fos)
        while (query.moveToNext()) {
            Log.e("query", DatabaseUtils.dumpCurrentRowToString(query))
            Log.d("db", "found more")
            list.add("${query.getInt(0)}")
            osw.write("${query.getInt(0)}\n")
        }
        Log.d("db", "No more data")

        if (list.isEmpty()) {
            list.add("No data")
            osw.write("No data\n")
        }
        osw.flush()
        osw.close()
        showList()
        query.close()
        db.close()
    }

    private fun initBusTable (db: SQLiteDatabase) {
        val manufacturer =  arrayOf("LiAZ", "PAZ", "Ikarus", "TrolZa", "Scania", "MAZ", "Bogdan")
        val type = arrayOf("Microcar", "subcompact car", "midi size car", "full size car", "luctury car", "Roadster")
        val model = arrayOf("Range Rover", "Nissan Pathfinder", " Subaru Outback", "Toyota Corolla", "Renault Logan", "Volkswagen Polo ")
        val abss = arrayOf("Avalible", "Unavalible")
        Random.nextBoolean()
        for (i in 1..15) {
            addCars(db,manufacturer[Random.nextInt(6)],type[Random.nextInt(6)],model[Random.nextInt(6)],Random.nextInt(0, 6),abss[Random.nextInt(2)], Random.nextInt(0, 6), Random.nextInt(20, 30)*10)

            //manufacturers[Random.nextInt(7)],  colors[Random.nextInt(6)], Random.nextInt(8, 15)*10, Random.nextInt(36, 64)*5, Random.nextInt(20, 30)*10)
        }
    }

    private fun formBusList(query: Cursor, fileName: String) {
        list.clear()
        val fos = openFileOutput(fileName, MODE_PRIVATE)
        val osw = OutputStreamWriter(fos)
        while (query.moveToNext()) {
            Log.d("query", DatabaseUtils.dumpCurrentRowToString(query))
            Log.d("db", "found more")
            //db.execSQL("CREATE TABLE IF NOT EXISTS Cars (id INTEGER PRIMARY KEY ASC,type TEXT,manufacturer TEXT,model TEXT,trunkSize INTEGER,ABS TEXT,saftyPillows INTEGER,fuelConsumption INTEGER)")

            val type = query.getString(1)
            val manufacturer = query.getString(2)
            val model = query.getString(3)
            val ABS= query.getString(5)
            val trunkSize= query.getInt(4)
            val saftyPillows= query.getInt(6)
            val fuelConsumption=query.getInt(7)

            val row = " type :$type manufacturer:$manufacturer model:$model trunkSize:$trunkSize ABS:$ABS saftyPillows:$saftyPillows fuelConsumption:$fuelConsumption"
            list.add(row)
            osw.write(row+'\n')
        }

        if (list.isEmpty()) {
            list.add("No data")
            osw.write("No data")
        }
        Log.d("db", "No more data")

        osw.flush()
        osw.close()
    }

    private fun showList() {
        val intent = Intent(this@MainActivity, ListActivity::class.java)

        intent.putExtra("list", list)
        startActivity(intent)
    }

    private fun addCars(db: SQLiteDatabase, manufacturer: String,type: String,model:String,trunkSize: Int,ABS:  String,saftyPillows:Int,fuelConsumption:Int) {
        Log.d("db", "adding data")

        val values = ContentValues()
        values.put("manufacturer",manufacturer)
        values.put("model",model)
        values.put("type",type)
        values.put("trunkSize",trunkSize)
        values.put("ABS",ABS)
        values.put("saftyPillows",saftyPillows)
        values.put("fuelConsumption",fuelConsumption)


        val id = db.insert("Cars", null, values)
        if (id < 0) {
            Log.d("db", "can't add data")
        }
    }
}

