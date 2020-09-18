package com.example.ngbp

fun findFire(b: IntArray): rowColTransfer {
    // must find fire with cloud next to it
    var row = -1
    var col = -1
    for (i in 0..9) {
        for (j in 0..9) {
            val index = 10 * i + j
            if (b[index] == FIRE) {
                for (dI in -1..1) {
                    for (dJ in -1..1) {
                        if (dI == 0 && dJ == 0) {
                            continue
                        }
                        val newI = i + dI
                        val newJ = j + dJ
                        if (newI >= 0 && newI < 10 && newJ >= 0 && newJ < 10) {
                            val newIndex = newI * 10 + newJ
                            if (b[newIndex] == CLOUD) {
                                return com.example.ngbp.rowColTransfer(i, j)
                            }
                        }
                    }
                }
            }
        }
    }
    return rowColTransfer(row, col)
}