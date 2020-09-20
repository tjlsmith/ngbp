package com.example.ngbp

import androidx.gridlayout.widget.GridLayout
import android.widget.ImageButton
import androidx.core.view.get
import kotlinx.android.synthetic.main.activity_main.*

fun drawHumanBoard(unKnownHumanBoard: IntArray, HumanGrid: GridLayout) {
    for ((index, square) in unKnownHumanBoard.withIndex()) {
        var hBtn =
            HumanGrid.get(index) as ImageButton // set human board element colour based on result
        if (square == WATER) {
            hBtn.setBackgroundColor(android.graphics.Color.WHITE)
            hBtn.tooltipText = "Cloud"
        } else {
            hBtn.setBackgroundColor(android.graphics.Color.LTGRAY)
            hBtn.tooltipText = "Ship"
        }
    }
}