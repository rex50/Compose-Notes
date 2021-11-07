package com.rex50.notes.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.rex50.notes.base.Actions
import com.rex50.notes.navigation.Screen

@Composable
fun NotesListScreen(actions: NotesListActions) {
    var id: Int by rememberSaveable { mutableStateOf(0) }
    Column(
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(text = "Notes list")
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = {
            id++
            actions.openNoteDetails(id)
        }) {
            Text(text = "Open details screen")
        }
    }
}

class NotesListActions(private val navHostController: NavHostController): Actions(navHostController) {
    fun openNoteDetails(noteId: Int) {
        navHostController.navigate(Screen.NoteDetails.withArgs("$noteId"))
    }
}















