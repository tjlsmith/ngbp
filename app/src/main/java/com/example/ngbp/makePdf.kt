package com.example.ngbp

fun makePdf(khb: IntArray, shipList: Array<ship>) {
// return pdf of possible ships on human board
    var pdfBoard = IntArray(100) { 0 }
    val vectors = intArrayOf(-1, -1, -1, 0, -1, 1, 0, -1, 0, 1, 1, -1, 1, 0, 1, 1)
    var square = IntArray(2) { 0 }
    var list = listOf(square)
    for (ship in shipList) {
        for (i in 0..ship.length) {
            for (row in 0..9) {
                for (col in 0..9) {

                }
            }
        }
    }

}