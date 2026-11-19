package com.example.state.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.state.components.AforoIndicatorCard
import com.example.state.components.LegendItem
import com.example.state.components.VisitasCard
import com.example.state.viewmodel.BibliotecaViewModel

@Composable
fun InicioScreen(
        modifier: Modifier = Modifier,
        vm: BibliotecaViewModel = viewModel()
) {
        Column(
                modifier = modifier
                        .fillMaxSize()
                        .background(Color(0xFFFCF8FF))
                        .padding(24.dp)
        ) {
                Spacer(modifier = Modifier.height(32.dp))

                Text(
                        text = "Biblioteca Central",
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color(0xFF1D1B20)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                        text = "Sala de estudio · capacidad ${vm.capacidadTotal}",
                        fontSize = 14.sp,
                        color = Color(0xFF49454F)
                )

                Spacer(modifier = Modifier.height(24.dp))

                AforoIndicatorCard(
                        personasEnSala = vm.personasEnSala,
                        capacidadTotal = vm.capacidadTotal,
                        porcentaje = vm.porcentaje,
                        progress = vm.progress,
                        puedeEntrar = vm.puedeEntrar
                )

                Spacer(modifier = Modifier.height(24.dp))

                Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                        OutlinedButton(
                                onClick = { vm.registrarSalida() },
                                enabled = vm.puedeSalir,
                                modifier = Modifier
                                        .weight(1f)
                                        .height(56.dp),
                                colors = ButtonDefaults.outlinedButtonColors(contentColor = Color(0xFF6750A4))
                        ) {
                                Text("- Sale", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                        }

                        Button(
                                onClick = { vm.registrarEntrada() },
                                enabled = vm.puedeEntrar,
                                modifier = Modifier
                                        .weight(1f)
                                        .height(56.dp),
                                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6750A4))
                        ) {
                                Text("+ Entra", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                        }
                }

                Spacer(modifier = Modifier.height(24.dp))

                VisitasCard(visitasDelDia = vm.visitasDelDia)

                Spacer(modifier = Modifier.height(24.dp))

                Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically
                ) {
                        LegendItem(color = Color(0xFF2A7D33), text = "Verde < 70 %")
                        LegendItem(color = Color(0xFFF9A825), text = "Amarillo 70–99 %")
                        LegendItem(color = Color(0xFFC62828), text = "Rojo 100 %")
                }

                Spacer(modifier = Modifier.weight(1f))

                OutlinedButton(
                        onClick = { vm.cerrarDia() },
                        modifier = Modifier
                                .fillMaxWidth()
                                .height(56.dp),
                        colors = ButtonDefaults.outlinedButtonColors(contentColor = Color(0xFFB3261E)),
                        border = BorderStroke(1.dp, Color(0xFFB3261E))
                ) {
                        Text("Cerrar día", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                }

                Spacer(modifier = Modifier.height(16.dp))
        }
}

@Preview(showBackground = true)
@Composable
fun ScreenPreview() {
        InicioScreen()
}