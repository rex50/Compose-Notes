package com.rex50.notes

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.rex50.notes.ui.NoteDetailsScreen
import com.rex50.notes.ui.NotesListScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.NotesListScreen.route) {
        composable(route = Screen.NotesListScreen.route) {
            NotesListScreen(navController = navController)
        }
        composable(
            route = Screen.NoteDetailsScreen.route + "/{id}",
            arguments = listOf(
                navArgument("id") {
                    type = NavType.IntType
                    defaultValue = -1
                }
            )
        ) { entry ->
            NoteDetailsScreen(
                noteId = entry.arguments?.getInt("id")
            ) {
                navController.navigateUp()
            }
        }
    }
}










