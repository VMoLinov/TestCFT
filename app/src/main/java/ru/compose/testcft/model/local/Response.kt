package ru.compose.testcft.model.local

import android.net.Uri
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.Gson
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity
data class Response(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo(name = "numbers") val numbers: String,
    @Embedded val bank: Bank?,
    @ColumnInfo(name = "brand") val brand: String?,
    @Embedded val country: Country?,
    @Embedded val number: Number?,
    @ColumnInfo(name = "prepaid") val prepaid: Boolean?,
    @ColumnInfo(name = "scheme") val scheme: String?,
    @ColumnInfo(name = "type") val type: String?
) : Parcelable {

    override fun toString(): String {
        return Uri.encode(Gson().toJson(this))
    }
}
