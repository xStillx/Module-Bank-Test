package com.example.modulebanktest.utils

fun Double.format(scale: Int) = "%.${scale}f".format(this)