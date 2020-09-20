package com.example.ngbp

import android.icu.text.RelativeDateTimeFormatter
import android.widget.TextView

data class newScore(
    var Score: TextView,
    var shipList: Array<ship>,
    var shipSunk: String,
    var knownBoard: IntArray
)

fun hitUpDate(
    Score: TextView,
    move: Int,
    unKnownBoard: IntArray,
    knownBoard: IntArray,
    ShipList: Array<ship>
): newScore {
    var cScore = Score.text.toString().toInt() // get score
    cScore -= 1 // loses a point in the hit // change score
    Score.setText(cScore.toString()) // set score
    if (killmode) {
        Score.setTextColor(android.graphics.Color.RED)
    } else {
        Score.setTextColor(android.graphics.Color.LTGRAY)
    }
    val mapp = intArrayOf(0, 0, 4, 3, 2, 1, 0)
    val shipN = mapp[unKnownBoard[move]] // prepare to change squares left for this ship
    for ((i, el) in ShipList[shipN].location.withIndex()) {
        if (el == move) {
            ShipList[shipN].location[i] =
                -ShipList[shipN].location[i] // found - set to negative itself - need the squares for setting to SUNK after all done note this does not work for zero fix it!
            break
        }
    }
    var count = 0 // count squares left
    for ((i, el) in ShipList[shipN].location.withIndex()) {
        if (el > 0 && el != UNUSED) {
            count++
            break // check for any elements left - if so, not sunk - break
        }
    }
    var shipSunk = ""
    if (count == 0) {
        ShipList[shipN].floating = false // sunk if here!
        for (el in ShipList[shipN].location) {
            if (el != UNUSED) {
                knownBoard[-el] = SUNK
            }
        }
        killmode = false // back to hunt mode
        shipSunk = computerShipList[shipN].name
        //hSunkAnnouncer.setText("You sunk my " + computerShipList[shipN].name + "!")
        //hSunkAnnouncer.visibility(VISIBLE)
    }
    val rT = newScore(Score, ShipList, shipSunk, knownBoard) // return modified datums!
    return rT
}