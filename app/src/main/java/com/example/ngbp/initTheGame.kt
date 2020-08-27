package com.example.ngbp

data class ship(val name: String, val length: Int)

fun initTheGame() {

    // return state of enemy's board
    val humanBoard = Array(10) { IntArray(10) }
    // return computer's board
    val ngbpBoard = Array(10) { IntArray(10) }

    // return available enemy ship list
    var shipList = arrayOf<ship>()
    shipList = addElement(shipList, ship("Aircraft Carrier", 6))
    shipList = addElement(shipList, ship("BattleShip", 5))
    shipList = addElement(shipList, ship("Destroyer", 4))
    shipList = addElement(shipList, ship("Cruiser", 3))
    shipList = addElement(shipList, ship("Submarine", 2))

    var points = 0
    for (ship in shipList) {
        points += ship.length
    }

    // return points for each player -> 20 in the default configuration
    var humanPoint = points
    var ngbpPoint = points
}

fun addElement(arr: Array<ship>, element: ship): Array<ship> {
    val mutableArray = arr.toMutableList()
    mutableArray.add(element)
    return mutableArray.toTypedArray()
}
