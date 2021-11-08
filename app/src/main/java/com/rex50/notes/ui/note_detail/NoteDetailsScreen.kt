package com.rex50.notes.ui.note_detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.rex50.notes.base.Actions

@Composable
fun NoteDetailsScreen(
    noteId: Int?,
    actions: NoteDetailsActions
) {

    IconButton(
        onClick = { actions.onUpPress() },
        modifier = Modifier.padding(8.dp)
    ) {
        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "back")
    }
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(text = "Show note details of $noteId")
    }
}

class NoteDetailsActions(navHostController: NavHostController): Actions(navHostController)






