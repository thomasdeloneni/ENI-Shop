package com.example.eni_shop.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun Date.toFrenchDate() : String {
    val formatter = SimpleDateFormat("dd/MMM/yyyy", Locale.FRENCH)
    return formatter.format(this)
}