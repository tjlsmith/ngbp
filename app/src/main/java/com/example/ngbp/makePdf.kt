package com.example.ngbp

fun makePdfHunt(khb: IntArray, shipList: Array<ship>): IntArray {
    // return pdf of possible ships on human board
    // no offset needed here
    var pdfBoard = IntArray(100) { WATER } // init to zero
    val vectors = intArrayOf(-1, -1, -1, 0, -1, 1, 0, -1, 0, 1, 1, -1, 1, 0, 1, 1)
    var list = mutableListOf<Int>()
    for (ship in shipList) { // do this ship
        if (ship.floating) {
            val shipLen = ship.length
            for (row in 0..9) {
                for (col in 0..9) { // do this square
                    if (khb[row * 10 + col] == CLOUD) { // only proceed if this might have a ship on it
                        for (vd in 0..7) { // vector direction
                            //var listt = mutableListOf<Int>() // empty list of new squares
                            val vdRow = vectors[2 * vd]
                            val vdCol = vectors[2 * vd + 1]
                            //var good = true
                            var (good, listtt) = check(shipLen, row, col, vdRow, vdCol, khb, 0)
                            if (good) {
                                for (i in 0..(listtt.size / 2) - 1) {
                                    val index = listtt[2 * i] * 10 + listtt[2 * i + 1]
                                    pdfBoard[index] += shipLen
                                }
                            }
                        }
                    }
                }
            } // row
        }
    }
    return pdfBoard
}

fun makePdfKill(khb: IntArray, shipList: Array<ship>, row: Int, col: Int): IntArray {
    // return pdf of possible ships on human board on this one spot row col
    // offset needed here
    var pdfBoard = IntArray(100) { WATER } // init to zero
    val vectors = intArrayOf(-1, -1, -1, 0, -1, 1, 0, -1, 0, 1, 1, -1, 1, 0, 1, 1)
    var list = mutableListOf<Int>()
    var dummy = 0 // place holder for breakpoints
    for (ship in shipList) { // do this ship
        if (ship.floating) {
            val shipLen = ship.length
            for (vd in 0..7) { // vector direction
                //var listt = mutableListOf<Int>() // empty list of new squares
                for (offset in 0..shipLen) {
                    val vdRow = vectors[2 * vd]
                    val vdCol = vectors[2 * vd + 1]
                    // var good = true
                    var (good, listtt) = check(shipLen, row, col, vdRow, vdCol, khb, offset)
                    if (good) {
                        var fireCount = 0
                        for (i in 0..(listtt.size / 2) - 1) {
                            val index = listtt[2 * i] * 10 + listtt[2 * i + 1]
                            if (khb[index] == FIRE) {
                                fireCount++
                            }
                        }
                        if (fireCount > 1) {
                            dummy = 1
                        }
                        for (i in 0..(listtt.size / 2) - 1) {
                            val index = listtt[2 * i] * 10 + listtt[2 * i + 1]
                            pdfBoard[index] += fireCount
                        }
                    }
                }
            }
        }
    }
    // zero actual hit point so it doesn't try and hit it again
    pdfBoard[row * 10 + col] = 0
    for ((i, el) in khb.withIndex()) {
        if (el != CLOUD) {
            dummy = pdfBoard[i]
            pdfBoard[i] = 0
        }
    }
    return pdfBoard
}

fun check(
    shipLen: Int,
    row: Int,
    col: Int,
    vdRow: Int,
    vdCol: Int,
    //list: MutableList<Int>,
    board: IntArray,
    offSet: Int
): Pair<Boolean, MutableList<Int>> {
    var good = true
    var listt = mutableListOf<Int>() // empty list of new squares
    //for (ofst in 0..offSet) {
    for (l in 0..shipLen - 1) {
        val newRow = row + (l - offSet) * vdRow
        val newCol = col + (l - offSet) * vdCol
        val newIndex = newRow * 10 + newCol
        if (newCol < 0 || newRow < 0 || newCol > 9 || newRow > 9) {
            good = false // must be in bounds
            break
        } else {
            // ok inbounds check contents of square
            if (board[newIndex] != CLOUD) {
                if (killmode && board[newIndex] == FIRE) {
                    // exception for killmode
                    val dummy = 0
                } else {
                    good = false // must be unknown unless killmode in which case fire is ok
                    break
                }
            }
            //      }
        }

        listt.add(newRow)
        listt.add(newCol)
    }
    return Pair(good, listt)
}