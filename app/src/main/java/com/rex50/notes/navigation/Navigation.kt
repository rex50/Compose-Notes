package com.rex50.notes.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.rex50.notes.extensions.getIntArgOrNull
import com.rex50.notes.ui.NoteDetailsActions
import com.rex50.notes.ui.NoteDetailsScreen
import com.rex50.notes.ui.NotesListActions
import com.rex50.notes.ui.NotesListScreen
import com.rex50.notes.utils.Args

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.Notes.route) {
        composable(route = Screen.Notes.route) {
            val notesListActions = NotesListActions(navController)
            NotesListScreen(actions = notesListActions)
        }
        composable(
            route = Screen.NoteDetails.fullPathWithRequiredArgs,
            arguments = Screen.NoteDetails.args
        ) { entry ->
            val noteDetailsActions = NoteDetailsActions(navController)
            NoteDetailsScreen(
                noteId = entry.getIntArgOrNull(Args.ID),
                actions = noteDetailsActions
            )
        }
    }
}










