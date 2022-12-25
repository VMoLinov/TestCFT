package ru.compose.testcft.ui.nistoryscreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import ru.compose.testcft.R
import ru.compose.testcft.model.local.Response

@Composable
fun HistoryScreen(onNavigateToItem: (Response) -> Unit) {
    val viewModel: HistoryScreenViewModel = hiltViewModel()
    HistoryTopBar(viewModel) {
        LazyColumn(modifier = Modifier.padding(it)) {
            items(items = viewModel.data.value) { response ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .clickable { onNavigateToItem(response) }
                ) {
                    val string = buildAnnotatedString {
                        append("Number: ")
                        append(response.numbers)
                        append(" Schema: ")
                        append(response.scheme.toString())
                    }
                    Text(text = string)
                }
            }
        }
    }
}

@Composable
fun HistoryTopBar(viewModel: HistoryScreenViewModel, content: @Composable (PaddingValues) -> Unit) {
    Scaffold(topBar = {
        TopAppBar(
            title = {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) { Text(text = stringResource(R.string.history_of_responses)) }
            },
            actions = {
                Icon(
                    modifier = Modifier
                        .size(40.dp)
                        .clickable { viewModel.clearBase() },
                    imageVector = Icons.Default.Clear,
                    contentDescription = stringResource(R.string.icon_clear)
                )
            }
        )
    }) { content(it) }
}
