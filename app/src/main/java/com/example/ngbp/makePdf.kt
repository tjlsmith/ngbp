package com.example.ngbp

fun makePdf(khb: IntArray, shipList: Array<ship>): IntArray {
    // return pdf of possible ships on human board
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
                            var (good, listtt) = check(shipLen, row, col, vdRow, vdCol, khb)
                            if (good) {
                                for (i in 0..(listtt.size / 2) - 1) {
                                    val index = listtt[2 * i] * 10 + listtt[2 * i + 1]
                                    pdfBoard[index]++
                                }
                            }
                        }
                    }
                }
            }
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
    board: IntArray
): Pair<Boolean, MutableList<Int>> {
    var good = true
    var listt = mutableListOf<Int>() // empty list of new squares
    for (l in 0..shipLen - 1) {
        val newRow = row + l * vdRow
        val newCol = col + l * vdCol
        val newIndex = newRow * 10 + newCol
        if (newCol < 0 || newRow < 0 || newCol > 9 || newRow > 9 || (board[newIndex] != CLOUD)) {
            good = false // must be in bounds and unknown
            break
        }
        listt.add(newRow)
        listt.add(newCol)
    }
    return Pair(good, listt)
}