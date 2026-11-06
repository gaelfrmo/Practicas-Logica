package com.example.state.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ComentarioField(
    texto: String,
    onChange: (String) -> Unit,
    maximo: Int = 200,
    modifier: Modifier = Modifier
) {
    val length = texto.length
    val esRojo = length >= 180
    val colorContador = if (esRojo) Color.Red else Color.Gray

    Column(modifier = modifier.fillMaxWidth()) {
        OutlinedTextField(
            value = texto,
            onValueChange = { nuevoTexto ->
                if (nuevoTexto.length <= maximo) {
                    onChange(nuevoTexto)
                }
            },
            label = { Text("Comentario (minimo 20 caracteres)") },
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp),
            maxLines = 5
        )
        Spacer(modifier = Modifier.height(4.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            Text(
                text = "$length/$maximo",
                fontSize = 12.sp,
                fontWeight = if (esRojo) FontWeight.Bold else FontWeight.Normal,
                color = colorContador
            )
        }
    }
}