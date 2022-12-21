package ru.compose.testcft.ui.mainscreen

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.compose.testcft.network.MainNetworkSource
import ru.compose.testcft.network.MainNetworkSourceImpl

class MainScreenViewModel(private val networkApi: MainNetworkSource = MainNetworkSourceImpl()) :
    ViewModel() {

    private val _data: MutableState<MainScreenState> = mutableStateOf(MainScreenState.Idle)
    val data: State<MainScreenState> = _data

    fun loadData(number: String) {
        viewModelScope.launch {
            _data.value = MainScreenState.Loading
            _data.value = networkApi.getData(number)
        }
    }
}
