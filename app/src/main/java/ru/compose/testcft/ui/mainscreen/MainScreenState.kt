package ru.compose.testcft.ui.mainscreen

import ru.compose.testcft.model.ResponseDto

sealed class MainScreenState {
    class Success(val data: ResponseDto) : MainScreenState()
    class Error(val message: String) : MainScreenState()
    object Loading : MainScreenState()
    object Idle : MainScreenState()
}
