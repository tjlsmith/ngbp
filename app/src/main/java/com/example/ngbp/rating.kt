package com.example.ngbp

fun rating(b: IntArray): Int {
    // rating this layout by the rules -
    // a point for each ship in the middle of the board
    // a point for each ship touching another ship
    // lower score better

    var score = 0
    var i = 44 // penilize middle squares
    score += quilty(b, i)
    i = 45
    score += quilty(b, i)
    i = 54
    score += quilty(b, i)
    i = 55
    score += quilty(b, i)
    // no corners
    i = 0
    score += quilty(b, i)
    i = 9
    score += quilty(b, i)
    i = 90
    score += quilty(b, i)
    i = 99
    score += quilty(b, i)
    // no edges
    var row = 0
    for (col in 0..9) {
        score += quilty(b, row * 10 + col)
    }
    row = 9
    for (col in 0..9) {
        score += quilty(b, row * 10 + col)
    }
    var col = 0
    for (row in 0..9) {
        score += quilty(b, row * 10 + col)
    }
    col = 9
    for (row in 0..9) {
        score += quilty(b, row * 10 + col)
    }
    // touching other chips
    for (row in 0..9) {
        for (col in 0..9) {
            val index = row * 10 + col
            if (b[index] > 1 && b[index] < 7) {
                // must have a ship in it
                // look around in all legal squares for another ship
                for (dRow in -1..1) {
                    for (dCol in -1..1) {
                        if (dRow == 0 && dCol == 0) {
                            continue
                        }
                        val cRow = row + dRow
                        val cCol = col + dCol
                        if (cRow >= 0 && cRow < 10 && cCol >= 0 && cCol < 10) {
                            val index2 = cRow * 10 + cCol
                            if (b[index2] > 1 && b[index2] < 7 && b[index2] != b[index]) {
                                score++
                            }
                        }
                    }
                }
            }
        }
    }
    return score
}

fun quilty(b: IntArray, i: Int): Int {
    var score = 0
    if (b[i] > 1 && b[i] < 7) {
        score++
    }
    return score
}