package com.example.ngbp

import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

data class ship(val name: String, val length: Int)
data class initVals(
    val humanPoint: Int,
    val ngbpPoint: Int,
    var ngbpBoard: IntArray,
    var unKnownHumanBoard: IntArray,
    var knownHumanBoard: IntArray,
    val shipList: Array<*>
)

var unKnownHumanBoard = IntArray(100)
var knownHumanBoard = IntArray(100)

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {

        var (humanPoint, ngbpPoint, ngbpBoard, unKnownHumanBoard, knownHumanBoard, shipList) = initTheGame()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    fun kaBoom(v: View?) {
        // var button = findViewById<Button>(R.id.NGBP.)
        val imgBtn = findViewById(v!!.id) as ImageButton
        val tag = Integer.parseInt(v.getTag().toString()) //.toInt()
        var row = -1
        var col = -1
        if (tag < 10) {
            row = 0
            col = tag
        } else {
            row = tag / 10
            col = tag % 10
        }
        mainGamePlay(row, col, imgBtn, knownHumanBoard, unKnownHumanBoard)
        val dummy = 1
        //val col = imgBtn.name
        //val row = imgBtn.NGBP.rowCount
    }

}