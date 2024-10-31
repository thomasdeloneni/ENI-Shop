package com.example.eni_shop.utils

import androidx.room.TypeConverter
import java.util.Date


class DateConverter {

    @TypeConverter
    fun dateToTimestamp(date: Date): Long {
        return date.time
    }

    @TypeConverter
    fun timestampToDate(value: Long): Date {
        return Date(value)
    }
}