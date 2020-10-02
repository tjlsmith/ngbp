package com.example.ngbp

import android.bluetooth.BluetoothSocket
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.solver.widgets.ConstraintWidget.VISIBLE
import androidx.core.view.get
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
import java.sql.Time
import java.time.Instant
import java.time.Instant.now
import java.time.LocalDateTime
import java.time.LocalDateTime.now
import java.time.LocalTime.now
import java.util.*
import kotlin.concurrent.schedule
import kotlin.math.pow

// import kotlinx.android.synthetic.main.layout.view.*

data class ship(
    val name: String,
    val length: Int,
    var floating: Boolean,
    var location: IntArray
)

data class initVals(
    var secretNgbpBoard: IntArray,
    val shipList: Array<*>
)

data class rowColTransfer(
    var row: Int,
    var col: Int
)

data class Result(
    var move: Int,
    val hit: Boolean
)

const val VERSION = 0.033
const val CLOUD = -1
const val CLEAR = 0
const val WATER = 1

// ships take 2-6
const val FIRE = 7
const val SUNK = 8
const val UNUSED = 100

var humanShipList = arrayOf<ship>(
    ship("Aircraft Carrier", 6, true, IntArray(6)),
    ship("BattleShip", 5, true, IntArray(6)),
    ship("Destroyer", 4, true, IntArray(6)),
    ship("Cruiser", 3, true, IntArray(6)),
    ship("Submarine", 2, true, IntArray(6))
)

var ngbpShipList = arrayOf<ship>(
    ship("Aircraft Carrier", 6, true, IntArray(6)),
    ship("BattleShip", 5, true, IntArray(6)),
    ship("Destroyer", 4, true, IntArray(6)),
    ship("Cruiser", 3, true, IntArray(6)),
    ship("Submarine", 2, true, IntArray(6))
)
var knownNgbpBoard = IntArray(100) { CLOUD } // element set to one when its button is clicked
var knownHumanBoard = IntArray(100) { CLOUD } // known to the computer human ship layout

var humanCloudMap = IntArray(100) { CLOUD } // human cloud map
var ngbpCloudMap = IntArray(100) { CLOUD } // nsbp cloud map

var killmode = false
var killRow = 0
var killCol = 0

class MainActivity : AppCompatActivity() {
    // Do not initialize yet
    // https://stackoverflow.com/questions/63760283/how-do-i-pass-an-array-up-to-a-higher-scope/63765137#63765137
    // C:\Users\Terry\Pictures\Screenshots\Screenshot (528) passing arrays in Kotlin stackoverflow.png
    private lateinit var secretNgbpBoard: IntArray
    private lateinit var secretHumanBoard: IntArray // the actual state of the human board
    //private lateinit var shipList: Array<ship>

    //var secretHumanBoard = IntArray(100) { WATER } // actual human ship layout
    //var knownHumanBoard = IntArray(100) { CLOUD } // known to the computer human ship layout

    //(secretNgbpBoard, shipList) = com.example.ngbp.initTheGame()
    //lateinit var secretNgbpBoard = IntArray(100) { WATER }
    var humanPoint = 20
    var ngbpPoint = 20

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // get rankings
        val sp = getSharedPreferences("RATINGS", Context.MODE_PRIVATE)
        val hELO = sp.getInt("hELO", 1500)
        val ngELO = sp.getInt("ngELO", 1500)
        var editor = sp.edit()
        val hExpected = 1.0 / (1.0 + 10.0.pow((ngELO - hELO) / 400.0))
        val ngExpected = 1.0 - hExpected
        editor.putInt("hELO", hELO)
        editor.putInt("ngELO", ngELO)
        editor.commit()
        hELOText.setText(hELO.toString())
        ngELOText.setText(ngELO.toString())

