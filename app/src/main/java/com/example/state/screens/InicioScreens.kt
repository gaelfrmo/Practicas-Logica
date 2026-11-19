package com.example.state.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.state.components.ComentarioField
import com.example.state.components.RatingBar
import com.example.state.components.ResumenReseña
import com.example.state.viewmodel.ResenaViewModel

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun InicioScreen(
        modifier: Modifier = Modifier,
        vm: ResenaViewModel = viewModel()
) {
        Column(
                modifier = modifier
                        .fillMaxSize()
                        .padding(16.dp)
                        .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
        ) {
                Text(text = "← Escribe tu reseña", fontSize = 24.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(16.dp))

                if (vm.publicada) {
                        ResumenReseña(
                                general = vm.general,
                                comentario = vm.comentario,
                                etiquetas = vm.etiquetas,
                                onReiniciar = { vm.reiniciar() }
                        )
                } else {
                        Column(modifier = Modifier) {
                                Text(text = "Restaurante El Mezquite")
                                Text(text = "Califica tu experiencia", fontSize = 10.sp, color = Color.Gray)
                                RatingBar(
                                        title = "Comida",
                                        rating = vm.comida,
                                        onRatingChange = { vm.onComidaChange(it) }
                                )
                                Spacer(modifier = Modifier.height(12.dp))
                                RatingBar(
                                        title = "Servicio",
                                        rating = vm.servicio,
                                        onRatingChange = { vm.onServicioChange(it) }
                                )
                                Spacer(modifier = Modifier.height(12.dp))
                                RatingBar(
                                        title = "Ambiente",
                                        rating = vm.ambiente,
                                        onRatingChange = { vm.onAmbienteChange(it) }
                                )
                                Spacer(modifier = Modifier.height(16.dp))
                        }

                        if (vm.tieneTodasCalificaciones) {
                                Text(
                                        text = "Calificación General: %.1f ⭐".format(vm.general),
                                        fontSize = 18.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = Color(0xFF624BFF)
                                )
                                Spacer(modifier = Modifier.height(12.dp))
                        }

                        ComentarioField(
                                texto = vm.comentario,
                                onChange = { vm.onComentarioChange(it) },
                                maximo = 200
                        )

                        Spacer(modifier = Modifier.height(24.dp))

                        FlowRow(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(8.dp),
                                verticalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                                vm.listaEtiquetasDisponibles.forEach { etiqueta ->
                                        val seleccionada = etiqueta in vm.etiquetas
                                        FilterChip(
                                                selected = seleccionada,
                                                onClick = { vm.toggleEtiqueta(etiqueta) },
                                                label = { Text(etiqueta) }
                                        )
                                }
                        }

                        Spacer(modifier = Modifier.height(24.dp))

                        Button(
                                onClick = { vm.publicar() },
                                enabled = vm.puedePublicar,
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