package ru.compose.testcft.ui.mainscreen

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.compose.testcft.interactor.MainScreenInteractor
import ru.compose.testcft.model.local.Response
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(private val interactor: MainScreenInteractor) :
    ViewModel() {

    private val _data: MutableState<MainScreenState> = mutableStateOf(MainScreenState.Idle)
    val data: State<MainScreenState> = _data

    fun loadData(number: String) {
        viewModelScope.launch {
            _data.value = MainScreenState.Loading
            _data.value = interactor.getNetworkData(number)
        }
    }

    fun saveResponse(response: Response) {
        viewModelScope.launch {
            interactor.insertData(response)
        }
    }
}
