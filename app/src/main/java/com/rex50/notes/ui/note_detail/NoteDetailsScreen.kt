package com.rex50.notes.ui.note_detail

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.rex50.notes.base.Actions
import com.rex50.notes.data.model.Note
import com.rex50.notes.extensions.toast
import com.rex50.notes.utils.Args.EDIT_SUCCESS

@ExperimentalComposeUiApi
@Composable
fun NoteDetailsScreen(
    note: Note?,
    actions: NoteDetailsActions,
    viewModel: NoteDetailsViewModel
) {

    val context = LocalContext.current
    val localFocusManager = LocalFocusManager.current
    val focusRequester = remember { FocusRequester() }

    var updatedNote by remember {
        mutableStateOf(note?.note ?: "")
    }

    var tempNote by remember {
        mutableStateOf(TextFieldValue(
            updatedNote,
            TextRange(updatedNote.length)
        ))
    }

    var editMode by remember {
        mutableStateOf(false)
    }

    fun handleBack() {
        if(editMode) {
            tempNote = TextFieldValue(
                updatedNote,
                TextRange(updatedNote.length)
            )
            editMode = !editMode
            localFocusManager.clearFocus()
        } else {
            actions.onUpPress()
        }
    }

    BackHandler {
        handleBack()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = if(editMode) "Edit note" else "View note")
                },
                navigationIcon = {
                    IconButton(
                        onClick = { handleBack() },
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Go back"
                        )
                    }
                }
            )
        },
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = {
            FloatingActionButton(onClick = {
                note?.let {
                    if(editMode) {
                        viewModel.updateNote(Note(it.id, tempNote.text))
                        updatedNote = tempNote.text
                        context.toast("Saved successfully")
                        actions.onEditSuccess()
                        localFocusManager.clearFocus()
                    } else {
                        focusRequester.requestFocus()
                    }
                    editMode = !editMode
                }
            }) {
                Icon(
                    imageVector = if(editMode) Icons.Default.Check else Icons.Default.Edit,
                    contentDescription = "Save changes"
                )
            }
        }
    ) {

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(16.dp)
        ) {

            TextField(
                value = tempNote,
                onValueChange = {
                    tempNote = it
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .focusRequester(focusRequester)
            )

            if(!editMode) {
                // Show an empty box for not allowing click on TextField
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .clickable(true) {}
                )
            }

        }

    }
}

class NoteDetailsActions(private val navHostController: NavHostController): Actions(navHostController) {
    fun onEditSuccess() {
        navHostController.previousBackStackEntry?.savedStateHandle?.set(EDIT_SUCCESS, true)
    }
}






