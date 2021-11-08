package com.rex50.notes.data.network

import com.rex50.notes.data.model.AllNotesResponse
import com.rex50.notes.data.model.DataResponse
import retrofit2.http.GET
import retrofit2.http.Header

interface NotesService {

    @GET("/note/all")
    suspend fun requestAllNotes(
        @Header("Authorization") token: String
    ): DataResponse<AllNotesResponse>

}