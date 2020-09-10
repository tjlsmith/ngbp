package com.example.ngbp

import android.graphics.Color
import android.graphics.Color.RED
import android.graphics.Color.WHITE
import android.widget.ImageButton

// Array(10){IntArray(10)}
// Array<*>

fun mainGamePlay(
    row: Int,
    col: Int,
    btn: ImageButton,
    khb: IntArray,
    ukhb: IntArray,
    ngbpBoard: IntArray,
    //shipList: Array<ship>
) {
    // human move shelling ngbp board
    val btnIndex = 10 * row + col // 1 d array
    val ngboElement = ngbspStateBoard.get(btnIndex)
    var dummy = 0
    // android.graphics.Color.WHITE
    if (ngboElement != CLOUD) { // already selected
        return
    }
    if (ngbpBoard.get(btnIndex) == WATER) {
        // water
        btn.setBackgroundColor(android.graphics.Color.BLUE)
        btn.isClickable = false // can't reclick a square
        ngbspStateBoard.set(btnIndex, WATER) // cant reclick
        //ngbpBoard.set(btnIndex, 0) // water
        dummy = 0
    } else {
        // hit!
        btn.setBackgroundColor(RED)
        btn.isClickable = false // can't reclick a square
        ngbspStateBoard.set(btnIndex, FIRE) // cant reclick
    }
    // build pdf for computer move selection
    val pdf = makePdf(khb, shipList)
    // select the move from the pdf
    val move = selectMove(pdf)
    // play the move

}
