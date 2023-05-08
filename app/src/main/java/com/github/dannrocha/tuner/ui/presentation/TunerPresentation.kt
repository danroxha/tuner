package com.github.dannrocha.tuner.ui.presentation

import android.content.Intent
import android.content.res.Configuration
import android.net.Uri
import android.os.Bundle
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.SwapHoriz
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.LockOpen
import androidx.compose.material.icons.outlined.UnfoldLess
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SheetState
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.github.dannrocha.tuner.R
import com.github.dannrocha.tuner.ui.components.GuitarTuner
import com.github.dannrocha.tuner.ui.components.Range
import com.github.dannrocha.tuner.ui.theme.TunerTheme
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TunerPresentation() {
    val colorOn = Color(0xFFB7B6F9)
    val colorOff = Color(0xFF1C1D22)

    val scope = rememberCoroutineScope()

    val scaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = SheetState(
            skipPartiallyExpanded = true,
            initialValue = SheetValue.Hidden,
        ),
    )
    val lockButtonState = rememberSaveable { mutableStateOf(true) }
    val context = LocalContext.current

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
            ) {
                Button(
                    onClick = {
                        scope.launch {
                            when(scaffoldState.bottomSheetState.isVisible) {
                                true -> scaffoldState.bottomSheetState.hide()
                                else -> scaffoldState.bottomSheetState.show()
                            }
                        }
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorOn
                    ),
                    modifier = Modifier.width(250.dp)
                ) {
                    Row (
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ){
                        Icon(
                            painter = painterResource(id = R.drawable.electric_guitar),
                            contentDescription = null,
                            modifier = Modifier.width(20.dp)
                        )
                        Text(text = "Electric Guitar")
                        Icon(
                            imageVector = Icons.Filled.SwapHoriz,
                            contentDescription = null,
                            modifier = Modifier
                                .width(20.dp)
                                .rotate(90f)
                        )

                    }
                }
            }
        },
        floatingActionButton = {
            if(!scaffoldState.bottomSheetState.isVisible) {
                FloatingActionButton(
                    onClick = {
                          lockButtonState.value = !lockButtonState.value
                    },
                    containerColor = colorOn,
                    contentColor = colorOff,
                ) {
                    Icon(
                        imageVector = if(lockButtonState.value) Icons.Outlined.Lock else Icons.Outlined.LockOpen,
                        contentDescription = null
                    )
                }
            }
        }
    ) { paddingValues ->

        BottomSheetScaffold(
            sheetContainerColor = colorOn,
            sheetContentColor = colorOff,
            scaffoldState = scaffoldState,
            sheetPeekHeight = 128.dp,
            sheetDragHandle = {
                BottomSheetDefaults.DragHandle(color = colorOff)
            },
            sheetContent = {
                LazyColumn(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    item {
                        Column(
                        ) {
                            InstrumentRow(
                                selected = true,
                                name = "Electric Guitar",
                                image = R.drawable.electric_guitar
                            )

                            InstrumentRow(
                                name = "Acoustic Guitar",
                                image = R.drawable.acoustic_guitar,
                                selected = false
                            )

                            InstrumentRow(
                                name = "Ukulele",
                                image = R.drawable.ukulele,
                                selected = false
                            )

                            InstrumentRow(
                                name = "Violin",
                                image = R.drawable.violin,
                                selected = false
                            )

                            InstrumentRow(
                                name = "Piano",
                                image = R.drawable.piano,
                                selected = false
                            )


                            GithubSocialMedia {
                                val intent = Intent(Intent.ACTION_VIEW)
                                intent.data = Uri.parse("https://github.com/dannRocha/tuner")
                                ContextCompat.startActivity(context, intent, Bundle())
                            }

                        }
                    }
                }
            }
        ) { _ ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                verticalArrangement = Arrangement.Center,
            ) {
                GuitarTuner(
                    range = Range(138.64f, 155.82f),
                    currentFrequency = 150.83f,
                    details = "High (D#)",
                )
            }
        }
    }
}

@Composable
private fun GithubSocialMedia(
    onClick: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .clickable(onClick = onClick)
            .fillMaxWidth()
            .padding(vertical = 10.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(text = "by Daniel @dannRocha")
        Spacer(modifier = Modifier.width(4.dp))
        Icon(
            painter = painterResource(id = R.drawable.github),
            contentDescription = "github icon",
            modifier = Modifier
                .width(30.dp),
            tint = Color(0xFF1C1D22)
        )
    }
}

@Composable
private fun InstrumentRow(
    name: String, image: Int,
    selected: Boolean = false,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Row(
        modifier = modifier
            .fillMaxWidth()

            .background(color = if (selected) Color(0x1F000000) else Color.Transparent)
            .clickable(
                enabled = !selected,
                onClick = onClick
            )
            .padding(horizontal = 18.dp, vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(text = name)
        Icon(
            painter = painterResource(image),
            contentDescription = "is a $name selector",
            modifier = Modifier
                .width(60.dp),
            tint = Color(0xFF1C1D22)
        )
    }
}

@Composable
@Preview(showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
fun TunerPresentationPreview() {
    TunerTheme {
        TunerPresentation()
    }
}