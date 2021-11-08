package com.rex50.notes.domain.notes

import com.rex50.notes.data.model.Note

interface NotesRepository {
    suspend fun getAllNotes(token: String): List<Note>
}