package com.compose.study

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Divider
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview

class TextScreen : ComponentActivity() {

    @Composable
    fun CustomText(text: String, fontWeight: FontWeight, color: Color) {
        Text(
            text = text,
            fontWeight = fontWeight,
            color = color
        )
    }

    @Composable
    fun CustomSwitch() {
        val checked = remember { mutableStateOf(true) }

        Column {
            Switch(
                checked = checked.value,
                onCheckedChange = { checked.value = it }
            )
            if (checked.value){
                Text("On")
            } else{
                Text("Off")
            }
        }
    }

    @Composable
    fun CustomList(items: List<String>){
        Column {
            for (item in items){
                Text(item)
                Divider(color = Color.LightGray)
            }
        }
    }

    @Preview
    @Composable
    fun DefaultPreview() {
//        CustomText(text = "helloCompose", fontWeight = FontWeight.Bold, color = Color.Cyan)
//        CustomSwitch()
        CustomList(listOf("one","two","three","for"))
    }

}