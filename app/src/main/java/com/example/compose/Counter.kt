package com.example.compose

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
    // Устанавливаем кодировку UTF-8
    System.setOut(java.io.PrintStream(System.out, true, "UTF-8"))

    runBlocking {
        val counter = MutableStateFlow(0)

        // Запускаем корутину для увеличения счётчика
        launch {
            while (true) {
                delay(1000)
                counter.value += 1
            }
        }

        // Подписываемся на изменения счётчика
        launch {
            counter.collect { value ->
                println("Счётчик: $value")
            }
        }

    }
}