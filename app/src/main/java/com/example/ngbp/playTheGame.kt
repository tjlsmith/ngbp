package com.example.ngbp

import android.graphics.Color
import android.graphics.Color.RED
import android.graphics.Color.WHITE
import android.widget.GridLayout
import androidx.gridlayout.widget.GridLayout as GridLayout2
import android.widget.ImageButton
import androidx.core.view.get
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.NGBPPoints
import kotlinx.android.synthetic.main.activity_main.view.HumanGrid
import java.time.LocalDateTime


// Array(10){IntArray(10)}
// Array<*>

fun mainGamePlay(
    row: Int, // human hit row
    col: Int, // human hit column
    cBtn: ImageButton,
    //khb: IntArray,
    ukhb: IntArray,
    secretNgbpBoard: IntArray,
    hsl: Array<ship>, // hsl is human ship list
    csl: Array<ship>,
    hG: GridLayout2
    //v: Button?
    //shipList: Array<ship>
): Result {
    // human move shelling ngbp board
    val btnIndex = 10 * row + col // 1 d array
    val ngboElement = knownNgbpBoard.get(btnIndex)
    var dummy = 0
    var wasAHit = false
    // android.graphics.Color.WHITE
    /*
    if (ngboElement != CLOUD) { // already selected
        return Result(-1, false)
    }
     */
    if (secretNgbpBoard.get(btnIndex) == WATER) {
        // water
        cBtn.setBackgroundColor(android.graphics.Color.GREEN) // sets ngbp board water here!
        cBtn.tooltipText = "Water"
        cBtn.isClickable = false // can't reclick a square
        knownNgbpBoard.set(btnIndex, WATER) // cant reclick
        //secretNgbpBoard.set(btnIndex, 0) // water
        dummy = 0
    } else {
        // hit!
        wasAHit = true
        cBtn.setBackgroundColor(android.graphics.Color.CYAN) // set ngbp board fire here!
        cBtn.tooltipText = "Fire"
        cBtn.isClickable = false // can't reclick a square
        knownNgbpBoard.set(btnIndex, FIRE) // cant reclick
    }

    // set ngbp sunk here
    for (ship in ngbpShipList) {
        if (!ship.floating) {
            // if here, ship actually sunk
            for (square in ship.location) {
                if (square < 0) {
                    var hBtnSunk =
                        hG.get(-square) as ImageButton // set human board element colour based on result
                    hBtnSunk.setBackgroundColor(android.graphics.Color.BLACK) // sets square sunk here!
                }
            }
        }
    }

    // build pdf for computer move selection
    var pdf = IntArray(100)
    //var (killRow, killCol) = findFire(khb) // is there anything on fire on the human board?
    var (killRow, killCol) = findFire() // is there anything on fire on the human board?
    killmode = true
    if (killRow == -1 || killCol == -1) {
        killmode = false
    }
    if (killmode) { // check if COMPUTER made a hit!
        //pdf = makePdfKill(khb, hsl, killRow, killCol) // kill not hunt
        pdf = makePdfKill(hsl, killRow, killCol) // kill not hunt
    } else {
        //pdf = makePdfHunt(khb, hsl) // hunt not kill
        pdf = makePdfHunt(hsl) // hunt not kill
    }
    // select the move from the pdf
    val move = selectMove(pdf)
    val r = Result(move, wasAHit)
    return r
}
