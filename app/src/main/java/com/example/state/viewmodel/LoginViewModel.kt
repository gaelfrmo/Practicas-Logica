package com.example.state.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.state.models.loginEstado

class LoginViewModel : ViewModel() {

    private val maxIntentos = 3
    private val correoCorrecto = "paciente@vida.mx"
    private val passwordCorrecta = "Vida2025"

    var textoCorreo by mutableStateOf("")
        private set

    var textoContrasena by mutableStateOf("")
        private set

    var recordarme by mutableStateOf(false)
        private set

    var intentosFallidos by mutableIntStateOf(0)
        private set

    var estadoLogin by mutableStateOf(loginEstado.INACTIVO)
        private set

    val mensajeError: String
        get() = when (estadoLogin) {
            loginEstado.ERROR -> "Correo o contraseña incorrectos (intento $intentosFallidos de $maxIntentos)"
            loginEstado.BLOQUEADO -> "Demasiados intentos. Intente de nuevo más tarde."
            else -> ""
        }

    fun onCorreoChange(nuevoCorreo: String) {
        textoCorreo = nuevoCorreo
    }

    fun onContrasenaChange(nuevaContrasena: String) {
        textoContrasena = nuevaContrasena
    }

    fun onRecordarmeChange(nuevoRecordarme: Boolean) {
        recordarme = nuevoRecordarme
    }

    fun iniciarSesion() {
        if (textoCorreo == correoCorrecto && textoContrasena == passwordCorrecta) {
            estadoLogin = loginEstado.EXITO
            intentosFallidos = 0
        } else {
            intentosFallidos++
            estadoLogin = if (intentosFallidos >= maxIntentos) {
                loginEstado.BLOQUEADO
            } else {
                loginEstado.ERROR
            }
        }
    }

    fun cerrarSesion() {
        textoCorreo = ""
        textoContrasena = ""
        recordarme = false
        intentosFallidos = 0
        estadoLogin = loginEstado.INACTIVO
    }
}