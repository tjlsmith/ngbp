package com.example.ngbp

fun initTheGame(): initVals {

    // actual position of ships on human board
    // var unKnownHumanBoard = Array(10) { IntArray(10) }
    //var unKnownHumanBoard = IntArray(100) { 0 }
    // return state of enemy's board - what ngbp learns through shelling
    //var knownHumanBoard = Array(10) { IntArray(10) {-1}} // default to -1
    //var knownHumanBoard = IntArray(100) { -1 } // default to -1
    // return computer's board
    var ngbpBoard = IntArray(100) { 0 }

    var points = 0
    for (ship in shipList) {
        points += ship.length
    }

    // https://lawcomic.net/guide/?p=864

    // return points for each player -> 20 in the default configuration
    var humanPoint = points
    var ngbpPoint = points

    val retVal =
        initVals(ngbpPoint, humanPoint, ngbpBoard, shipList)

    return retVal
}

fun addElement(arr: Array<ship>, element: ship): Array<ship> {
    val mutableArray = arr.toMutableList()
    mutableArray.add(element)
    return mutableArray.toTypedArray()
}
