package com.github.dannrocha.tuner.ui.components

import android.content.res.Configuration
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.coerceIn
import androidx.compose.ui.unit.dp
import com.github.dannrocha.tuner.ui.theme.TunerTheme
import kotlin.math.roundToInt

data class BarTuner(
    val height: Dp,
    val width: Dp,
    val x: Float,
    val y: Float,
    val color: Color
)

data class Range(
    val start: Float,
    val end: Float
)

@OptIn(ExperimentalTextApi::class)
@Composable
fun StatusBarTuner(
    modifier: Modifier = Modifier,
    range: Range =  Range(start = 1f, end = 100f),
    currentFrequency: Float = 50f,
    populate: Float = 0.4f,
    barWidth: Dp = 5.dp,
    enabledCornerRadius: Boolean = false,

    ) {
    val colorOn = Color(0xFFB7B6F9)
    val colorOff = Color(0xA3FFFFFF)

    var _populate = populate

    var _currentFrequency = currentFrequency.coerceIn(minimumValue = range.start, maximumValue = range.end)

    if((_populate * 100f).toInt() % 5 != 0) {
        _populate += (_populate * 100f).toInt() % 5
    }
    val _barWidth = barWidth.coerceIn(minimumValue = 5.dp, maximumValue = 85.dp)
    val BASE_DIVIDER = 5

    Canvas(
        modifier = modifier
            .height(200.dp)
            .fillMaxWidth(),
        onDraw = {
            val defaultBar = BarTuner(width = _barWidth, height = size.toDpSize().height, x = 0f, y = 0f, color = Color(0xA3FFFFFF))

            var amountBar = ((size.width / defaultBar.width.toPx()) / (10 * (1 - _populate))).toInt()
            var complement = 0
            if(amountBar % BASE_DIVIDER != 0) {
                complement = amountBar % BASE_DIVIDER
                if(complement > 2) {
                    amountBar += BASE_DIVIDER - complement
                }
                else
                    amountBar -= BASE_DIVIDER - complement
            }

            amountBar = amountBar.coerceIn(minimumValue = BASE_DIVIDER, maximumValue = (size.width / defaultBar.width.toPx()).toInt())

            val spaceBetween = (size.width - (defaultBar.width.toPx().roundToInt() * amountBar)) / amountBar
            val barBreakpoint = if(_barWidth < 5.dp || amountBar == 5) 3 else 4

            val MIDDLE_PERCENT_DIVIDER = 0.7f
            val SMALL_PERCENT_DIVIDER = 0.35f

            for(position in 0..amountBar) {
                val isMiddle = position == ((amountBar / 2))
                val bar = position % barBreakpoint + 1

                var  currentBarHeight = if(isMiddle) size.height else when(bar) {
                    barBreakpoint -> size.height * MIDDLE_PERCENT_DIVIDER
                    else -> size.height * SMALL_PERCENT_DIVIDER
                }

                val amplitude = (range.end - range.start) / amountBar
                val sliceFrequency = amplitude * position + range.start

                val isBarSelected = _currentFrequency > sliceFrequency
                        && _currentFrequency < (sliceFrequency + amplitude)

                if(enabledCornerRadius) {
                    drawRoundRect(
                        color = if (isBarSelected) colorOn else colorOff,
                        size = Size(
                            width = defaultBar.width.toPx(),
                            height = currentBarHeight),
                        topLeft = Offset(
                            x = (position * defaultBar.width.toPx() + position * spaceBetween + spaceBetween / 2f),
                            y = (size.height - currentBarHeight) / 2
                        ),
                        cornerRadius = CornerRadius(20f, 20f),
                    )
                }
                else {
                    drawRect(
                        color = if (isBarSelected) colorOn else colorOff,
                        size = Size(
                            width = defaultBar.width.toPx(),
                            height = currentBarHeight),
                        topLeft = Offset(
                            x = (position * defaultBar.width.toPx() + position * spaceBetween + spaceBetween / 2f),
                            y = (size.height - currentBarHeight) / 2
                        )
                    )
                }
            }
        }
    )
}

@Composable
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
fun StatusBarTunerPreview() {
    TunerTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            StatusBarTuner(
                modifier = Modifier,
                currentFrequency = 150.83f,
                range = Range(138.64f, 155.82f),
                enabledCornerRadius = true
            )
        }
    }
}