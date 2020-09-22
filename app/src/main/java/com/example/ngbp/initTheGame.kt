package com.example.ngbp

//fun initTheGame(): initVals {
fun initTheGame(): IntArray {

    // actual position of ships on human board
    // var secretHumanBoard = Array(10) { IntArray(10) }
    //var secretHumanBoard = IntArray(100) { 0 }
    // return state of enemy's board - what ngbp learns through shelling
    //var knownHumanBoard = Array(10) { IntArray(10) {-1}} // default to -1
    //var knownHumanBoard = IntArray(100) { -1 } // default to -1
    // return computer's board
    //var secretNgbpBoard = IntArray(100) { 0 }

    // return available enemy ship list

    /*
    var shipList = arrayOf<ship>(
        ship("Aircraft Carrier", 6),
        ship("BattleShip", 5),
        ship("Destroyer", 4),
        ship("Cruiser", 3),
        ship("Submarine", 2)
    )

    var points = 0
    for (ship in shipList) {
        points += ship.length
    }
    */


    // https://lawcomic.net/guide/?p=864

    // return points for each player -> 20 in the default configuration
    var humanPoint = 20 // points
    var ngbpPoint = 20 // points

    var secretNgbpBoard = makeSecretBoard(humanShipList) // put ships on computers board
    var best = rating(secretNgbpBoard)
    for (i in 0..1000) {
        var newB = makeSecretBoard(humanShipList)
        var newRating = rating(newB)
        if (newRating < best) {
            best = newRating
            secretNgbpBoard = newB
        }
        if (best == 0){
            break
        }
    }


    //val retVal = initVals(secretNgbpBoard, shipList)

    return secretNgbpBoard
}

/*
fun addElement(arr: Array<ship>, element: ship): Array<ship> {
    val mutableArray = arr.toMutableList()
    mutableArray.add(element)
    return mutableArray.toTypedArray()
}
*/

