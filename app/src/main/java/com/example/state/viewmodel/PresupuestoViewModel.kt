package com.example.state.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.state.model.Categoria
import com.example.state.model.Gasto

class PresupuestoViewModel : ViewModel() {

    var ingresoTexto by mutableStateOf("4500")
        private set

    val gastos = mutableStateListOf<Gasto>()

    private var idCounter by mutableIntStateOf(1)

    val ingresoNum: Double
        get() = ingresoTexto.toDoubleOrNull() ?: 0.0

    fun onIngresoChange(nuevoIngreso: String) {
        ingresoTexto = nuevoIngreso
    }

    fun agregarGasto(concepto: String, monto: Double, categoria: Categoria) {
        val nuevoGasto = Gasto(
            id = idCounter++,
            concepto = concepto,
            monto = monto,
            categoria = categoria
        )
        gastos.add(nuevoGasto)
    }

    fun eliminarGasto(gasto: Gasto) {
        gastos.remove(gasto)
    }
}