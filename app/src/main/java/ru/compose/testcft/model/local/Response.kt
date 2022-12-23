package ru.compose.testcft.model.local

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Response(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @Embedded val bank: Bank,
    @ColumnInfo(name = "brand") val brand: String?,
    @Embedded val country: Country,
    @Embedded val number: Number,
    @ColumnInfo(name = "prepaid") val prepaid: Boolean?,
    @ColumnInfo(name = "scheme") val scheme: String?,
    @ColumnInfo(name = "type") val type: String?
)
