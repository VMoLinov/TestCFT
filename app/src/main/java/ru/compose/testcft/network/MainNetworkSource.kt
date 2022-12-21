package ru.compose.testcft.network

import ru.compose.testcft.ui.mainscreen.MainScreenState

interface MainNetworkSource {

    suspend fun getData(number: String): MainScreenState
}
