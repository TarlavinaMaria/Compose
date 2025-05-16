package com.example.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    AppScreens()
                }
            }
        }
    }
}

@Composable
fun AppScreens() {
    var showDetails by remember { mutableStateOf(false) }
    var showColorPicker by remember { mutableStateOf(false) }
    var userName by remember { mutableStateOf("") }
    var selectedColor by remember { mutableStateOf<Color?>(null) }

    when {
        showColorPicker -> {
            ColorPickerScreen(
                onColorSelected = { color ->
                    selectedColor = color
                    showColorPicker = false
                },
                onBack = { showColorPicker = false }
            )
        }

        showDetails -> {
            DetailScreen(
                userName = userName,
                onBack = { showDetails = false }
            )
        }

        else -> {
            HomeScreen(
                userName = userName,
                selectedColor = selectedColor,
                onUserNameChange = { userName = it },
                onNavigateToDetails = { showDetails = true },
                onPickColor = { showColorPicker = true }
            )
        }
    }
}

@Composable
fun HomeScreen(
    userName: String,
    selectedColor: Color?,
    onUserNameChange: (String) -> Unit,
    onNavigateToDetails: () -> Unit,
    onPickColor: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Показать выбранный цвет (если есть)
        selectedColor?.let { color ->
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .background(color)
                    .padding(8.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
        }

        OutlinedTextField(
            value = userName,
            onValueChange = onUserNameChange,
            label = { Text("Enter your name") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = onNavigateToDetails,
            enabled = userName.isNotBlank()
        ) {
            Text("Go to Details")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = onPickColor) {
            Text("Pick Color")
        }
    }
}

@Composable
fun DetailScreen(
    userName: String,
    onBack: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Welcome, $userName!",
            style = MaterialTheme.typography.headlineMedium
        )

        Button(onClick = onBack) {
            Text("Go Back")
        }
    }
}

@Composable
fun ColorPickerScreen(
    onColorSelected: (Color) -> Unit,
    onBack: () -> Unit
) {
    val colors = listOf(
        Color.Red to "Red",
        Color.Green to "Green",
        Color.Blue to "Blue",
        Color.Yellow to "Yellow",
        Color.Cyan to "Cyan"
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Select a Color",
            style = MaterialTheme.typography.headlineSmall
        )

        Spacer(modifier = Modifier.height(24.dp))

        Column {
            colors.forEach { (color, name) ->
                Button(
                    onClick = { onColorSelected(color) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = color,
                        contentColor = Color.White
                    )
                ) {
                    Text(name)
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(onClick = onBack) {
            Text("Cancel")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewApp() {
    MaterialTheme {
        AppScreens()
    }
}