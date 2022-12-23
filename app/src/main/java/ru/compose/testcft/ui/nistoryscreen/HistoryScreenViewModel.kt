package ru.compose.testcft.ui.nistoryscreen

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.compose.testcft.interactor.HistoryScreenInteractor
import ru.compose.testcft.model.local.Response
import javax.inject.Inject

@HiltViewModel
class HistoryScreenViewModel @Inject constructor(private val interactor: HistoryScreenInteractor) :
    ViewModel() {

    private val _data: MutableState<List<Response>> = mutableStateOf(listOf())
    val data: State<List<Response>> = _data

    init {
        viewModelScope.launch {
            _data.value = interactor.getAll()
        }
    }

    fun clearBase() {
        viewModelScope.launch {
            interactor.deleteAll()
            _data.value = listOf()
        }
    }
}
