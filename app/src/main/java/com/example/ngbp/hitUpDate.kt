package com.example.ngbp

import android.widget.TextView

data class newScore(
    var Score: TextView,
    var shipList: Array<ship>,
    var shipSunk: String
)

fun hitUpDate(Score: TextView, move: Int, board: IntArray, ShipList: Array<ship>): newScore {
    var cScore = Score.text.toString().toInt() // get score
    cScore -= 1 // loses a point in the hit // change score
    Score.setText(cScore.toString()) // set score
    val mapp = intArrayOf(0, 0, 4, 3, 2, 1, 0)
    val shipN = mapp[board[move]] // prepare to change squares left for this ship
    for ((i, el) in ShipList[shipN].location.withIndex()) {
        if (el == move) {
            ShipList[shipN].location[i] =
                -ShipList[shipN].location[i] // found - set to negative itself - need the squares for setting to SUNK after all done note this does not work for zero fix it!
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
    var shipSunk = ""
    if (count == 0) {
        ShipList[shipN].floating = false // sunk if here!
        for (el in ShipList[shipN].location) {
            board[-el] = SUNK
        }
        killmode = false // back to hunt mode
        shipSunk = computerShipList[shipN].name
        //hSunkAnnouncer.setText("You sunk my " + computerShipList[shipN].name + "!")
        //hSunkAnnouncer.visibility(VISIBLE)
    }
    val rT = newScore(Score, ShipList, shipSunk) // return modified datums!
    return rT
}