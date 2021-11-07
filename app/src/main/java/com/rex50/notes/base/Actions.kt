package com.rex50.notes.base

import androidx.navigation.NavHostController

abstract class Actions(private val navHostController: NavHostController) {
    fun onUpPress() {
        navHostController.navigateUp()
    }
}