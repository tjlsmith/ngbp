package com.example.ngbp

fun makeNGBPBoard(): IntArray {
    // return ngbp's board with all ships positioned
    var ngbpBoard = IntArray(100) { CLOUD } // must be to make check work
    val vectors = intArrayOf(-1, -1, -1, 0, -1, 1, 0, -1, 0, 1, 1, -1, 1, 0, 1, 1)
    var list = mutableListOf<Int>()
    for (ship in shipList) { // do this ship
        val shipLen = ship.length
        while (true) { // loop until done
            val row = (0..9).random()
            val col = (0..9).random()
            var placed = false
            if (ngbpBoard[row * 10 + col] == -1) { // only proceed if this square is empty
                for (vd in 0..7) { // vector direction
                    val vdRow = vectors[2 * vd]
                    val vdCol = vectors[2 * vd + 1]
                    var (good, placeList) = check(shipLen, row, col, vdRow, vdCol, ngbpBoard)
                    if (good) {
                        placed = true
                        for (i in 0..(placeList.size / 2) - 1) {
                            val index = placeList[2 * i] * 10 + placeList[2 * i + 1]
                            if (ngbpBoard[index] != CLOUD) {
                                var dummy = 1
                            }
                            ngbpBoard[index] = shipLen
                        }
                        break
                    }
                }
            }
            if (placed) {
                break
            }
        }
    }
    for (ind in 0..99) {
        if (ngbpBoard[ind] == CLOUD) {
            ngbpBoard[ind] = WATER
        }
    }
    return ngbpBoard
}

