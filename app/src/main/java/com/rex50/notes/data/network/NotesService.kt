package com.rex50.notes.data.network

import com.rex50.notes.data.model.AllNotesResponse
import com.rex50.notes.data.model.Note
import com.rex50.notes.data.model.NoteRequest
import com.rex50.notes.data.model.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

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

}