package com.rex50.notes.ui.notes

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
import com.rex50.notes.enums.DialogState
import com.rex50.notes.extensions.toast
import com.rex50.notes.navigation.Screen
import com.rex50.notes.ui.components.NewNoteDialog
import com.rex50.notes.ui.components.NoteCard

@Composable
fun NotesListScreen(
    viewModel: NotesListViewModel,
    actions: NotesListActions
) {
    val context = LocalContext.current
    var dialogState by remember {
        mutableStateOf(DialogState.HIDDEN)
    }

    dialogState = when(val result = viewModel.addStatus.value) {
        is Data.Loading -> {
            DialogState.LOADING
        }

        is Data.Ready -> {
            if(result.data)
                context.toast("Added successfully")
            DialogState.HIDDEN
        }

        is Data.Error -> {
            context.toast(result.msg)
            DialogState.HIDDEN
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "My Notes")
                },
                actions = {
                    IconButton(
                        onClick = { context.toast("Work in progress") },
                    ) {
                        Icon(imageVector = Icons.Default.Info, contentDescription = "About")
                    }
                }
            )
        },
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = {
            FloatingActionButton(onClick = { dialogState = DialogState.DISPLAY }) {
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

        NewNoteDialog(
            dialogState = dialogState,
            onAdd = {
                viewModel.addNote(it)
            },
            onDismiss = {
                dialogState = DialogState.HIDDEN
            }
        )
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

class NotesListActions(private val navHostController: NavHostController): Actions(navHostController) {
    fun openNoteDetails(noteId: Int) {
        navHostController.navigate(Screen.NoteDetails.withArgs("$noteId"))
    }
}















