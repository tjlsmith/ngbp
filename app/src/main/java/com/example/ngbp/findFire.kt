package com.example.ngbp

fun findFire(knownHumanBoard: IntArray): rowColTransfer {
    // must find fire with cloud next to it

    var row = -1
    var col = -1
    for (i in 0..9) {
        for (j in 0..9) {
            val index = 10 * i + j
            if (knownHumanBoard[index] == FIRE) {
                for (dI in -1..1) {
                    for (dJ in -1..1) {
                        if (dI == 0 && dJ == 0) {
                            continue
                        }
                        val newI = i + dI
                        val newJ = j + dJ
                        val newBehindI = i - dI
                        val newBehindJ = j - dJ
                        if (newI >= 0 && newI < 10 && newJ >= 0 && newJ < 10 && newBehindI >= 0 && newBehindI < 10 && newBehindJ >= 0 && newBehindJ < 10) {
                            val newIndex = newI * 10 + newJ
                            val newBehindIndex = newBehindI * 10 + newBehindJ
                            if (knownHumanBoard[newIndex] == CLOUD && knownHumanBoard[newBehindIndex] == FIRE) {
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