package com.example.lab4

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_list.*

class ListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, intent.extras.getStringArrayList("list"))
        listView.adapter = adapter

    }
}
