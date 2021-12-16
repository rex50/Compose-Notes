package com.rex50.notes.data.model

import java.io.Serializable

data class AllNotesResponse(
    val notes: List<Note>
): Serializable
