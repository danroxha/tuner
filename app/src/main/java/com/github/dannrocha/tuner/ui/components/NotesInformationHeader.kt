package com.github.dannrocha.tuner.ui.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.github.dannrocha.tuner.ui.theme.TunerTheme

@Composable
fun NotesInformationHeader(
    details: String,
    currentFrequency: Float,
    modifier: Modifier = Modifier,
    strings: List<String> = listOf("E", "A", "D", "G", "B", "E")
) {
    Column(modifier = modifier
        .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center)
    {

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ){

            Row(modifier = Modifier
                .fillMaxWidth(0.8f),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                strings.forEach {
                    if(it == "D")
                        ButtonNote(note = it, selected = true)
                    else
                        ButtonNote(note = it)
                }
            }
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "$details",
                color = Color(0xFFB7B6F9),
                fontSize = 24.sp,
                fontWeight = FontWeight.Normal,
            )
            Text(text = "$currentFrequency Hz", fontSize = 18.sp, color = Color(0xA3FFFFFF))
        }
    }
}

@Composable
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
fun NotesInformationHeaderPreview() {
    TunerTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            NotesInformationHeader(
                currentFrequency = 150.83f,
                details = "In tune!"
            )
        }
    }
}