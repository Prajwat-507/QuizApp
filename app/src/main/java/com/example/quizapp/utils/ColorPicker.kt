package com.example.quizapp.utils

import android.graphics.Color

object ColorPicker {
    val color = arrayListOf<String>(
        "#FFFF0000", "#FF07AEFA",
        "#FFFFEB3B", "#FF08A50F",
        "#FFFF5722", "#FF7736E9", "#FFF333DF"

    )
    var currentColorIndex = 0
    fun getColor(): String {
        currentColorIndex = (currentColorIndex + 1) % color.size
        return color[currentColorIndex]
    }
}