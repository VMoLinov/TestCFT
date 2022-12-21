package ru.compose.testcft.network

import retrofit2.http.GET
import retrofit2.http.Path
import ru.compose.testcft.model.ResponseDto

interface NetworkApi {

    @GET("{number}")
    suspend fun getData(@Path("number") number: String) : ResponseDto
}
