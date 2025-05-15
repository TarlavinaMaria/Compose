package com.example.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose.ui.theme.ComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeTheme {
//                SimpleCalculator()
                SimpleTrafficLight()
            }
        }
    }
}

@Composable
fun SimpleCalculator() {
    var num1 by remember { mutableStateOf("") }
    var num2 by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("Введите числа") }

    LazyColumn(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        item {
            TextField(
                value = num1,
                onValueChange = { num1 = it },
                label = { Text("Число 1") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
        }

        item {
            TextField(
                value = num2,
                onValueChange = { num2 = it },
                label = { Text("Число 2") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
        }

        item {
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Button(onClick = {
                    result = (num1.toDouble() + num2.toDouble()).toString()
                }) { Text("+") }

                Button(onClick = {
                    result = (num1.toDouble() - num2.toDouble()).toString()
                }) { Text("-") }

                Button(onClick = {
                    result = (num1.toDouble() * num2.toDouble()).toString()
                }) { Text("×") }

                Button(onClick = {
                    result = (num1.toDouble() / num2.toDouble()).toString()
                }) { Text("÷") }
            }
        }

        item {
            Text("Результат: $result", style = MaterialTheme.typography.headlineSmall)
        }
    }
}

@Composable
fun SimpleTrafficLight() {
    var light by remember { mutableStateOf("Красный") }

    LazyColumn(
        modifier = Modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .background(
                        when (light) {
                            "Красный" -> Color.Red
                            "Жёлтый" -> Color.Yellow
                            else -> Color.Green
                        }
                    )
                    .clip(CircleShape)
            )
        }

        item {
            Button(onClick = {
                light = when (light) {
                    "Красный" -> "Жёлтый"
                    "Жёлтый" -> "Зелёный"
                    else -> "Красный"
                }
            }) {
                Text("Переключить")
            }
        }

        item {
            Text("Текущий: $light", style = MaterialTheme.typography.bodyLarge)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ComposeTheme {
        SimpleCalculator()
//        SimpleTrafficLight()
    }
}