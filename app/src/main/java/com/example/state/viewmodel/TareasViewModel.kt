package com.example.state.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class TareasViewModel : ViewModel() {

    val tareas = mutableStateListOf(
        "Entregar proyecto POO",
        "Leer capitulo 4",
        "Taller de calculo",
        "Preparar expocision"
    )

    var texto by mutableStateOf("")
        private set

    var prioridadSeleccionada by mutableStateOf("Alta")
        private set

    var filtroSeleccionado by mutableStateOf("Todas")
        private set

    fun onTextoChange(nuevoTexto: String) {
        texto = nuevoTexto
    }

    fun onPrioridadChange(nuevaPrioridad: String) {
        prioridadSeleccionada = nuevaPrioridad
    }

    fun onFiltroChange(nuevoFiltro: String) {
        filtroSeleccionado = nuevoFiltro
    }

    fun agregarTarea() {
        if (texto.isNotBlank()) {
            tareas.add(texto)
            texto = ""
        }
    }

    fun eliminarTarea(tarea: String) {
        tareas.remove(tarea)
    }

    fun borrarCompletadas() {
        tareas.clear()
    }
}