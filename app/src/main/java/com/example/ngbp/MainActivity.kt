package com.example.ngbp

import android.bluetooth.BluetoothSocket
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*

// import kotlinx.android.synthetic.main.layout.view.*

data class ship(val name: String, val length: Int, var floating: Boolean)
data class initVals(
    var ngbpBoard: IntArray,
    val shipList: Array<*>
)

data class Result(
    var move: Int,
    val hit: Boolean
)

const val WATER = 0
const val CLOUD = -1
const val FIRE = 2

var shipList = arrayOf<ship>(
    ship("Aircraft Carrier", 6, true),
    ship("BattleShip", 5, true),
    ship("Destroyer", 4, true),
    ship("Cruiser", 3, true),
    ship("Submarine", 2, true)
)
var ngbspStateBoard = IntArray(100) { CLOUD } // element set to one when its button is clicked

class MainActivity : AppCompatActivity() {
    // Do not initialize yet
    // https://stackoverflow.com/questions/63760283/how-do-i-pass-an-array-up-to-a-higher-scope/63765137#63765137
    // C:\Users\Terry\Pictures\Screenshots\Screenshot (528) passing arrays in Kotlin stackoverflow.png
    private lateinit var ngbpBoard: IntArray
    private lateinit var unKnownHumanBoard: IntArray
    //private lateinit var shipList: Array<ship>

    //var unKnownHumanBoard = IntArray(100) { WATER } // actual human ship layout
    var knownHumanBoard = IntArray(100) { CLOUD } // known to the computer human ship layout

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
        this.unKnownHumanBoard = makeNGBPBoard() // initilize human board
        drawHumanBoard(unKnownHumanBoard, HumanGrid)
        val dummy = 1
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
        //mainGamePlay(row, col, imgBtn, knownHumanBoard, unKnownHumanBoard, ngbpBoard,v)
        val (rowCol, itWasAHit) = mainGamePlay(
            row,
            col,
            imgBtn,
            knownHumanBoard,
            unKnownHumanBoard,
            ngbpBoard
        )
        // play the computer move
        if (itWasAHit) {
            var cScore = NGBPScore.text.toString().toInt()
            cScore -= 1 // loses a point in the hit
            NGBPScore.setText(cScore.toString())
        }
        if (rowCol >= 0) {
            var hBtn =
                HumanGrid.get(rowCol) as ImageButton // set human board element colour based on result
            if (unKnownHumanBoard[rowCol] == WATER) {
                hBtn.setBackgroundColor(android.graphics.Color.BLUE)
                knownHumanBoard[rowCol] = WATER
            } else {
                hBtn.setBackgroundColor(android.graphics.Color.RED) // hit a ship!
                var hScore = HumanScore.text.toString().toInt()
                hScore -= 1 // loses a point in the hit
                HumanScore.setText(hScore.toString())
                knownHumanBoard[rowCol] = FIRE
            }
            // val hImgBtn = ("imageButtonH"+Integer.toString(move)) as ImageButton
            // dummy = 0
            val dummy = 1
            //val col = imgBtn.name
            //val row = imgBtn.NGBP.rowCount
        }
    }

}