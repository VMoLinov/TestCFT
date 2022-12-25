package ru.compose.testcft.navigation

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import ru.compose.testcft.model.local.Response
import ru.compose.testcft.ui.itemscreen.ItemScreen
import ru.compose.testcft.ui.mainscreen.MainScreen
import ru.compose.testcft.ui.nistoryscreen.HistoryScreen

@SuppressLint("NewApi")
@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = Points.MAIN.name
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable(Points.MAIN.name) {
            MainScreen(onNavigateToHistory = {
                navController.navigate(Points.HISTORY.name)
            })
        }
        composable(Points.HISTORY.name) {
            HistoryScreen(onNavigateToItem = {
                navController.navigate("${Points.ITEM.name}/$it")
            })
        }
        composable(
            "${Points.ITEM.name}/{response}",
            arguments = listOf(navArgument("response") { type = ResponseNav() })
        ) {
            it.arguments?.getParcelable<Response>("response")?.let { response -> ItemScreen(response) }
        }
    }
}

enum class Points(val string: String) {
    MAIN("main"),
    HISTORY("history"),
    ITEM("item");
}
