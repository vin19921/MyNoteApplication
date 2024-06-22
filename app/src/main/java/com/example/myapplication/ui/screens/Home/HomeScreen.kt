package com.example.myapplication.ui.screens.Home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.myapplication.viewmodel.NoteViewModel
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.myapplication.ui.components.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(viewModel: NoteViewModel, navController: NavController) {

    // Observe the categories with notes from the viewModel
    val categoriesWithNotes by viewModel.getCategoriesWithNotes().collectAsState(initial = emptyMap())

    Scaffold(
        topBar = {
            TopAppBar(
                // Set the title and actions for the top app bar
                title = { Text("Home") },
                actions = {
                    IconButton(
                        // Navigate to Settings screen on click
                        onClick = { navController.navigate(Screen.Settings.route) }
                    ) {
                        Icon(
                            Icons.Default.Settings,
                            contentDescription = "Settings",
                            tint = Color.White
                        )
                    }
                },
                // Customize TopAppBar colors
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF301934),
                    titleContentColor = Color.White
                )
            )
        }
    ) { paddingValues ->
        // LazyColumn for scrollable content with padding and spacing
        LazyColumn(
            contentPadding = paddingValues,
            modifier = Modifier.padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // Loop through categories and their notes
            categoriesWithNotes.forEach { (category, notes) ->
                item {
                    // Display category title
                    Text(
                        text = category,
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }
                // Display up to 3 notes per category with NoteButton composable
                items(notes.take(3)) { note ->
                    NoteButton(note)
                }
            }
        }
    }
}

