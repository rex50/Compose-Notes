package com.rex50.notes.data.network

import com.rex50.notes.data.model.AllNotesResponse
import com.rex50.notes.data.model.Note
import com.rex50.notes.data.model.NoteRequest
import com.rex50.notes.data.model.Response
import retrofit2.http.*

interface NotesService {

    @GET("/note/all")
    suspend fun requestAllNotes(
        @Header("Authorization") token: String
    ): Response<AllNotesResponse>

    @POST("/note")
    suspend fun addNote(
        @Header("Authorization") token: String,
        @Body noteRequest: NoteRequest
    ): Response<String>

    @DELETE("/note/{noteId}")
    suspend fun deleteNote(
        @Header("Authorization") token: String,
        @Path("noteId") noteId: Int
    ): Response<Int>

    @PUT("/note/{noteId}")
    suspend fun updateNote(
        @Header("Authorization") token: String,
        @Path("noteId") noteId: Int,
        @Body noteRequest: NoteRequest
    ): Response<NoteRequest>
}