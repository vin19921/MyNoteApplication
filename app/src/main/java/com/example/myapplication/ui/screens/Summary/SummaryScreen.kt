package com.example.myapplication.ui.screens.Summary

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.myapplication.viewmodel.NoteViewModel
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SummaryScreen(viewModel: NoteViewModel) {
    // Observe categories with their corresponding note counts using viewModel
    val categoriesWithNotesCounts by viewModel.getCategoriesWithNoteCounts().collectAsState(initial = emptyMap())

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Summary") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF301934),
                    titleContentColor = Color.White
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(horizontal = 16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            // Loop through each category and its note count
            categoriesWithNotesCounts.forEach { (category, count) ->
                Text(
                    text = category,
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(top = 16.dp)
                )
                // Display SummaryButton with the note count
                SummaryButton(count = count)
            }
        }
    }
}

