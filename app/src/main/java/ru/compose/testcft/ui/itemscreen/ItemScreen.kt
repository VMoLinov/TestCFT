package ru.compose.testcft.ui.itemscreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.compose.testcft.model.local.Response
import ru.compose.testcft.ui.mainscreen.BottomInfo
import ru.compose.testcft.ui.mainscreen.MainScreenState

@Composable
fun ItemScreen(response: Response) {
    Box(modifier = Modifier.padding(16.dp)) {
        BottomInfo(data = MainScreenState.Success(response), modifier = Modifier.padding(top = 20.dp))
    }
}
