package ru.compose.testcft.ui.mainscreen

import android.content.Intent
import android.net.Uri
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.*
import androidx.core.content.ContextCompat.startActivity
import androidx.hilt.navigation.compose.hiltViewModel
import ru.compose.testcft.R
import ru.compose.testcft.model.local.Bank
import ru.compose.testcft.model.local.Country
import ru.compose.testcft.model.local.Number
import ru.compose.testcft.model.local.Response


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
            contentDescription = stringResource(R.string.info)
        )
    }
}

@Composable
fun ResponseBody(response: Response, modifier: Modifier) {
    Column(Modifier.fillMaxWidth(0.7f)) {
        Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = modifier.fillMaxWidth()) {
            SingleBlock(name = response.scheme, header = stringResource(R.string.scheme_network))
            ChoiceBlock(
                type = response.type == stringResource(R.string.type_debit),
                property = stringResource(R.string.type),
                first = stringResource(R.string.type_debit),
                second = stringResource(R.string.type_credit)
            )
        }
        Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = modifier.fillMaxWidth()) {
            SingleBlock(name = response.brand, header = stringResource(R.string.brand))
            ChoiceBlock(
                type = response.prepaid,
                property = stringResource(R.string.prepaid),
                first = stringResource(R.string.yes),
                second = stringResource(R.string.no)
            )
        }
        CardNumber(modifier, response.number)
        CountryInfo(modifier, response.country)
        BankInfo(modifier, response.bank)
    }
}

@Composable
fun BankInfo(modifier: Modifier, bank: Bank?) {
    Column(modifier = modifier) {
        Text(text = stringResource(R.string.bank))
        Text(
            text = if (bank?.name != null) bank.name else stringResource(R.string.unknown),
            style = TextStyle(fontWeight = if (bank?.name != null) FontWeight.Bold else FontWeight.Normal)
        )
        val uriHandler = LocalUriHandler.current
        Text(
            text = bank?.url ?: stringResource(R.string.unknown),
            modifier = Modifier.clickable {
                bank?.url?.let { uriHandler.openUri("https://$it") }
            }
        )
        val context = LocalContext.current
        val intent = Intent(Intent.ACTION_DIAL)
        intent.data = Uri.parse("tel:${bank?.phone}")
        Text(
            text = if (bank?.phone != null) bank.phone else stringResource(R.string.unknown),
            modifier = Modifier.clickable {
                startActivity(context, intent, null)
            }
        )
    }
}

@Composable
fun CountryInfo(modifier: Modifier, country: Country?) {
    Column(modifier = modifier) {
        Text(text = stringResource(R.string.country))
        Text(
            text = if (country != null) "${country.alpha2} ${country.name}"
            else stringResource(R.string.unknown), style = TextStyle(fontWeight = FontWeight.Bold)
        )
        val string = buildAnnotatedString {
            append(stringResource(R.string.latitude_open))
            if (country?.latitude != null) {
                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                    append("${country.latitude.toInt()}")
                }
            } else append(stringResource(R.string.unknown))
            append(stringResource(R.string.longitude))
            if (country?.longitude != null) {
                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                    append("${country.longitude.toInt()}")
                }
            } else {
                append(stringResource(R.string.unknown))
                append(stringResource(R.string.close_brace))
            }
        }
        val context = LocalContext.current
        val gmmIntentUri =
            Uri.parse("geo:${country?.longitude},${country?.longitude}10.013988")
        val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
        mapIntent.setPackage("com.google.android.apps.maps")
        Text(text = string, modifier = Modifier.clickable {
            startActivity(context, mapIntent, null)
        })
    }
}

@Composable
fun CardNumber(modifier: Modifier, number: Number?) {
    Column(modifier = modifier.fillMaxWidth()) {
        Text(textAlign = TextAlign.Center, text = stringResource(R.string.card_number))
        Row {
            SingleBlock(name = number?.length.toString(), header = stringResource(R.string.length))
            ChoiceBlock(
                type = number?.luhn,
                property = stringResource(R.string.luhn),
                first = stringResource(R.string.yes),
                second = stringResource(R.string.no)
            )
        }
    }
}

@Suppress("OPT_IN_IS_NOT_ENABLED")
@OptIn(ExperimentalUnitApi::class)
@Composable
fun ChoiceBlock(type: Boolean?, property: String, first: String, second: String) {
    Column(modifier = Modifier.width(150.dp)) {
        Text(text = property, fontSize = TextUnit(16f, TextUnitType.Sp))
        val string = buildAnnotatedString {
            if (type != null) {
                withStyle(
                    style = SpanStyle(
                        fontWeight = if (type) FontWeight.Bold
                        else FontWeight.Normal
                    )
                ) { append(first) }
                append(stringResource(R.string.slash))
                withStyle(
                    style = SpanStyle(
                        fontWeight = if (!type) FontWeight.Bold
                        else FontWeight.Normal
                    )
                ) { append(second) }
            } else append(stringResource(R.string.unknown))
        }
        Text(text = string)
    }
}

@Suppress("OPT_IN_IS_NOT_ENABLED")
@OptIn(ExperimentalUnitApi::class)
@Composable
fun SingleBlock(name: String?, header: String) {
    Column(modifier = Modifier.width(150.dp)) {
        Text(
            text = header,
            fontSize = TextUnit(16f, TextUnitType.Sp)
        )
        Text(
            text = if (name?.isNotBlank() == true) name else stringResource(R.string.unknown),
            fontWeight = if (name?.isNotBlank() == true) FontWeight.Bold else FontWeight.Normal
        )
    }
}

@Composable
fun Placeholder() {
    Text(text = stringResource(R.string.example_number), modifier = Modifier.alpha(0.5f))
}

fun checkCharacter(it: String): Boolean {
    return when {
        it.isNotEmpty() -> it.last().isDigit()
        it.length > 8 -> false
        else -> true
    }
}
