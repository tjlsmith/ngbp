package com.example.ngbp

fun makePdf(khb: IntArray, shipList: Array<ship>) {
// return pdf of possible ships on human board
    var pdfBoard = IntArray(100) { 0 }
    val vectors = intArrayOf(-1, -1, -1, 0, -1, 1, 0, -1, 0, 1, 1, -1, 1, 0, 1, 1)
    var list = mutableListOf<Int>()
    for (ship in shipList) { // do this ship
        val shipLen = ship.length
        for (row in 0..9) {
            for (col in 0..9) { // do this square
                if (khb[row * 10 + col] == -1) { // only proceed if this might have a ship on it
                    for (vd in 0..7) { // vector direction
                        var list = mutableListOf<Int>() // empty list of new squares
                        val vdRow = vectors[2 * vd]
                        val vdCol = vectors[2 * vd + 1]
                        var good = true
                        for (l in 0..shipLen) {
                            val newRow = row + l * vdRow
                            val newCol = col + l * vdCol
                            val newIndex = newRow * 10 + newCol
                            if (newCol < 0 || newRow < 0 || newCol > 9 || newRow > 9 || (khb[newIndex] != -1)) {
                                good = false // must be in bounds and unknown
                                break
                            }
                            list.add(newRow)
                            list.add(newCol)
                        }
                        if (good) {
                            var dummy = 1
                            for (i in 0..(list.size / 2)-1) {
                                val index = list[2 * i] * 10 + list[2 * i + 1]
                                pdfBoard[index]++
                            }
                        }
                    }
                }
            }
        }
    }

}