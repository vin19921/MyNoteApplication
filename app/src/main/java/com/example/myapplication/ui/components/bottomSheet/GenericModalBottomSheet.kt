package com.example.myapplication.ui.components.bottomSheet

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun GenericModalBottomSheet(
    // Content to be displayed inside the bottom sheet
    sheetContent: @Composable () -> Unit,

    // State of the bottom sheet, controlling its visibility and behavior
    sheetState: SheetState,

    // Callback function to be invoked when the bottom sheet is dismissed
    onDismiss: () -> Unit,
) {
    // ModalBottomSheet composable from Material 3
    ModalBottomSheet(
        // Lambda to handle dismiss request, invokes the provided onDismiss callback
        onDismissRequest = {
            onDismiss.invoke()
        },
        // State object controlling the bottom sheet's behavior and visibility
        sheetState = sheetState,
        // Background color of the bottom sheet container
        containerColor = Color.White,
    ) {
        // Content to be displayed inside the bottom sheet, provided as a composable lambda
        sheetContent()
    }
}