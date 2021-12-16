package com.rex50.notes.ui.components

import android.graphics.drawable.AdaptiveIconDrawable
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun BoxWithIcon(
    icon: ImageVector,
    modifier: Modifier = Modifier,
    contentDescription: String = ""
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.CenterEnd
    ) {
        Icon(
            imageVector = icon,
            contentDescription,
            tint = Color.White,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
    }
}