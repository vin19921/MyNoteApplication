package com.example.myapplication.ui.screens.NewNote

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.components.bottomSheet.GenericModalBottomSheet
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectNoteCategory(
    onCategorySelected: (String) -> Unit
) {
    // Track the visibility state of the bottom sheet
    val showModalBottomSheetValue = remember { mutableStateOf(false) }
    // Manage the state of the bottom sheet
    val sheetState = rememberModalBottomSheetState()
    // Coroutine scope for asynchronous operations within the composable
    val coroutineScope = rememberCoroutineScope()
    // Track the currently selected category
    var selectedCategory by remember { mutableStateOf("Choose a category") }
    // Predefined list of categories
    val categories = listOf("Work and Study", "Life", "Health and Well")

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Button(
            // Toggle the bottom sheet visibility on button click
            onClick = { showModalBottomSheetValue.value = !showModalBottomSheetValue.value },
            modifier = Modifier
                .fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6650a4))
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = selectedCategory,
                    modifier = Modifier.weight(1f)
                )
                Icon(
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = "Arrow Down"
                )
            }
        }

        // Conditionally display the bottom sheet
        if (showModalBottomSheetValue.value) {
            GenericModalBottomSheet(
                // Handle bottom sheet dismissal
                onDismiss = {
                    showModalBottomSheetValue.value = false

                    // Dismiss the sheet using coroutines for proper animation
                    coroutineScope.launch {
                        coroutineScope.launch {
                            sheetState.hide()
                        }.invokeOnCompletion {
                            showModalBottomSheetValue.value = false
                        }
                    }

                },
                sheetState = sheetState,
                // Define the content displayed within the bottom sheet
                sheetContent = {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 36.dp)
                            .background(color = Color.White)
                    ) {
                        Column {
                            // List items for each category with selection logic
                            categories.forEach { category ->
                                ListItem(
                                    modifier = Modifier.clickable {
                                        selectedCategory = category
                                        onCategorySelected(category)
                                        // Dismiss the sheet upon selection (using coroutines)
                                        coroutineScope.launch {
                                            coroutineScope.launch {
                                                sheetState.hide()
                                            }.invokeOnCompletion {
                                                showModalBottomSheetValue.value = false
                                            }
                                        }
                                    },
                                    headlineContent = { Text(category) }
                                )
                            }
                        }
                    }
                }
            )
        }
    }
}
