package com.rex50.notes.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.rex50.notes.Screen

@Composable
fun NotesListScreen(navController: NavController) {
    var count: Int by rememberSaveable { mutableStateOf(0) }
    Column(
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(text = "Notes list")
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = {
            count++
            navController.navigate(Screen.NoteDetailsScreen.withArgs("$count"))
        }) {
            Text(text = "Open details screen")
        }
    }
}















