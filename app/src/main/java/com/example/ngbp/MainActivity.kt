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


// return available enemy ship list
var shipList = arrayOf<ship>(
    ship("Aircraft Carrier", 6),
    ship("BattleShip", 5),
    ship("Destroyer", 4),
    ship("Cruiser", 3),
    ship("Submarine", 2)
)

class MainActivity : AppCompatActivity() {

    var unKnownHumanBoard = IntArray(100) { WATER }
    var knownHumanBoard = IntArray(100) { CLOUD }
    var ngbpBoard = IntArray(100) { WATER }
    var humanPoint = 20
    var ngbpPoint = 20

    override fun onCreate(savedInstanceState: Bundle?) {

        var (ngbpBoard, shipList) = initTheGame()
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
        mainGamePlay(row, col, imgBtn, knownHumanBoard, unKnownHumanBoard, ngbpBoard, shipList)
        val dummy = 1
        //val col = imgBtn.name
        //val row = imgBtn.NGBP.rowCount
    }

}