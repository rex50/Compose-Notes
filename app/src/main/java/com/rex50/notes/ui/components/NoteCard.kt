package com.rex50.notes.ui.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rex50.notes.data.model.Note


@Composable
fun NoteCard(
    note: Note,
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null
) {
    Card (
        shape = RoundedCornerShape(8.dp),
        elevation = 6.dp,
        modifier = modifier
            .fillMaxWidth()
            .height(90.dp)
            .padding(start = 16.dp, end = 16.dp, top = 6.dp, bottom = 10.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .clickable {
                    onClick?.invoke()
                }
        ) {
            val size = 8.dp
            Canvas(
                onDraw = {
                    drawCircle(
                        color = Color.Red,
                        radius = size.toPx() / 2f
                    )
                },
                modifier = Modifier
                    .size(size)
                    .padding(start = 24.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = note.note,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NoteCardPreview() {
    NoteCard(note = Note(-1, "My new light note"))
}

@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun NoteCardPreviewDark() {
    NoteCard(note = Note(-1, "My new dark note"))
}