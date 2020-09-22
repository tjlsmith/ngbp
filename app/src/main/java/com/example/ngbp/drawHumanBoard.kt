package com.example.ngbp

import androidx.gridlayout.widget.GridLayout
import android.widget.ImageButton
import androidx.core.view.get
import kotlinx.android.synthetic.main.activity_main.*

fun drawBoard(human: Boolean, Grid: GridLayout) {
    for ((index, square) in unKnownHumanBoard.withIndex()) { // background
        var hBtn =
            Grid.get(index) as ImageButton // set human board element colour based on result
        if (square == CLOUD) {
            hBtn.setBackgroundColor(android.graphics.Color.WHITE)
            hBtn.tooltipText = "Cloud"
        } else if (square == WATER) {
            hBtn.setBackgroundColor(android.graphics.Color.BLUE)
            hBtn.tooltipText = "Water"
        } else {
            hBtn.setBackgroundColor(android.graphics.Color.LTGRAY)
            hBtn.tooltipText = "Ship"
        }
    }
    for ((index, square) in knownHumanBoard.withIndex()) {
        var hBtn =
            Grid.get(index) as ImageButton // set human board element colour based on result
        if (square == FIRE) {
            hBtn.setBackgroundColor(android.graphics.Color.RED)
            hBtn.tooltipText = "Fire"
        } else if (square == SUNK) {
            hBtn.setBackgroundColor(android.graphics.Color.BLACK)
            hBtn.tooltipText = "Sunk"
        }
    }
}