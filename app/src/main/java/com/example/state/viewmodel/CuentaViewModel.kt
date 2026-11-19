package com.example.state.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.state.model.Cuenta

class CuentaViewModel : ViewModel() {

    var montoTexto by mutableStateOf("")
        private set

    var propinaPct by mutableIntStateOf(20)
        private set

    var personas by mutableIntStateOf(1)
        private set

    val resultado: Cuenta
        get() {
            val monto = montoTexto.toDoubleOrNull() ?: 0.0
            val propinaTotal = monto * (propinaPct / 100.0)
            val totalConPropina = monto + propinaTotal
            val divisor = if (personas > 0) personas else 1
            val montoPorPersona = totalConPropina / divisor

            return Cuenta(
                propinaTotal = propinaTotal,
                totalConPropina = totalConPropina,
                montoPorPersona = montoPorPersona,
                sobrantePorRedondeo = 0.0
            )
        }

    fun onMontoChange(nuevoMonto: String) {
        montoTexto = nuevoMonto
    }

    fun onPropinaChange(nuevaPropina: Int) {
        propinaPct = nuevaPropina
    }

    fun onPersonasChange(nuevasPersonas: Int) {
        personas = nuevasPersonas
    }
}