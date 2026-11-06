package com.example.state.model

enum class Categoria(val nombre: String) {
    COMIDA("Comida"),
    TRANSPORTE("Transporte"),
    RENTA("Renta"),
    OCIO("Ocio"),
    OTROS("Otros")
}

data class Gasto(
    val id: Int,
    val concepto: String,
    val monto: Double,
    val categoria: Categoria
)