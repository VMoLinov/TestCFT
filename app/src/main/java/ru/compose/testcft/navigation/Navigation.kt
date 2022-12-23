package ru.compose.testcft.navigation

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
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

            })
        }
        composable(Points.ITEM.name) {

        }
    }
}

enum class Points(val string: String) {
    MAIN("main"),
    HISTORY("history"),
    ITEM("item");
}
