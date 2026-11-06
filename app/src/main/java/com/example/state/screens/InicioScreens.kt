package com.example.state.screens

import android.R
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.state.components.ComentarioField
import com.example.state.components.RatingBar
import com.example.state.components.ResumenReseña




@Composable
fun InicioScreen(modifier: Modifier = Modifier) {
        var comida by remember { mutableStateOf(0) }
        var servicio by remember { mutableStateOf(0) }
        var ambiente by remember { mutableStateOf(0) }
        var comentario by remember { mutableStateOf("") }
        var etiquetas by remember { mutableStateOf(setOf<String>()) }
        var publicada by remember { mutableStateOf(false) }
        val listaEtiquetasDisponibles = listOf("Familiar", "Romántico", "Económico", "Vegetariano", "Rápido")
        val tieneTodasCalificaciones = comida > 0 && servicio > 0 && ambiente > 0
        val general = if (tieneTodasCalificaciones) (comida + servicio + ambiente) / 3.0 else 0.0
        val puedePublicar = tieneTodasCalificaciones && comentario.length >= 20
        Column(
                modifier = modifier
                        .fillMaxSize()
                        .padding(16.dp)
                        .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
        ) {
                Text(text = "← Escribe tu reseña", fontSize = 24.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(16.dp))

                if (publicada) {
                        ResumenReseña(
                                general = general,
                                comentario = comentario,
                                etiquetas = etiquetas,
                                onReiniciar = { comida = 0
                                        servicio = 0
                                        ambiente = 0
                                        comentario = ""
                                        etiquetas = setOf()
                                        publicada = false
                                }
                        )
                } else {
                        Column(modifier= Modifier) {
                                Text(text = "Restaurante El Mezquite")
                                Text(text = "Califica tu experiencia", fontSize = 10.sp, color = Color.Gray)
                                RatingBar(
                                        title = "Comida",
                                        rating = comida,
                                        onRatingChange = { comida = it })
                                Spacer(modifier = Modifier.height(12.dp))
                                RatingBar(
                                        title = "Servicio",
                                        rating = servicio,
                                        onRatingChange = { servicio = it })
                                Spacer(modifier = Modifier.height(12.dp))
                                RatingBar(
                                        title = "Ambiente",
                                        rating = ambiente,
                                        onRatingChange = { ambiente = it })
                                Spacer(modifier = Modifier.height(16.dp))
                        }
                        if (tieneTodasCalificaciones) {
                                Text(
                                        text = "Calificación General: %.1f ⭐".format(general),
                                        fontSize = 18.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = Color(0xFF624BFF)
                                )
                                Spacer(modifier = Modifier.height(12.dp))
                        }

                        ComentarioField(
                                texto = comentario,
                                onChange = { comentario = it },
                                maximo = 200
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        Spacer(modifier = Modifier.height(8.dp))

                        FlowRow(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(8.dp),
                                verticalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                                listaEtiquetasDisponibles.forEach { etiqueta ->
                                        val seleccionada = etiqueta in etiquetas
                                        FilterChip(
                                                selected = seleccionada,
                                                onClick = {
                                                        etiquetas = if (seleccionada) etiquetas - etiqueta else etiquetas + etiqueta
                                                },
                                                label = { Text(etiqueta) }
                                        )
                                }
                        }

                        Spacer(modifier = Modifier.height(24.dp))

                        Button(
                                onClick = { publicada = true },
                                enabled = puedePublicar,
                                modifier = Modifier
                                        .fillMaxWidth()
                                        .height(48.dp)
                        ) {
                                Text(text = "Publicar", fontSize = 16.sp)
                        }
                }
        }
}

@Preview(showBackground = true)
@Composable
fun ScreenPreview() {
        InicioScreen(modifier = Modifier)
}