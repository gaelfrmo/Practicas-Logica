package com.example.state.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.state.model.Pantalla
import com.example.state.model.Pregunta

class QuizViewModel : ViewModel() {

    val listaPreguntasQuiz = listOf(
        Pregunta("que tipo de dato conviene usar para guardar el valor 3.14?", listOf("Int", "Double", "String", "Boolean"), 1),
        Pregunta("cual es la extension de los archivos de codigo kotlin?", listOf(".java", ".kt", ".xml", ".js"), 1),
        Pregunta("que estructura se usa para repetir un bloque de codigo?", listOf("if-else", "for / while", "val / var", "fun"), 1),
        Pregunta("que palabra clave se usa para declarar una variable mutable en kotlin?", listOf("val", "var", "const", "static"), 1),
        Pregunta("que layout se usa para alinear elementos uno debajo del otro en compose?", listOf("Row", "Column", "Box", "Spacer"), 1)
    )

    var pantalla by mutableStateOf(Pantalla.INICIO)
        private set

    var nombre by mutableStateOf("")
        private set

    var indicePregunta by mutableIntStateOf(0)
        private set

    var seleccionada by mutableStateOf<Int?>(null)
        private set

    var respondida by mutableStateOf(false)
        private set

    var puntaje by mutableIntStateOf(0)
        private set

    val preguntaActual: Pregunta
        get() = listaPreguntasQuiz[indicePregunta]

    fun onNombreChange(nuevoNombre: String) {
        nombre = nuevoNombre
    }

    fun comenzarQuiz() {
        if (nombre.trim().length >= 2) {
            pantalla = Pantalla.QUIZ
        }
    }

    fun seleccionarOpcion(indice: Int) {
        seleccionada = indice
    }

    fun confirmarRespuesta() {
        if (seleccionada != null) {
            respondida = true
            if (seleccionada == preguntaActual.correcta) {
                puntaje++
            }
        }
    }

    fun siguientePregunta() {
        if (indicePregunta < listaPreguntasQuiz.size - 1) {
            indicePregunta++
            seleccionada = null
            respondida = false
        } else {
            pantalla = Pantalla.RESULTADO
        }
    }

    fun reintentar() {
        indicePregunta = 0
        puntaje = 0
        seleccionada = null
        respondida = false
        pantalla = Pantalla.QUIZ
    }

    fun cambiarJugador() {
        nombre = ""
        indicePregunta = 0
        puntaje = 0
        seleccionada = null
        respondida = false
        pantalla = Pantalla.INICIO
    }
}