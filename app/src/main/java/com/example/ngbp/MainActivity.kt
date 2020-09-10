package com.example.ngbp

import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

data class ship(val name: String, val length: Int)
data class initVals(
    var ngbpBoard: IntArray,
    val shipList: Array<*>
)

const val WATER = 0
const val CLOUD = -1
const val FIRE = 2

var shipList = arrayOf<ship>(
    ship("Aircraft Carrier", 6),
    ship("BattleShip", 5),
    ship("Destroyer", 4),
    ship("Cruiser", 3),
    ship("Submarine", 2)
)
var ngbspStateBoard = IntArray(100) { CLOUD } // element set to one when its button is clicked

class MainActivity : AppCompatActivity() {
    // Do not initialize yet
    // https://stackoverflow.com/questions/63760283/how-do-i-pass-an-array-up-to-a-higher-scope/63765137#63765137
    private lateinit var ngbpBoard: IntArray
    //private lateinit var shipList: Array<ship>

    var unKnownHumanBoard = IntArray(100) { WATER }
    var knownHumanBoard = IntArray(100) { CLOUD }

    //(ngbpBoard, shipList) = com.example.ngbp.initTheGame()
    //lateinit var ngbpBoard = IntArray(100) { WATER }
    var humanPoint = 20
    var ngbpPoint = 20

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val (ngbpBoard, shipList) = initTheGame()
        //val ngbpBoard = initTheGame()
        // Initialize this variable
        this.ngbpBoard = ngbpBoard
        // this.shipList = shipList
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
        // mainGamePlay(row, col, imgBtn, knownHumanBoard, unKnownHumanBoard, ngbpBoard, shipList)
        mainGamePlay(row, col, imgBtn, knownHumanBoard, unKnownHumanBoard, ngbpBoard)
        val dummy = 1
        //val col = imgBtn.name
        //val row = imgBtn.NGBP.rowCount
    }

}