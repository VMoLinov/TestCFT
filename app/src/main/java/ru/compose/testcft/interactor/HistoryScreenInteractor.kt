package ru.compose.testcft.interactor

import ru.compose.testcft.model.local.Response

interface HistoryScreenInteractor {

    suspend fun getAll() : List<Response>

    suspend fun deleteAll()
}
