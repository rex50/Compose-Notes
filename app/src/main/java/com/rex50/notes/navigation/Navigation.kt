package com.rex50.notes.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.gson.Gson
import com.rex50.notes.data.model.Note
import com.rex50.notes.extensions.getIntArgOrNull
import com.rex50.notes.ui.note_detail.NoteDetailsActions
import com.rex50.notes.ui.note_detail.NoteDetailsScreen
import com.rex50.notes.ui.notes.NotesListActions
import com.rex50.notes.ui.notes.NotesListScreen
import com.rex50.notes.utils.Args

@ExperimentalMaterialApi
@ExperimentalAnimationApi
@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.Notes.route) {
        composable(route = Screen.Notes.route) {
            val notesListActions = NotesListActions(navController)
            NotesListScreen(
                viewModel = hiltViewModel(),
                actions = notesListActions
            )
        }
        composable(
            route = Screen.NoteDetails.fullPathWithRequiredArgs,
            arguments = Screen.NoteDetails.args
        ) { entry ->
            val noteDetailsActions = NoteDetailsActions(navController)
            val note = entry.arguments?.getString(Args.NOTE)?.let {
                Gson().fromJson(it, Note::class.java)
            }
            NoteDetailsScreen(
                note = note,
                actions = noteDetailsActions
            )
        }
    }
}










