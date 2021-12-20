package com.rex50.notes.ui.notes

import android.net.Uri
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.google.gson.Gson
import com.rex50.notes.base.Actions
import com.rex50.notes.data.model.Data
import com.rex50.notes.data.model.Note
import com.rex50.notes.enums.DialogState
import com.rex50.notes.extensions.toast
import com.rex50.notes.navigation.Screen
import com.rex50.notes.ui.components.AnimatedSwipeDismiss
import com.rex50.notes.ui.components.BoxWithIcon
import com.rex50.notes.ui.components.NewNoteDialog
import com.rex50.notes.ui.components.NoteCard

@ExperimentalAnimationApi
@ExperimentalMaterialApi
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
                    notes = result.data.asReversed(),
                    onNoteClicked = {
                        actions.openNoteDetails(it)
                    },
                    onDeleteNote = {
                        viewModel.deleteNote(it)
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

@ExperimentalAnimationApi
@ExperimentalMaterialApi
@Composable
fun NotesList(
    notes: List<Note>,
    modifier: Modifier = Modifier,
    onDeleteNote: ((Note) -> Unit)? = null,
    onNoteClicked: ((Note) -> Unit)? = null,
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
            AnimatedSwipeDismiss(
                item = notes[index],
                background = {
                    BoxWithIcon(
                        icon = Icons.Default.Delete,
                        contentDescription = "Delete",
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 20.dp, vertical = 10.dp)
                            .background(Color.Red, RoundedCornerShape(8.dp))
                    )
                },
                content = {
                    NoteCard(
                        note = notes[index],
                        onClick = {
                            onNoteClicked?.invoke(notes[index])
                        }
                    )
                },
                onDismiss = {
                    //TODO: delete note using API
                    onDeleteNote?.invoke(notes[index])
                })
        }
    }
}

class NotesListActions(private val navHostController: NavHostController): Actions(navHostController) {
    fun openNoteDetails(note: Note) {
        val json = Uri.encode(Gson().toJson(note))
        navHostController.navigate(Screen.NoteDetails.withArgs(json))
    }
}















