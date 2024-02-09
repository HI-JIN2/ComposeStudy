package com.compose.study

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.compose.study.ui.theme.ComposeStudyTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeStudyTheme {

                val cnt = remember { mutableStateOf(0) }
                val messageList = remember { mutableStateListOf<Message>() }

                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    Column() {

                        Greeting("Android", cnt.value, onClick = {
                            Log.d("gretting", "클릭죔")
                            cnt.value = cnt.value + 1
                            val newMsg = Message(id = cnt.value, content = "메시지입니다. ${cnt.value}")
                            messageList.add(newMsg)
                        })
                        MessageList(messages = messageList, onDeleteClicked = {
                            Log.d("TAG", "onDeleteClicked: ${it.id}")
                            messageList.remove(it)
                        })

                    }


                }
            }
        }
    }
}

@Composable
fun MessageList(messages: List<Message>, onDeleteClicked: (Message) -> Unit) {
    LazyColumn {
        items(messages) { message ->
            MessageRow(message, onDeleteClicked)
        }
    }
}

@Composable
fun MessageRow(meg: Message, onDeleteClicked: (Message) -> Unit) {
    Surface(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(1.dp, Color.LightGray),
        shadowElevation = 10.dp
    ) {

        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Text(text = "${meg.id} / ${meg.content}")
            Button(onClick = { onDeleteClicked(meg) }) {
                Text("삭제")
            }
        }
    }


}

@Composable
fun Greeting(name: String, cnt: Int, onClick: () -> Unit) {
    Column() {
        Text(
            text = "Hello $name!",
//            modifier = modifier
        )
        Text(text = "클릭한 카운트 $cnt")
        Button(onClick) {
            Text("클릭해주세요")
        }
    }

}
//
//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    ComposeStudyTheme {
//        Greeting("Android")
//    }
//}