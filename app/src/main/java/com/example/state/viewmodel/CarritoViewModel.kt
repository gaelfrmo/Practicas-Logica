package com.example.state.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.state.model.Producto

class CarritoViewModel : ViewModel() {

    val catalogo = listOf(
        Producto("Tortillas 1 kg", 24.0),
        Producto("Leche 1 L", 27.5),
        Producto("Huevo 12 pz", 52.0),
        Producto("Frijol 1 kg", 38.0)
    )

    val cantidades = mutableStateListOf(0, 0, 0, 0)

    val totalArticulos: Int
        get() = cantidades.sum()

    val subtotal: Double
        get() = catalogo.indices.sumOf { index ->
            catalogo[index].precio * cantidades[index]
        }

    val descuento: Double
        get() = if (subtotal >= 200.0) subtotal * 0.10 else 0.0

    val subtotalConDescuento: Double
        get() = subtotal - descuento

    val envio: Double
        get() = when {
            totalArticulos == 0 -> 0.0
            subtotalConDescuento >= 150.0 -> 0.0
            else -> 25.0
        }

    val totalFinal: Double
        get() = subtotalConDescuento + envio

    fun actualizarCantidad(index: Int, nuevaCantidad: Int) {
        if (index in cantidades.indices) {
            cantidades[index] = nuevaCantidad
        }
    }

    fun vaciarCarrito() {
        for (i in cantidades.indices) {
            cantidades[i] = 0
        }
    }
}