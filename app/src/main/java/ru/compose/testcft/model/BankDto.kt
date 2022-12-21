package ru.compose.testcft.model

import com.google.gson.annotations.SerializedName

data class BankDto(
    @SerializedName("city") val city: String?,
    @SerializedName("name") val name: String?,
    @SerializedName("phone") val phone: String?,
    @SerializedName("url") val url: String?
)
