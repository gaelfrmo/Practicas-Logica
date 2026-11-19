package com.example.state.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.state.model.Interlineado

class LecturaViewModel : ViewModel() {

    var tamanoLetra by mutableFloatStateOf(22f)
        private set

    var interlineado by mutableStateOf(Interlineado.NORMAL)
        private set

    var modoOscuro by mutableStateOf(true)
        private set

    var usaSerif by mutableStateOf(true)
        private set

    fun actualizarTamano(nuevoTamano: Float) {
        tamanoLetra = nuevoTamano
    }

    fun actualizarInterlineado(nuevoInterlineado: Interlineado) {
        interlineado = nuevoInterlineado
    }

    fun actualizarModoOscuro(activo: Boolean) {
        modoOscuro = activo
    }

    fun actualizarFuente(serif: Boolean) {
        usaSerif = serif
    }

    fun restablecer() {
        tamanoLetra = 22f
        interlineado = Interlineado.NORMAL
        modoOscuro = true
        usaSerif = true
    }
}