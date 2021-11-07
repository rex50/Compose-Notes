package com.rex50.notes

sealed class Screen(val route: String) {
    object NotesListScreen: Screen("noteListScreen")
    object NoteDetailsScreen: Screen("noteDetailsScreen")

    fun withArgs(vararg args:String): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}