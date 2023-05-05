package com.github.dannrocha.tuner.ui.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.dannrocha.tuner.ui.theme.TunerTheme

@Composable
fun GuitarTuner(
    range: Range,
    currentFrequency: Float,
    modifier: Modifier = Modifier,
    details: String
) {
    Column(modifier = modifier
        .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        NotesInformationHeader(
            details = details,
            currentFrequency = currentFrequency,
            modifier = Modifier
                .padding(bottom = 28.dp)
        )

        Row {
            StatusBarTuner(
                currentFrequency = currentFrequency,
                range = range,
                enabledCornerRadius = true
            )
        }
    }
}

@Composable
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
fun GuitarTunerPreivew() {
    TunerTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background,

            ) {
            GuitarTuner(
                range = Range(138.64f, 155.82f),
                currentFrequency = 150.83f,
                details = "High (D#)")
        }
    }
}

@Composable
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
fun GuitarTunerPreivew2() {
    TunerTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            GuitarTuner(
                details = "In tune!",
                range = Range(138.64f, 155.82f),
                currentFrequency = 146.83f
            )
        }
    }
}
