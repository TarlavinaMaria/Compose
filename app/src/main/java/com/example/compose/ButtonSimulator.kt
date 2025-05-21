package com.example.compose

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
    // Настройка кодировки
    System.setOut(java.io.PrintStream(System.out, true, "UTF-8"))

    runBlocking {
        val clickFlow = MutableSharedFlow<Unit>()

        // Корутина для обработки кликов
        launch {
            clickFlow.collect {
                println("Кнопка была нажата!")
            }
        }

        // Корутина для ввода
        launch {
            while (true) {
                print("\nВведите 'click': ")
                val input = readLine()?.trim()

                when (input) {
                    "click" -> {
                        clickFlow.emit(Unit)
                    }

                    else -> {
                        println("Ошибка: ожидается 'click', получено: '${input ?: "пусто"}'")
                    }
                }
            }
        }
    }
}