package com.example.state.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.state.Components.camposItem
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment


@Composable
fun InicioScreen(modifier: Modifier = Modifier) {
        var textoCorreo by remember { mutableStateOf("") }
        var textoContrasena by remember { mutableStateOf("") }
        Column(modifier= Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) { }
        camposItem(
                Correo = textoCorreo,
                onCorreoChange = { textoCorreo = it },
                Contraseña = textoContrasena,
                onContraseñaChange = { textoContrasena = it }
        )
}

@Preview(showBackground = true)
@Composable
fun ScreenPreview() {
        InicioScreen(modifier = Modifier)
}