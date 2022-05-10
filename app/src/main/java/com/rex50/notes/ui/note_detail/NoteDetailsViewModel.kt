package com.rex50.notes.ui.note_detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rex50.notes.data.model.Note
import com.rex50.notes.domain.notes.NotesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteDetailsViewModel
@Inject
constructor(
    private val notesRepository: NotesRepository,
    private val savedStateHandle: SavedStateHandle,
): ViewModel() {

    fun updateNote(note: Note) {
        viewModelScope.launch {
            notesRepository.updateNote(note = note)
        }
    }

}