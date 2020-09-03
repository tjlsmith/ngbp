package com.example.ngbp

fun selectMove(pdf: IntArray): Int {
    var list = mutableListOf<Int>() // empty list of best moves
    var best = -1
    var pointer = 0
    for (element in pdf) {
        if (element > best) {
            list.clear() // new empty list of best moves
            best = element
            list.add(pointer)
        } else if (element == best) {
            list.add(pointer)
        }
        pointer++
    }
    return list.shuffled().get(0)
}