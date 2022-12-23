package ru.compose.testcft.ui.nistoryscreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import ru.compose.testcft.model.local.Response

@Composable
fun HistoryScreen(onNavigateToItem: () -> Unit) {
    val viewModel: HistoryScreenViewModel = hiltViewModel()
    LazyColumn {
        items(items = viewModel.data.value) {
            ResponseBody(response = it, modifier = Modifier.padding(top = 20.dp))
        }
    }
}

@Composable
fun ResponseBody(response: Response, modifier: Modifier) {
    Column(Modifier.fillMaxWidth(0.7f)) {
        Text(modifier = modifier, text = response.bank.toString())
        Text(modifier = modifier, text = response.number.toString())
        Text(modifier = modifier, text = response.country.toString())
        Text(modifier = modifier, text = response.scheme.toString())
    }
}
