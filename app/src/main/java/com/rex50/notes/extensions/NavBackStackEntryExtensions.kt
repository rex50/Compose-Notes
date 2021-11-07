package com.rex50.notes.extensions

import androidx.navigation.NavBackStackEntry

fun NavBackStackEntry.getIntArgOrNull(name: String): Int? = arguments?.getInt(name)