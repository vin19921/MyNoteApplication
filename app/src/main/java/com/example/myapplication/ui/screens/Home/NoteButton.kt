package com.example.myapplication.ui.screens.Home

import androidx.compose.foundation.Image
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.myapplication.R
import com.example.myapplication.model.Note

@Composable
fun NoteButton(note: Note) {
    Button(
        // Set button click action (to be implemented later)
        onClick = { },
        // Set button color to purple and rounded corners
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6650a4)),
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            // Display first 20 characters of note content
            Text(
                text = note.content.take(20),
                modifier = Modifier.weight(1f)
            )
            // Display arrow right icon
            Image(
                painter = painterResource(R.drawable.ic_arrow_right),
                contentDescription = "arrow right icon"
            )
        }
    }
}