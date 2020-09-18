package com.example.ngbp

import android.graphics.Color
import android.graphics.Color.RED
import android.graphics.Color.WHITE
import android.widget.GridLayout
import android.widget.ImageButton
import androidx.core.view.get
import kotlinx.android.synthetic.main.activity_main.view.NGBPPoints
import kotlinx.android.synthetic.main.activity_main.view.HumanGrid
import java.time.LocalDateTime


// Array(10){IntArray(10)}
// Array<*>

fun mainGamePlay(
    row: Int,
    col: Int,
    cBtn: ImageButton,
    khb: IntArray,
    ukhb: IntArray,
    ngbpBoard: IntArray,
    hsl: Array<ship>,
    csl: Array<ship>
    //v: Button?
    //shipList: Array<ship>
): Result {
    // human move shelling ngbp board
    val btnIndex = 10 * row + col // 1 d array
    val ngboElement = ngbspStateBoard.get(btnIndex)
    var dummy = 0
    var wasAHit = false
    // android.graphics.Color.WHITE
    if (ngboElement != CLOUD) { // already selected
        return Result(-1, false)
    }
    if (ngbpBoard.get(btnIndex) == WATER) {
        // water
        cBtn.setBackgroundColor(android.graphics.Color.BLUE)
        cBtn.isClickable = false // can't reclick a square
        ngbspStateBoard.set(btnIndex, WATER) // cant reclick
        //ngbpBoard.set(btnIndex, 0) // water
        dummy = 0
    } else {
        // hit!
        wasAHit = true
        cBtn.setBackgroundColor(RED)
        cBtn.isClickable = false // can't reclick a square
        ngbspStateBoard.set(btnIndex, FIRE) // cant reclick
    }
    // build pdf for computer move selection
    var pdf = IntArray(100)
    var (killRow, killCol) = findFire(khb) // is there anything on fire on the human board?
    killmode = true
    if (killRow == -1 || killCol == -1) {
        killmode = false
    }
    if (killmode) { // check if COMPUTER made a hit!
        pdf = makePdfKill(khb, hsl, killRow, killCol) // kill not hunt
    } else {
        pdf = makePdfHunt(khb, hsl) // hunt not kill
    }
    // select the move from the pdf
    val move = selectMove(pdf)
    val r = Result(move, wasAHit)
    return r
}
