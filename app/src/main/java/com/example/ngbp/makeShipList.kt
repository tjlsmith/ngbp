package com.example.ngbp

import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*

fun makeShipList(b: IntArray, shipList: Array<ship>): Array<ship> {
    var pointer = IntArray(7) // start all pointer at zero
    val mapp = intArrayOf(0, 0, 4, 3, 2, 1, 0)
    for ((i, el) in b.withIndex()) {
        if (el > 1) {
            shipList[mapp[el]].location[pointer[el]] = i
            pointer[el]++
        }
    }
    return shipList
}

