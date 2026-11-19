package com.example.state.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class BibliotecaViewModel : ViewModel() {

    val capacidadTotal = 50

    var personasEnSala by mutableIntStateOf(0)
        private set

    var visitasDelDia by mutableIntStateOf(118)
        private set

    val porcentaje: Float
        get() = (personasEnSala.toFloat() / capacidadTotal) * 100

    val progress: Float
        get() = personasEnSala.toFloat() / capacidadTotal

    val puedeEntrar: Boolean
        get() = personasEnSala < capacidadTotal

    val puedeSalir: Boolean
        get() = personasEnSala > 0

    fun registrarEntrada() {
        if (puedeEntrar) {
            personasEnSala++
            visitasDelDia++
        }
    }

    fun registrarSalida() {
        if (puedeSalir) {
            personasEnSala--
        }
    }

    fun cerrarDia() {
        personasEnSala = 0
        visitasDelDia = 0
    }
}