        //val (secretNgbpBoard, shipList) = initTheGame()
        val secretNgbpBoard = initTheGame()
        ngbpShipList = makeShipList(secretNgbpBoard, ngbpShipList)
        val banner = gameBanner
        banner.text = "Nuts Good BattleShip Program\nV." + VERSION
        // Initialize this variable
        this.secretNgbpBoard = secretNgbpBoard
        this.secretHumanBoard = makeSecretBoard(humanShipList) // initialize human board
        var best = rating(this.secretHumanBoard)
        for (i in 0..100) { // try 100 times to make good human ship distribution
            var newB = makeSecretBoard(humanShipList)
            var newRating = rating(newB)
            if (newRating < best) {
                best = newRating
                this.secretHumanBoard = newB
            }
            if (best == 0) {
                break
            }
        }
        humanShipList = makeShipList(secretHumanBoard, humanShipList)
        //drawBoard(true, HumanGrid) // init
        val dummy = 1
        // this.shipList = shipList
    } // big starting routine

    fun kaBoom(v: View?) {
        // process human move
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

        if (ngbpCloudMap[tag] == CLEAR) {
            // already shelled
            return
        }

        ngbpCloudMap[tag] = CLEAR // clear this spot

        val hMove = tag
        val hAnno = hSunkAnnouncer // as TextView
        hAnno.setVisibility(View.GONE)
        val cAnno = cSunkAnnouncer // as TextView
        cAnno.setVisibility(View.GONE)

        // mainGamePlay(row, col, imgBtn, knownHumanBoard, secretHumanBoard, secretNgbpBoard, shipList)
        //mainGamePlay(row, col, imgBtn, knownHumanBoard, secretHumanBoard, secretNgbpBoard,v)

        // human moved hMove - now computer returns
        val (cMove, humanMadeAHit) = mainGamePlay(
            row,
            col,
            imgBtn,
            //knownHumanBoard,
            secretHumanBoard,
            secretNgbpBoard,
            humanShipList,
            ngbpShipList,
            NgbpGrid
        )
        // wait for 1 second
        //Timer().schedule(1000) {
        //}
        //Instant instant = Instant.now()
        //while (true){
        //    Instant.now()-instant
        //}
        //do something
        // play the computer move

        // wait one second
        //val now = LocalDateTime.now().second
        //while (LocalDateTime.now().second == now) {
        //}

        if (humanMadeAHit) { // change computer score if necessary
            var (NGBPScore, ngbpShipList, shipSunk, secretNgbpBoard) = hitUpDate(
                NGBPScore,
                hMove,
                secretNgbpBoard,
                secretNgbpBoard,
                ngbpShipList
            )
            if (shipSunk.length != 0) {
                cAnno.setText("You sunk my " + shipSunk + "!")
                cAnno.setVisibility(View.VISIBLE)
            }
        }
        if (NGBPScore.text.toString().toInt() == 0) {
            val hAnno = hSunkAnnouncer // as TextView
            hAnno.setText("Human Wins!")
            hAnno.setVisibility(View.VISIBLE)
        }

        // check here if computer made a hit
        if (cMove >= 0) {
            var hBtn =
                HumanGrid.get(cMove) as ImageButton // set human board element colour based on result
            if (secretHumanBoard[cMove] == WATER) {
                //hBtn.setBackgroundColor(android.graphics.Color.YELLOW) // sets human board water here!
                hBtn.setBackgroundColor(android.graphics.Color.BLUE) // sets human board water here!
                hBtn.tooltipText = "Water"
                knownHumanBoard[cMove] = WATER
            } else {
                // computer hit a ship!
                //killmode = true
                killRow = 0
                killCol = 0
                if (cMove < 10) {
                    killCol = cMove
                } else {
                    killRow = cMove / 10
                    killCol = cMove % 10
                }
                //hBtn.setBackgroundColor(android.graphics.Color.MAGENTA) // sets ngbp board fire here!
                knownHumanBoard[cMove] = FIRE
                hBtn.setBackgroundColor(android.graphics.Color.RED) // sets ngbp board fire here!
                hBtn.tooltipText = "Fire"

                var (HumanScore, HumanShipList, shipSunk, knownHumanBoard) = hitUpDate(
                    HumanScore, cMove, secretHumanBoard, knownHumanBoard,
                    humanShipList
                )
                if (HumanScore.text.toString().toInt() == 0) {
                    // val cAnno = cSunkAnnouncer // as TextView
                    cAnno.setText("NGBP Wins!")
                    cAnno.setVisibility(View.VISIBLE)
                } else {
                    if (shipSunk.length != 0) {
                        // val hAnno = hSunkAnnouncer // as TextView
                        hAnno.setText("You sunk my " + shipSunk + "!")
                        hAnno.setVisibility(View.VISIBLE)
                    }
                }
                //var hScore = HumanScore.text.toString().toInt()
                //hScore -= 1 // loses a point in the hit
                //HumanScore.setText(hScore.toString())
                knownHumanBoard[cMove] = FIRE
            }
            for (square in 0..99) {
                if (secretHumanBoard[square] > WATER) {
                    var hBtnSunk =
                        HumanGrid.get(square) as android.widget.ImageButton // set human board element colour based on result
                    hBtnSunk.setBackgroundColor(android.graphics.Color.LTGRAY) // sets square sunk here!
                    if (knownHumanBoard[square] == FIRE) {
                        var hBtnSunk =
                            HumanGrid.get(square) as android.widget.ImageButton // set human board element colour based on result
                        hBtnSunk.setBackgroundColor(android.graphics.Color.RED) // sets square sunk here!
                    }
                }
            }
            // set ngbp sunk here
            for (ship in humanShipList) {
                if (!ship.floating) {
                    // if here, ship actually sunk!
                    for (square in ship.location) {
                        if (square < 0) {
                            knownHumanBoard[-square] = SUNK
                            var hBtnSunk =
                                HumanGrid.get(-square) as ImageButton // set human board element colour based on result
                            hBtnSunk.setBackgroundColor(android.graphics.Color.BLACK) // sets square sunk here!
                        }
                    }
                }
            }
            //v.invalidate()
            // val hImgBtn = ("imageButtonH"+Integer.toString(move)) as ImageButton
            // dummy = 0
            val dummy = 1
            //val col = imgBtn.name
            //val row = imgBtn.NGBP.rowCount
        } // if cMove >= 0
        //drawBoard(true, HumanGrid)
        //drawBoard(false, NgbpGrid)
        //HumanGrid.postInvalidate()
        //v.postInvalidate()
    } //kaboom
}