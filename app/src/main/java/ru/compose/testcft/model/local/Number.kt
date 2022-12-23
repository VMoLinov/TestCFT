package ru.compose.testcft.model.local

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity
data class Number(
    @ColumnInfo(name = "length") val length: Int?,
    @ColumnInfo(name = "luhn") val luhn: Boolean?
)
