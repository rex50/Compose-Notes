package com.rex50.notes.ui.notes

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rex50.notes.data.enums.ErrorType
import com.rex50.notes.data.model.Data
import com.rex50.notes.data.model.Note
import com.rex50.notes.data.model.Result
import com.rex50.notes.domain.notes.NotesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class NotesListViewModel
@Inject
constructor(
    private val notesRepository: NotesRepository,
    private val savedStateHandle: SavedStateHandle,
): ViewModel() {

    private val mutableNotes: MutableState<Data<List<Note>>> = mutableStateOf(Data.Loading)
    val notes: State<Data<List<Note>>> = mutableNotes

    private val mutableAddStatus: MutableState<Data<Boolean>> = mutableStateOf(Data.Ready(false))
    val addStatus: State<Data<Boolean>> = mutableAddStatus

    init {
        getAllNotes()
    }

    private fun getAllNotes() {
        viewModelScope.launch {
            mutableNotes.value = Data.Loading
            when(val result = notesRepository.getAllNotes()) {
                is Result.Success -> {
                    mutableNotes.value = Data.Ready(result.data)
                }

                is Result.Failure -> {
                    mutableNotes.value = Data.Error(result.exception.message ?: "", result.errorType)
                }
            }
        }
    }

    fun addNote(note: String) {
        viewModelScope.launch {
            mutableAddStatus.value = Data.Loading
            when(val result = notesRepository.addNote(note)) {
                is Result.Success -> {
                    mutableAddStatus.value = Data.Ready(true)
                    getAllNotes()
                }

                is Result.Failure -> {
                    mutableAddStatus.value = Data.Error(result.exception.message ?: "", result.errorType)
                }
            }
        }
    }

    fun deleteNote(note: Note) {
        viewModelScope.launch {
            when(val result = notesRepository.deleteNote(note)) {
                is Result.Success -> {
                    Log.i(TAG, "deleteNote success: ${result.data}")
                }

                is Result.Failure -> {
                    Log.i(TAG, "deleteNote failure: ${note.id}")
                }
            }
        }
    }

    companion object {
        private const val TAG = "NotesViewModel"
    }

}