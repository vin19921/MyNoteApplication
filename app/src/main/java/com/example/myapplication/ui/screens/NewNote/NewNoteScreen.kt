package com.example.myapplication.ui.screens.NewNote

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.myapplication.ui.components.bottomSheet.GenericModalBottomSheet
import com.example.myapplication.viewmodel.NoteViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewNoteScreen(
    viewModel: NoteViewModel,
    navController: NavController
) {
    // Track note content and selected category using mutableStateOf
    var content by remember { mutableStateOf("") }
    var selectedCategory by remember { mutableStateOf("Select Category") }

    // Predefined list of categories for selection (can be replaced with logic to fetch categories)
    val categories = listOf("Work and Study", "Life", "Health and Well")

    Scaffold(
        topBar = {
            TopAppBar(
                // Set title, navigation back icon, and colors for top app bar
                title = { Text("New Note") },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF301934),
                    titleContentColor = Color.White
                )
            )
        }
    ) { paddingValues ->
        Surface {
            Column(
                // Set column modifier with padding and vertical spacing
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(top = 8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                // SelectNoteCategory composable for category selection
                SelectNoteCategory(
                    onCategorySelected = { category -> selectedCategory = category },
                )

                // Text field for note content with limitations (single line disabled)
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .height(300.dp)
                        .background(color = Color.Transparent, shape = RoundedCornerShape(16.dp))
                ) {
                    TextField(
                        value = content,
                        onValueChange = { content = it },
                        label = { Text("Please input note content") },
                        singleLine = false,
                        modifier = Modifier
                            .fillMaxSize(),
                        shape = RoundedCornerShape(16.dp)
                    )
                }

                Row {
                    // Save button with enabled state based on validation
                    Button(
                        onClick = {
                            viewModel.addNote(content, selectedCategory)
                            navController.popBackStack()
                        },
                        enabled = content.isNotBlank() && content.length <= 200 && selectedCategory in categories,
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF7D5260)),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                            .align(Alignment.Bottom)
                    ) {
                        Text("Save")
                    }
                }
            }
        }
    }
}