package com.example.myapplication.ui

import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.ui.components.BottomNavigationBar
import com.example.myapplication.ui.components.Screen
import com.example.myapplication.ui.components.currentRoute
import com.example.myapplication.ui.screens.Home.HomeScreen
import com.example.myapplication.ui.screens.NewNote.NewNoteScreen
import com.example.myapplication.ui.screens.Settings.SettingsScreen
import com.example.myapplication.ui.screens.Summary.SummaryScreen
import com.example.myapplication.viewmodel.NoteViewModel

@Composable
fun MainScreen() {
    // Remember the NavController and create a NoteViewModel instance
    val navController = rememberNavController()
    val viewModel: NoteViewModel = viewModel()

    Scaffold(
        bottomBar = {
            // Conditionally display the bottom navigation bar
            if (currentRoute(navController) in listOf(Screen.Home.route, Screen.Summary.route)) {
                BottomNavigationBar(navController)
            }
        }
    ) { innerPadding ->
        NavHost(
            // Set up NavHost with navController, start destination, and padding
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            // Define composables for each screen
            composable(Screen.Home.route) {
                HomeScreen(viewModel, navController)
            }
            composable(
                route = Screen.NewNote.route,
                enterTransition = { slideInHorizontally(initialOffsetX = { 1000 }, animationSpec = tween(500)) },
                exitTransition = { slideOutHorizontally(targetOffsetX = { 1000 }, animationSpec = tween(500)) }
            ) {
                NewNoteScreen(viewModel, navController)
            }
            composable(Screen.Summary.route) {
                SummaryScreen(viewModel)
            }
            composable(
                route = Screen.Settings.route,
                enterTransition = { slideInHorizontally(initialOffsetX = { 1000 }, animationSpec = tween(500)) },
                exitTransition = { slideOutHorizontally(targetOffsetX = { 1000 }, animationSpec = tween(500)) }
            ) {
                SettingsScreen(viewModel, navController)
            }
        }
    }
}
