package com.example.state.Components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun camposItem(Correo: String,
               onCorreoChange: (String)->Unit,
               Contraseña: String,
               onContraseñaChange: (String)->Unit){
    Column(modifier = Modifier) {
        OutlinedTextField(value = Correo, onValueChange = onCorreoChange, label = { Text("Correo") })
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(value = Contraseña, onValueChange = onContraseñaChange, label = { "Contrase;a" })
    }
}