package com.rex50.notes.navigation

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.rex50.notes.utils.Args

sealed class Screen(val route: String, val args: List<NamedNavArgument> = listOf()) {
    object Notes: Screen("noteListScreen")
    object NoteDetails: Screen("noteDetailsScreen", listOf(
        navArgument(Args.NOTE) {
            type = NavType.StringType
            nullable = true
            defaultValue = null
        }
    ))


    //----------- Helper functions -----------------
    val fullPathWithRequiredArgs: String
        get() = buildString {
            append(route)
            args.forEach {
                append("/{${it.name}}")
            }
        }

    fun withArgs(vararg args:String): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}