package com.rex50.notes.ui.notes

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rex50.notes.data.model.Note
import com.rex50.notes.domain.notes.NotesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class NotesListViewModel
@Inject
constructor(
    private val notesRepository: NotesRepository,
    @Named("auth_token") private val token: String,
    private val savedStateHandle: SavedStateHandle,
): ViewModel() {

    private val mNotes: MutableState<List<Note>> = mutableStateOf(arrayListOf())
    val notes: State<List<Note>> = mNotes

    init {
        viewModelScope.launch {
            val response = notesRepository.getAllNotes(token)
            mNotes.value = response
        }
    }

}