package com.github.dannrocha.tuner

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.github.dannrocha.tuner.ui.components.Range
import com.github.dannrocha.tuner.ui.components.GuitarTuner
import com.github.dannrocha.tuner.ui.presentation.TunerPresentation
import com.github.dannrocha.tuner.ui.theme.TunerTheme

// https://www.figma.com/file/ZoN7fQ00wyyJi3K0ed8MsH/Stay-Tuned!-(Community)?node-id=115-51&t=sLOWRVbjVPEp5ZCo-0

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TunerTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TunerPresentation()
                }
            }
        }
    }
}

