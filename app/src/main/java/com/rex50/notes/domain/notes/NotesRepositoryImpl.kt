package com.rex50.notes.domain.notes

import android.util.Log
import com.rex50.notes.data.enums.ErrorType
import com.rex50.notes.data.model.Note
import com.rex50.notes.data.model.NoteRequest
import com.rex50.notes.data.model.Result
import com.rex50.notes.data.network.NotesService
import com.rex50.notes.interfaces.providers.TokenProvider

class NotesRepositoryImpl(
    private val notesService: NotesService,
    private val tokenProvider: TokenProvider
): NotesRepository {
    override suspend fun getAllNotes(): Result<List<Note>> {
        return try {
            val data = notesService.requestAllNotes(tokenProvider.getToken()).data
            data?.let {
                Result.Success(it.notes)
            } ?: Result.Failure(Exception("Problem while preparing notes"), ErrorType.PROCESSING)
        } catch (e: Exception) {
            Log.e("NotesRepository", "getAllNotes: ", e)
            Result.Failure(Exception("Problem while preparing notes"), ErrorType.PROCESSING)
        }
    }

    override suspend fun addNote(note: String): Result<String> {
        return try {
            val data = notesService.addNote(tokenProvider.getToken(), NoteRequest(note)).data
            data?.let {
                Result.Success(it)
            } ?: Result.Failure(Exception("Problem adding new note"), ErrorType.PROCESSING)
        } catch (e: Exception) {
            Log.e("NotesRepository", "getAllNotes: ", e)
            Result.Failure(Exception("Problem adding new note"), ErrorType.PROCESSING)
        }
    }

    override suspend fun deleteNote(note: Note): Result<Int> {
        return try {
            val data = notesService.deleteNote(tokenProvider.getToken(), note.id).data
            data?.let {
                Result.Success(it)
            } ?: Result.Failure(Exception("Problem adding new note"), ErrorType.PROCESSING)
        } catch (e: Exception) {
            Log.e("NotesRepository", "getAllNotes: ", e)
            Result.Failure(Exception("Problem adding new note"), ErrorType.PROCESSING)
        }
    }


}