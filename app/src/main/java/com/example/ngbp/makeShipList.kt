package com.example.ngbp

import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*

fun makeShipList(b: IntArray, shipList: Array<ship>): Array<ship> {
    var pointer = IntArray(7) // start all pointer at zero
    val mapp = intArrayOf(0, 0, 4, 3, 2, 1, 0)
    for (i in 0..4) { // fill with unused first
        for (j in 0..5) {
            shipList[i].location[j] = UNUSED
        }
    }
    for ((i, el) in b.withIndex()) { // go through board b and for each ship square put loc in right position in shiplist
        if (el > 1) {
            // fill with UNUSED
            shipList[mapp[el]].location[pointer[el]] = i
            pointer[el]++
        }
    }
    return shipList
}

