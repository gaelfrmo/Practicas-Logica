package com.example.state.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class ResenaViewModel : ViewModel() {

    val listaEtiquetasDisponibles = listOf("Familiar", "Romántico", "Económico", "Vegetariano", "Rápido")

    var comida by mutableIntStateOf(0)
        private set

    var servicio by mutableIntStateOf(0)
        private set

    var ambiente by mutableIntStateOf(0)
        private set

    var comentario by mutableStateOf("")
        private set

    var etiquetas by mutableStateOf(setOf<String>())
        private set

    var publicada by mutableStateOf(false)
        private set

    val tieneTodasCalificaciones: Boolean
        get() = comida > 0 && servicio > 0 && ambiente > 0

    val general: Double
        get() = if (tieneTodasCalificaciones) (comida + servicio + ambiente) / 3.0 else 0.0

    val puedePublicar: Boolean
        get() = tieneTodasCalificaciones && comentario.length >= 20

    fun onComidaChange(valor: Int) {
        comida = valor
    }

    fun onServicioChange(valor: Int) {
        servicio = valor
    }

    fun onAmbienteChange(valor: Int) {
        ambiente = valor
    }

    fun onComentarioChange(nuevoComentario: String) {
        comentario = nuevoComentario
    }

    fun toggleEtiqueta(etiqueta: String) {
        etiquetas = if (etiqueta in etiquetas) etiquetas - etiqueta else etiquetas + etiqueta
    }

    fun publicar() {
        if (puedePublicar) {
            publicada = true
        }
    }

    fun reiniciar() {
        comida = 0
        servicio = 0
        ambiente = 0
        comentario = ""
        etiquetas = setOf()
        publicada = false
    }
}