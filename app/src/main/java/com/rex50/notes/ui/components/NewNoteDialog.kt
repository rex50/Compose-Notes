package com.rex50.notes.ui.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.rex50.notes.enums.DialogState

@Composable
fun NewNoteDialog(
    dialogState: DialogState,
    onAdd: (String) -> Unit,
    onDismiss: () -> Unit,
) {
    if (dialogState == DialogState.DISPLAY || dialogState == DialogState.LOADING) {
        var note by remember {
            mutableStateOf("")
        }
        Dialog(onDismissRequest = { onDismiss() }) {
            Card(
                elevation = 16.dp,
                shape = RoundedCornerShape(6.dp)
            ) {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                ) {
                    Text(
                        text = "New note",
                        style = MaterialTheme.typography.h5
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    TextField(
                        value = note,
                        onValueChange = { note = it },
                        label = {
                            Text(text = "Enter your note here...")
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(150.dp)
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    if(dialogState == DialogState.LOADING) {
                        CircularProgressIndicator(
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally)
                                .padding(6.dp)
                                .size(32.dp)
                        )
                    } else {
                        Row(
                            modifier = Modifier.align(Alignment.End)
                        ) {
                            Button(
                                colors = ButtonDefaults.outlinedButtonColors(contentColor = MaterialTheme.colors.primary),
                                elevation = ButtonDefaults.elevation(0.dp, 0.dp, 0.dp),
                                onClick = {
                                    onDismiss()
                                }
                            ) {
                                Text(text = "Cancel")
                            }
                            
                            Spacer(modifier = Modifier.width(8.dp))
                            
                            Button(
                                onClick = {
                                    // Change the state to close the dialog
                                    if(note.isNotEmpty())
                                        onAdd(note)
                                    else {
                                        //TODO: show error message
                                    }
                                },
                            ) {
                                Text("Add")
                            }
                        }
                    }
                }
            }
        }
    }
}