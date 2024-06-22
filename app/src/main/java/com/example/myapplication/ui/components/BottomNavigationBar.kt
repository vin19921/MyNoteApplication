package com.example.myapplication.ui.components

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BottomNavigationBar(navController: NavController) {

    // NavigationBar composes the bottom navigation bar and its items
    NavigationBar {
        // Each NavigationBarItem represents a single tab in the bar

        NavigationBarItem(
            icon = { // This lambda defines the icon for the item
                Icon(Icons.Default.Home, contentDescription = "Home")
            },
            selected = currentRoute(navController) == Screen.Home.route, // Checks if this item is selected
            onClick = { navController.navigate(Screen.Home.route) } // Navigation action on click
        )

        NavigationBarItem(
            icon = { Icon(Icons.Default.Add, contentDescription = "New Note") },
            selected = currentRoute(navController) == Screen.NewNote.route,
            onClick = { navController.navigate(Screen.NewNote.route) }
        )

        NavigationBarItem(
            icon = { Icon(Icons.AutoMirrored.Filled.List, contentDescription = "Summary") },
            selected = currentRoute(navController) == Screen.Summary.route,
            onClick = { navController.navigate(Screen.Summary.route) }
        )
    }
}

@Composable
fun currentRoute(navController: NavController): String? {
    // Retrieves the current back stack entry from the NavController
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    // Returns the route (destination) of the current back stack entry (if any)
    return navBackStackEntry?.destination?.route
}
