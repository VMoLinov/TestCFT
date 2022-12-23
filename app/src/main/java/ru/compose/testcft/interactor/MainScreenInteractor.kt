package ru.compose.testcft.interactor

import ru.compose.testcft.model.local.Response
import ru.compose.testcft.ui.mainscreen.MainScreenState

interface MainScreenInteractor {

    suspend fun getNetworkData(number: String): MainScreenState

    suspend fun insertData(response: Response)
}
