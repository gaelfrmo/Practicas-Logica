package com.example.state

import Screens.InicioScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.state.ui.theme.StateTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            StateTheme {
                var textoIngresado by remember { mutableStateOf("") }
                val listaElementos = remember { mutableStateListOf<String>() }
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    InicioScreen(
                        modifier = Modifier.padding(innerPadding),
                        textoIngresado = textoIngresado,
                        elementos = listaElementos,
                        onTextoChange = { nuevoTexto ->
                            textoIngresado = nuevoTexto
                        },
                        onAgregar = {
                            if (textoIngresado.isNotBlank()) {
                                listaElementos.add(textoIngresado)
                                textoIngresado = ""
                            }
                        }
                    )
                }
            }
        }
    }
}