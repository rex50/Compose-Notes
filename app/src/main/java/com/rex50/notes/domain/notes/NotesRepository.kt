package com.rex50.notes.domain.notes

import com.rex50.notes.data.model.Note
import com.rex50.notes.data.model.Result

interface NotesRepository {
    suspend fun getAllNotes(token: String): Result<List<Note>>
    suspend fun addNote(token: String, note: String): Result<String>
}