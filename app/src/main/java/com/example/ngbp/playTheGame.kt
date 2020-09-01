package com.example.ngbp

import android.graphics.Color
import android.graphics.Color.WHITE
import android.widget.ImageButton

// Array(10){IntArray(10)}
// Array<*>

fun mainGamePlay(row: Int, col: Int, btn: ImageButton, khb: IntArray, ukhb: IntArray,shipList:Array<ship>) {
    val btnIndex = 10 * row + col // 1 d array
    val khbElement = khb.get(btnIndex)
    var dummy = 0
    // android.graphics.Color.WHITE
    if (khbElement != -1) { // already selected
        return
    }
    if (ukhb.get(btnIndex) == 0) {
        // water
        btn.setBackgroundColor(android.graphics.Color.BLUE)
        btn.isClickable = false // can't reclick a square
        khb.set(btnIndex, 0) // water
        dummy = 0
    } else {
    }
    val pdf = makePdf(khb,shipList)
}
