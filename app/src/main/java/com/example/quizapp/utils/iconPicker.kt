package com.example.quizapp.utils

import com.example.quizapp.R

object iconPicker{
    val icons = arrayOf(R.drawable.icon1,
    R.drawable.icon2,
    R.drawable.icon3,
    R.drawable.icon4,
    R.drawable.icon5,
    R.drawable.icon6)

    var currentIcon=0

    fun getIcon(): Int{
        currentIcon = (currentIcon+1) % icons.size
        return icons[currentIcon]
    }
}