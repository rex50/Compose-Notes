package com.rex50.notes.di

import com.rex50.notes.data.network.NotesService
import com.rex50.notes.domain.notes.NotesRepository
import com.rex50.notes.domain.notes.NotesRepositoryImpl
import com.rex50.notes.interfaces.providers.TokenProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Singleton
    @Provides
    fun provideNotesRepository(
        recipeService: NotesService,
        tokenProvider: TokenProvider
    ): NotesRepository{
        return NotesRepositoryImpl(
            notesService = recipeService,
            tokenProvider = tokenProvider
        )
    }
}