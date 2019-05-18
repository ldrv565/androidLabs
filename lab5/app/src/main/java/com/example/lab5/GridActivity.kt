package com.example.lab5

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.GridView
import android.widget.TextView
import kotlinx.android.synthetic.main.grid_item.view.*

class GridActivity : AppCompatActivity() {

    var data = arrayOf("a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_grid)

        val grid = findViewById<GridView>(R.id.grid)



        val items = arrayListOf("askdj", "asdasdsadas", "casflkm", "asdasdsadas", "casflkm", "asdasdsadas", "casflkm", "asdasdsadas", "casflkm")

        val adapter = GridAdapter(this, items,
            getColor(intent.getStringExtra("setBackgroundColor")),
            getColor(intent.getStringExtra("setTextColor")),
            0)

        grid.adapter = adapter



//        text1.setBackgroundColor(getColor(intent.getStringExtra("setBackgroundColor")))
//        text1.setTextColor(getColor(intent.getStringExtra("setTextColor")))

    }

    class GridAdapter(context: Context, list: ArrayList<String>, textColor: Int, bgColor: Int, changePosition: Int) : BaseAdapter() {
        private val context = context
        private var list = list
        private var textColor = textColor
        private var bgColor = bgColor
        private var changePosition = changePosition

        init {
            this.list = list
        }

        override fun getCount(): Int {
            return list.size
        }

        override fun getItem(position: Int): Any {
            return list[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val item = this.list[position]

            val inflator = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val grid_item = inflator.inflate(R.layout.grid_item, null)
            grid_item.name.text = item!!

            if(position == changePosition) {
                grid_item.name.setBackgroundColor(bgColor)
                grid_item.name.setTextColor(textColor)
            }

            return grid_item
        }
    }


    private fun getColor(colorName:String): Int {

        var red = 0
        var green = 0
        var blue = 0

        if (colorName == "red") {
            red = 255
        }
        if (colorName == "green") {
            green = 255
        }
        if (colorName == "blue") {
            blue = 255
        }

        val fill_color = Color.argb(
            255, red, green, blue
        )

        return fill_color
    }
}