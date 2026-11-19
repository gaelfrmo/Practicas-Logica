package com.example.state.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class InscripcionViewModel : ViewModel() {

    val talleres = listOf("Pintura", "Fotografía", "Guitarra")

    var nombre by mutableStateOf("")
        private set

    var correo by mutableStateOf("")
        private set

    var edad by mutableStateOf("")
        private set

    var taller by mutableStateOf("Fotografía")
        private set

    var aceptaPrivacidad by mutableStateOf(false)
        private set

    var intentoEnviar by mutableStateOf(false)
        private set

    var inscrito by mutableStateOf(false)
        private set

    val errorNombre: String?
        get() = if (intentoEnviar && nombre.trim().length < 3) "Ingresa al menos 3 caracteres" else null

    val errorCorreo: String?
        get() {
            if (!intentoEnviar) return null
            val arroba = correo.indexOf('@')
            val punto = correo.indexOf('.', startIndex = arroba.coerceAtLeast(0))
            return if (arroba <= 0 || punto <= arroba + 1 || punto == correo.lastIndex) {
                "Ingresa un correo válido"
            } else null
        }

    val errorEdad: String?
        get() {
            if (!intentoEnviar) return null
            val num = edad.toIntOrNull()
            return if (num == null || num !in 12..99) "Edad entre 12 y 99 años" else null
        }

    val errorAviso: String?
        get() = if (intentoEnviar && !aceptaPrivacidad) "Debes aceptar el aviso de privacidad" else null

    fun onNombreChange(nuevo: String) { nombre = nuevo }
    fun onCorreoChange(nuevo: String) { correo = nuevo }
    fun onEdadChange(nuevo: String) {
        if (nuevo.all { it.isDigit() }) edad = nuevo
    }
    fun onTallerChange(nuevo: String) { taller = nuevo }
    fun onPrivacidadChange(acepta: Boolean) { aceptaPrivacidad = acepta }

    fun inscribir() {
        intentoEnviar = true
        if (errorNombre == null && errorCorreo == null && errorEdad == null && errorAviso == null) {
            inscrito = true
        }
    }

    fun nuevaInscripcion() {
        nombre = ""
        correo = ""
        edad = ""
        taller = "Fotografía"
        aceptaPrivacidad = false
        intentoEnviar = false
        inscrito = false
    }
}