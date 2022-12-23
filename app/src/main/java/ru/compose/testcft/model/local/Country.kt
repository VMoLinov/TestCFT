package ru.compose.testcft.model.local

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity
data class Country(
    @ColumnInfo(name = "alpha2") val alpha2: String?,
    @ColumnInfo(name = "currency") val currency: String?,
    @ColumnInfo(name = "emoji") val emoji: String?,
    @ColumnInfo(name = "latitude") val latitude: Int?,
    @ColumnInfo(name = "longitude") val longitude: Int?,
    @ColumnInfo(name = "country_name") val name: String?,
    @ColumnInfo(name = "numeric") val numeric: String?
)
