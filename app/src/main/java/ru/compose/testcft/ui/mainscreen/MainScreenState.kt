package ru.compose.testcft.ui.mainscreen

import ru.compose.testcft.model.local.Response

sealed class MainScreenState {
    class Success(val data: Response) : MainScreenState()
    class Error(val message: String) : MainScreenState()
    object Loading : MainScreenState()
    object Idle : MainScreenState()
}
