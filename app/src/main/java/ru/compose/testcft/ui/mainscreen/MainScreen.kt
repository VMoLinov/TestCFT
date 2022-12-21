package ru.compose.testcft.ui.mainscreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.List
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import ru.compose.testcft.model.ResponseDto

@Suppress("OPT_IN_IS_NOT_ENABLED")
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun MainScreen(onNavigateToHistory: () -> Unit) {
    val viewModel: MainScreenViewModel = hiltViewModel()
    var text by remember { mutableStateOf("") }
    val data by remember(viewModel) { viewModel.data }
    val modifierWithTop = Modifier.padding(top = 20.dp)
    val keyboard = LocalSoftwareKeyboardController.current
    val loadData = { viewModel.loadData(text) }
    MainTopBar(onNavigateToHistory) { paddingValues ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Column(
                modifier = modifierWithTop,
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                OutlinedTextField(
                    value = text,
                    placeholder = { Placeholder() },
                    onValueChange = { if (checkCharacter(it)) text = it },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(onDone = { loadData(); keyboard?.hide() })
                )
                Button(
                    modifier = modifierWithTop,
                    onClick = { loadData(); keyboard?.hide() }
                ) { Text(text = "Check!") }
                BottomInfo(data, modifierWithTop)
            }
        }
    }
}

@Composable
fun MainTopBar(
    onNavigateToHistory: () -> Unit,
    content: @Composable (PaddingValues) -> Unit
) {
    Scaffold(topBar = {
        TopAppBar(
            title = {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) { Text(text = "Enter a numbers") }
            },
            actions = {
                Icon(
                    modifier = Modifier
                        .size(40.dp)
                        .clickable { onNavigateToHistory() },
                    imageVector = Icons.Default.List, contentDescription = "History"
                )
            }
        )
    }) { content(it) }
}

@Composable
fun BottomInfo(data: MainScreenState, modifier: Modifier) {
    when (data) {
        is MainScreenState.Success -> ResponseBody(data.data, modifier)
        is MainScreenState.Loading -> CircularProgressIndicator(modifier)
        is MainScreenState.Error -> Text(modifier = modifier, text = data.message)
        is MainScreenState.Idle -> Icon(
            modifier = modifier,
            imageVector = Icons.Default.Info,
            contentDescription = "Info"
        )
    }
}

@Composable
fun ResponseBody(response: ResponseDto, modifier: Modifier) {
    Column(Modifier.fillMaxWidth(0.7f)) {
        Text(modifier = modifier, text = response.bankDto.toString())
        Text(modifier = modifier, text = response.numberDto.toString())
        Text(modifier = modifier, text = response.countryDto.toString())
        Text(modifier = modifier, text = response.scheme.toString())
    }
}

@Composable
fun Placeholder() {
    Text(text = "0000", modifier = Modifier.alpha(0.5f))
}

fun checkCharacter(it: String): Boolean {
    return when {
        it.isNotEmpty() -> it.last().isDigit()
        it.length > 8 -> false
        else -> true
    }
}

@Preview(showBackground = true)
@Composable
fun MainPreview() {
    MainScreen {}
}
