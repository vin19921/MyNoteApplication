package com.example.myapplication.ui.components

sealed class Screen(val route: String) {
    // Data objects represent different screens in the app
    data object Home : Screen("home")
    data object NewNote : Screen("new_note")
    data object Summary : Screen("summary")
    data object Settings : Screen("settings")
}
