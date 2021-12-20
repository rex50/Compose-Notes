package com.rex50.notes.ui.note_detail

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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.rex50.notes.base.Actions
import com.rex50.notes.data.model.Note
import com.rex50.notes.extensions.toast

@ExperimentalComposeUiApi
@Composable
fun NoteDetailsScreen(
    note: Note?,
    actions: NoteDetailsActions
) {

    val context = LocalContext.current
    val keyboard = LocalSoftwareKeyboardController.current

    var noteString by remember {
        mutableStateOf(note?.note ?: "")
    }

    var editMode by remember {
        mutableStateOf(false)
    }


    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = if(editMode) "Edit note" else "View note")
                },
                navigationIcon = {
                    IconButton(
                        onClick = { actions.onUpPress() },
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
                if(editMode) {
                    //TODO: update note
                    context.toast("Saved successfully")
                    keyboard?.hide()
                }
                editMode = !editMode
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
                value = noteString,
                readOnly = !editMode,
                onValueChange = {
                    noteString = it
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
            )

        }

    }
}

class NoteDetailsActions(navHostController: NavHostController): Actions(navHostController)






