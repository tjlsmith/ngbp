package com.example.ngbp

import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // var gridView = findViewById(R.id.gridlayout) as GridView
    }

    fun kaBoom(v: View?) {
        // var button = findViewById<Button>(R.id.NGBP.)
        val imgBtn = findViewById(v!!.id) as ImageButton
        val tag =  Integer.parseInt(v.getTag().toString()) //.toInt()
        var row = -1
        var col = -1
        if (tag < 10) {
           row = 0
            col=tag
        } else {
            row=tag/10
            col=tag % 10
        }
        val dummy = 1
        //val col = imgBtn.name
        //val row = imgBtn.NGBP.rowCount

    }

}