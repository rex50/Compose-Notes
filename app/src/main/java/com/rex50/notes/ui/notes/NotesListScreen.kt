package com.rex50.notes.ui.notes

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.rex50.notes.base.Actions
import com.rex50.notes.data.model.Data
import com.rex50.notes.data.model.Note
import com.rex50.notes.navigation.Screen
import com.rex50.notes.ui.components.NoteCard

@Composable
fun NotesListScreen(
    viewModel: NotesListViewModel,
    actions: NotesListActions
) {
    val context = LocalContext.current
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "My Notes")
                },
                actions = {
                    IconButton(
                        onClick = { showToast(context, "Work in progress") },
                    ) {
                        Icon(imageVector = Icons.Default.Info, contentDescription = "About")
                    }
                }
            )
        },
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = {
            FloatingActionButton(onClick = { showToast(context, "Work in progress") }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "fab icon")
            }
        }
    ) {
        when(val result = viewModel.notes.value) {
            is Data.Loading -> {
                Loader()
            }

            is Data.Ready -> {
                NotesList(
                    notes = result.data,
                    onNoteClicked = {
                        actions.openNoteDetails(it.id)
                    }
                )
            }

            is Data.Error -> {
                ErrorMessage(message = result.msg)
            }
        }
    }
}

@Composable
fun Loader() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun ErrorMessage(message: String) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        Text(text = message)
    }
}

@Composable
fun NotesList(
    notes: List<Note>,
    modifier: Modifier = Modifier,
    onNoteClicked: ((Note) -> Unit)? = null
) {
    LazyColumn(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        items(notes.size) { index ->
            if(index == 0) {
                Spacer(modifier = Modifier.height(8.dp))
            }
            NoteCard(
                note = notes[index],
                onClick = {
                    onNoteClicked?.invoke(notes[index])
                }
            )
        }
    }
}

private fun showToast(context:Context, msg: String) {
    Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
}
class NotesListActions(private val navHostController: NavHostController): Actions(navHostController) {
    fun openNoteDetails(noteId: Int) {
        navHostController.navigate(Screen.NoteDetails.withArgs("$noteId"))
    }
}















