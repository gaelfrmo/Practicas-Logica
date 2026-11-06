package com.example.state.model

enum class Pantalla {
    INICIO,
    QUIZ,
    RESULTADO
}

data class Pregunta(
    val texto: String,
    val opciones: List<String>,
    val correcta: Int
)