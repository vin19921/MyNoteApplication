package com.example.myapplication.ui.screens.Settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.myapplication.ui.components.Screen
import com.example.myapplication.viewmodel.NoteViewModel

@Composable
fun ButtonList(
    viewModel: NoteViewModel,
    navController: NavController
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 8.dp)
    ) {
        val buttons = listOf(
            "Online Customer" to "https://example.com/online-customer",
            "User Agreement" to "https://example.com/user-agreement",
            "Privacy Policy" to "https://example.com/privacy-policy",
            "About Us" to "https://example.com/about-us"
        )

        // Display buttons for opening URLs in a web browser
        buttons.forEach { (text, url) ->
            OpenUrlButton(text = text, url = url)
        }

        // Spacer to fill remaining vertical space
        Spacer(modifier = Modifier.weight(1f))

        // Button for deleting all notes and navigating back to Home screen
        Button(
            onClick = {
                viewModel.deleteAllNotes()
                navController.navigate(Screen.Home.route) {
                    // Clear back stack and navigate to Home (inclusive)
                    popUpTo(Screen.Home.route) { inclusive = true }
                }
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF7D5260)),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text("Delete All Notes")
        }
    }
}
