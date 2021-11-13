package com.rex50.notes.domain.notes

import com.rex50.notes.data.model.Note
import com.rex50.notes.data.model.Result

interface NotesRepository {
    suspend fun getAllNotes(token: String): Result<List<Note>>
}