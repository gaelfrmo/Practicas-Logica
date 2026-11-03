package com.example.state.model

data class Cuenta(
    val propinaTotal: Double,
    val totalConPropina: Double,
    val montoPorPersona: Double,
    val sobrantePorRedondeo: Double?
)
