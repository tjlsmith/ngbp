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

data class newScore(
    var Score: TextView,
    var shipList: Array<ship>
)

fun hitUpDate(Score: TextView, move: Int, board: IntArray, ShipList: Array<ship>): newScore {
    var cScore = Score.text.toString().toInt() // get score
    cScore -= 1 // loses a point in the hit // change score
    Score.setText(cScore.toString()) // set score
    val mapp = intArrayOf(0, 0, 4, 3, 2, 1, 0)
    val shipN = mapp[board[move]] // prepare to change squares left for this ship
    for ((i, el) in ShipList[shipN].location.withIndex()) {
        if (el == move) {
            ShipList[shipN].location[i] = 0 // found - set to zero
            break
        }
    }
    var count = 0 // count squares left
    for ((i, el) in ShipList[shipN].location.withIndex()) {
        if (el > 0) {
            count++
            break // check for any elements left - if so, not sunk - break
        }
    }
    if (count == 0) {
        ShipList[shipN].floating = false // sunk if here!
        killmode = false // back to hunt mode
        //hSunkAnnouncer.setText("You sunk my " + computerShipList[shipN].name + "!")
        //hSunkAnnouncer.visibility(VISIBLE)
    }
    val rT = newScore(Score, ShipList) // return modified datums!
    return rT
}