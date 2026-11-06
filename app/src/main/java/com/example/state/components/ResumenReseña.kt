package com.example.state.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ResumenReseña(
    general: Double,
    comentario: String,
    etiquetas: Set<String>,
    onReiniciar: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Reseña Publicada con exito", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color(0xFF624BFF))
            Spacer(modifier = Modifier.height(12.dp))

            Text(text = "Calificación General: %.1f ⭐".format(general), fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
            Spacer(modifier = Modifier.height(8.dp))

            Text(text = "Comentario:", fontWeight = FontWeight.Bold)
            Text(text = comentario, fontSize = 14.sp)
            Spacer(modifier = Modifier.height(8.dp))

            Text(text = "Etiquetas elegidas:", fontWeight = FontWeight.Bold)
            Text(text = if (etiquetas.isEmpty()) "Ninguna" else etiquetas.joinToString(", "), fontSize = 14.sp)

            Spacer(modifier = Modifier.height(20.dp))

            Button(onClick = onReiniciar, modifier = Modifier.fillMaxWidth()) {
                Text("Escribir otra reseña")
            }
        }
    }
}