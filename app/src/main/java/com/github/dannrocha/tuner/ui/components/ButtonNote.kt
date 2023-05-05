package com.github.dannrocha.tuner.ui.components

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.dannrocha.tuner.ui.theme.TunerTheme

@Composable
fun ButtonNote(
    note: String,
    selected: Boolean = false,
    modifier: Modifier = Modifier,
    onClick: () -> Unit =  {}
) {
    Text(

        modifier = modifier
            .width(40.dp)
            .clip(shape = MaterialTheme.shapes.medium)
            .clickable(
                enabled = !selected,
                onClick = onClick,
            ),
        text = note,
        textAlign = TextAlign.Center,
        fontSize = if(selected) 56.sp else 32.sp,
        color = if(selected)
            Color(0xFFB7B6F9)
        else
            Color(0xA3FFFFFF),
    )
}

@Composable
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
fun ButtonNotePreview() {
    TunerTheme {
        Surface {
            Row {
                ButtonNote(note = "E")
                ButtonNote(note = "A", selected = true)
                ButtonNote(note = "D")
            }
        }
    }
}