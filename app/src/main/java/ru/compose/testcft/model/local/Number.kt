package ru.compose.testcft.model.local

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity
data class Number(
    @ColumnInfo(name = "length") val length: Int?,
    @ColumnInfo(name = "luhn") val luhn: Boolean?
) : Parcelable
