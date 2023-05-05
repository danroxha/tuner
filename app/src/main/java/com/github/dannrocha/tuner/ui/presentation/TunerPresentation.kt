package com.github.dannrocha.tuner.ui.presentation

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.outlined.LockOpen
import androidx.compose.material.icons.outlined.MusicNote
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.github.dannrocha.tuner.ui.components.GuitarTuner
import com.github.dannrocha.tuner.ui.components.Range
import com.github.dannrocha.tuner.ui.theme.TunerTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TunerPresentation() {
    val colorOn = Color(0xFFB7B6F9)
    val colorOff = Color(0xFF1C1D22)

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
            ) {
                Button(
                    onClick = { /*TODO*/ },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorOn
                    ),
                ) {
                    Icon(imageVector = Icons.Outlined.MusicNote, contentDescription = null)
                    Text(text = "Electric Guitar")
                    Icon(imageVector = Icons.Filled.ArrowDropDown, contentDescription = null)
                }
            }
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { /*TODO*/ },
                containerColor = colorOn,
                contentColor = colorOff,
            ) {
                Icon(imageVector = Icons.Outlined.LockOpen, contentDescription = null)
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            verticalArrangement = Arrangement.Center,
        ) {
            GuitarTuner(
                range = Range(138.64f, 155.82f),
                currentFrequency = 150.83f,
                details = "High (D#)"
            )
        }
    }
}

@Composable
@Preview(showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
fun TunerPresentationPreview() {
    TunerTheme {
        TunerPresentation()
    }
}