package com.rex50.notes.domain.notes

import com.rex50.notes.data.model.Note
import com.rex50.notes.data.network.NotesService

class NotesRepositoryImpl(
    private val notesService: NotesService
): NotesRepository {
    override suspend fun getAllNotes(token: String): List<Note> {
        return notesService.requestAllNotes(token).data.notes
    }
}