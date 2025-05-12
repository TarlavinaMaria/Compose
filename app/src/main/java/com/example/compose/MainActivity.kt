package com.example.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.compose.ui.theme.ComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                            .padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        GreetingText(name = "Мария")
                        TruncatedDescription(description = "Это очень длинное описание, которое должно быть обрезано после трёх строк. Если текст не помещается в три строки, то в конце должно появиться троеточие...")
                        StyledTexts()
                    }
                }
            }
        }
    }
}

@Composable
fun GreetingText(name: String) {
    Text(
        text = "Привет, $name!",
        color = Color.Blue,
        fontSize = 22.sp
    )
}

@Composable
fun TruncatedDescription(description: String) {
    Text(
        text = description,
        maxLines = 3,
        overflow = TextOverflow.Ellipsis,
        modifier = Modifier.padding(8.dp)
    )
}

@Composable
fun StyledTexts() {
    Column {
        Text(
            text = "Это жирный текст",
            color = Color.Red,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = "Это курсивный текст",
            color = Color.Green,
            fontSize = 18.sp,
            fontStyle = FontStyle.Italic
        )

        Text(
            text = "Это подчёркнутый текст",
            color = Color.Magenta,
            fontSize = 20.sp,
            textDecoration = TextDecoration.Underline
        )

        Text(
            text = "Это обычный текст с тенью",
            color = Color.DarkGray,
            fontSize = 16.sp,
            style = TextStyle(
                shadow = Shadow(
                    color = Color.LightGray,
                    offset = Offset(2f, 2f),
                    blurRadius = 4f
                )
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ComposeTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                GreetingText(name = "Мария")
                TruncatedDescription(description = "Это очень длинное описание, которое должно быть обрезано после трёх строк. Если текст не помещается в три строки, то в конце должно появиться троеточие...")
                StyledTexts()
            }
        }
    }
}