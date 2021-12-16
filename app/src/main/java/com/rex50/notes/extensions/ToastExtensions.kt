package com.rex50.notes.extensions

import android.content.Context
import android.widget.Toast

private var toast: Toast? = null

fun Context.toast(msg: String, length: Int = Toast.LENGTH_SHORT) {
    toast?.cancel()
    toast = Toast.makeText(this, msg, length)
    toast?.show()
}