package ru.compose.testcft.model.local

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity
data class Bank(
    @ColumnInfo(name = "city") val city: String?,
    @ColumnInfo(name = "name") val name: String?,
    @ColumnInfo(name = "phone") val phone: String?,
    @ColumnInfo(name = "url") val url: String?
)
