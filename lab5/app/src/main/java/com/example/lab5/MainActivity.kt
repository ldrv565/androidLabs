package com.example.lab5

import android.annotation.SuppressLint
import android.graphics.Color.argb
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ExpandableListAdapter
import android.widget.ExpandableListView

import java.util.ArrayList
import java.util.HashMap
import android.content.Intent



class MainActivity : AppCompatActivity() {
    internal var expandableListView: ExpandableListView? = null
    internal var adapter: ExpandableListAdapter? = null
    internal var titleList: List<String> ? = null

    val data: HashMap<String, List<String>>
        get() {
            val listData = HashMap<String, List<String>>()

            val cellColor = ArrayList<String>()
            cellColor.add("red")
            cellColor.add("green")
            cellColor.add("blue")

            val textColor = ArrayList<String>()
            textColor.add("red")
            textColor.add("green")
            textColor.add("blue")

            listData["setBackgroundColor"] = cellColor
            listData["setTextColor"] = textColor

            return listData
        }

    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val intent = Intent(this@MainActivity, GridActivity::class.java)

        expandableListView = findViewById(R.id.expListView)
        if (expandableListView != null) {
            val listData = data
            titleList = ArrayList(listData.keys)
            adapter = CustomExpandableListAdapter(this, titleList as ArrayList<String>, listData)
            expandableListView!!.setAdapter(adapter)

            expandableListView!!.setOnChildClickListener { parent, v, groupPosition, childPosition, id ->
                val selectedColor = listData[(titleList as ArrayList<String>)[groupPosition]]!![childPosition]

                val field = (titleList as ArrayList<String>)[groupPosition]
                if (field == "setBackgroundColor") {
                    intent.putExtra("setBackgroundColor", selectedColor)
                } else {
                    intent.putExtra("setTextColor", selectedColor)
                }

                if(intent.extras["setBackgroundColor"] != null && intent.extras["setTextColor"] != null) {
                    startActivity(intent);
                }
                false
            }

        }
    }
}
