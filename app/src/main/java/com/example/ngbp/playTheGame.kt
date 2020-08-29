package com.example.ngbp

import android.graphics.Color
import android.widget.ImageButton

// Array(10){IntArray(10)}
// Array<*>

fun mainGamePlay(row: Int, col: Int, btn: ImageButton, vararg ukhb: IntArray) {
    //println("Hello World!")
    //val unKnownHumanBoard = Array(10) { IntArray(10) }
    //unKnownHumanBoard = ukhb
    val rowBoard = ukhb.get(row)
    var dummy = 0
    if (rowBoard.get(col) == 0) {
        // water
        btn.setBackgroundColor(android.graphics.Color.BLUE)
        btn.setClickable(false) // can't reclick a square
        dummy = 0
    }else{}
}
