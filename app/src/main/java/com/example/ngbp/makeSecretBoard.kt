package com.example.ngbp

fun makeSecretBoard(shipList: Array<ship>): IntArray {
    // return ngbp's board with all ships positioned
    var secretNgbpBoard = IntArray(100) { CLOUD } // must be to make check work
    val vectors = intArrayOf(-1, -1, -1, 0, -1, 1, 0, -1, 0, 1, 1, -1, 1, 0, 1, 1)
    var list = mutableListOf<Int>()
    for (ship in shipList) { // do this ship
        val shipLen = ship.length
        while (true) { // loop until done
            val row = (0..9).random()
            val col = (0..9).random()
            var placed = false
            if (secretNgbpBoard[row * 10 + col] == CLOUD) { // only proceed if this square is empty
                var vdi = intArrayOf(0, 1, 2, 3, 4, 5, 6, 7)//.shuffle()
                vdi.shuffle() // must shuffle here not inline
                for (vd in 0..7) { // vector direction - note that this biases the ship distribution - must make random
                    val vdRow = vectors[2 * vdi[vd]]
                    val vdCol = vectors[2 * vdi[vd] + 1]
                    var (good, placeList) = check(shipLen, row, col, vdRow, vdCol, secretNgbpBoard, 0)
                    if (good) {
                        placed = true
                        for (i in 0..(placeList.size / 2) - 1) {
                            val index = placeList[2 * i] * 10 + placeList[2 * i + 1]
                            if (secretNgbpBoard[index] != CLOUD) {
                                var dummy = 1
                            }
                            secretNgbpBoard[index] = shipLen
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
        if (secretNgbpBoard[ind] == CLOUD) {
            secretNgbpBoard[ind] = WATER
        }
    }
    return secretNgbpBoard
}

