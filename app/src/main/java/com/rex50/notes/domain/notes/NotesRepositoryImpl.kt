package com.rex50.notes.domain.notes

import android.util.Log
import com.rex50.notes.data.enums.ErrorType
import com.rex50.notes.data.model.Note
import com.rex50.notes.data.model.NoteRequest
import com.rex50.notes.data.model.Result
import com.rex50.notes.data.network.NotesService

class NotesRepositoryImpl(
    private val notesService: NotesService
): NotesRepository {
    override suspend fun getAllNotes(token: String): Result<List<Note>> {
        return try {
            val data = notesService.requestAllNotes(token).data
            data?.let {
                Result.Success(it.notes)
            } ?: Result.Failure(Exception("Problem while preparing notes"), ErrorType.PROCESSING)
        } catch (e: Exception) {
            Log.e("NotesRepository", "getAllNotes: ", e)
            Result.Failure(Exception("Problem while preparing notes"), ErrorType.PROCESSING)
        }
    }

    override suspend fun addNote(token: String, note: String): Result<String> {
        return try {
            val data = notesService.addNote(token, NoteRequest(note)).data
            data?.let {
                Result.Success(it)
            } ?: Result.Failure(Exception("Problem adding new note"), ErrorType.PROCESSING)
        } catch (e: Exception) {
            Log.e("NotesRepository", "getAllNotes: ", e)
            Result.Failure(Exception("Problem adding new note"), ErrorType.PROCESSING)
        }
    }
}