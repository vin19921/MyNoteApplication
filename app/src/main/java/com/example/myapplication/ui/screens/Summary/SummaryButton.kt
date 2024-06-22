package com.example.myapplication.ui.screens.Summary

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun SummaryButton(count: Int) {
    Button(
        // Currently no functionality assigned to the click action (empty onClick)
        onClick = { },
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6650a4)), // Set button color to purple
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(40.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                // Display formatted text with the note count
                text = "This topic has a total of $count records",
                modifier = Modifier.weight(1f)
            )
        }
    }
}
