package com.rex50.notes.domain.notes

import com.rex50.notes.data.model.Note
import com.rex50.notes.data.model.Result

interface NotesRepository {
    suspend fun getAllNotes(): Result<List<Note>>
    suspend fun addNote(note: String): Result<String>
    suspend fun deleteNote(note: Note): Result<Int>
}