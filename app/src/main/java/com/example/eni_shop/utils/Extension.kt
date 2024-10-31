package com.example.eni_shop.utils

import java.text.SimpleDateFormat
import java.util.Date


fun Date.toFrenchDate() : String{
    val formatter = SimpleDateFormat("dd/MM/yyyy")
    return formatter.format(this)
}