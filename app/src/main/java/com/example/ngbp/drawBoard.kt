package com.example.ngbp

import androidx.gridlayout.widget.GridLayout
import android.widget.ImageButton
import androidx.core.view.get
import kotlinx.android.synthetic.main.activity_main.*

//fun drawBoard(board: IntArray, Grid: GridLayout) {
fun drawBoard(human: Boolean, Grid: GridLayout) {
    var cloudBoard = ngbpCloudMap
    var knownBoard = knownNgbpBoard

    if (human) {
        cloudBoard = humanCloudMap
        knownBoard = knownHumanBoard
    }

    for ((index, square) in cloudBoard.withIndex()) { // background
        var bTn =
            Grid.get(index) as ImageButton // set human board element colour based on result
        if (square == CLOUD) {
            bTn.setBackgroundColor(android.graphics.Color.WHITE)
            bTn.tooltipText = "Cloud"
        } else {
            // hole in cloud map - lets see whats in there!
            // states are fire sunk or water

            //for ((index, square) in knownHumanBoard.withIndex()) {
            var squareK = knownBoard[index]
            // var bTn = Grid.get(index) as ImageButton // set human board element colour based on result
            if (squareK == FIRE) {
                bTn.setBackgroundColor(android.graphics.Color.RED)
                bTn.tooltipText = "Fire"
            } else if (squareK == SUNK) {
                bTn.setBackgroundColor(android.graphics.Color.BLACK)
                bTn.tooltipText = "Sunk"
            } else if (squareK == WATER) {
                bTn.setBackgroundColor(android.graphics.Color.BLUE)
                bTn.tooltipText = "Water"
            }
        }
    }
}