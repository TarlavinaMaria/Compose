package com.example.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

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
    val (showDetails, setShowDetails) = remember { mutableStateOf(false) }
    val (userName, setUserName) = remember { mutableStateOf("") }

    if (showDetails) {
        DetailScreen(
            userName = userName,
            onBack = { setShowDetails(false) }
        )
    } else {
        HomeScreen(
            userName = userName,
            onUserNameChange = setUserName,
            onNavigateToDetails = { setShowDetails(true) }
        )
    }
}

@Composable
fun HomeScreen(
    userName: String,
    onUserNameChange: (String) -> Unit,
    onNavigateToDetails: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Home Screen")

        OutlinedTextField(
            value = userName,
            onValueChange = onUserNameChange,
            label = { Text("Enter your name") }
        )

        Button(
            onClick = onNavigateToDetails,
            enabled = userName.isNotBlank()
        ) {
            Text("Go to Details")
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

@Preview(showBackground = true)
@Composable
fun PreviewApp() {
    MaterialTheme {
        AppScreens()
    }
}