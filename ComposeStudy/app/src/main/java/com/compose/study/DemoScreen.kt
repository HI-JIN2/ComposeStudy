package com.compose.study

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Slider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

class DemoScreen : ComponentActivity() {

    @Composable
    fun Demo2Screen() {
        var sliderPosition by remember { mutableStateOf(20f) }


    }

    @Composable
    fun DemoSlider(sliderPositions: Float, onPositionChange: (Float) -> Unit) {
        Slider(
            modifier = Modifier.padding(10.dp),
            valueRange = 20f..40f,
            value = sliderPositions,
            onValueChange = onPositionChange
        )
    }
}