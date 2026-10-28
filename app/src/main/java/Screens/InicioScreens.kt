package Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun InicioScreen(modifier: Modifier = Modifier) {
        var personasEnSala by rememberSaveable { mutableIntStateOf(0) }
        var visitasDelDia by rememberSaveable { mutableIntStateOf(118) }
        val capacidadTotal = 50

        val porcentaje = (personasEnSala.toFloat() / capacidadTotal) * 100
        val progress = personasEnSala.toFloat() / capacidadTotal

        val statusColor = when {
                porcentaje < 70 -> Color(0xFF2A7D33)
                porcentaje < 100 -> Color(0xFFF9A825)
                else -> Color(0xFFC62828)
        }

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
                        text = "Sala de estudio · capacidad $capacidadTotal",
                        fontSize = 14.sp,
                        color = Color(0xFF49454F)
                )

                Spacer(modifier = Modifier.height(24.dp))

                Surface(
                        shape = RoundedCornerShape(16.dp),
                        color = Color(0xFFE8F5E9),
                        modifier = Modifier.fillMaxWidth()
                ) {
                        Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier.padding(24.dp)
                        ) {
                                Text(
                                        text = "$personasEnSala / $capacidadTotal",
                                        fontSize = 48.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = statusColor
                                )
                                Text(
                                        text = "personas en la sala",
                                        fontSize = 14.sp,
                                        color = Color(0xFF49454F)
                                )

                                Spacer(modifier = Modifier.height(16.dp))

                                LinearProgressIndicator(
                                        progress = { progress },
                                        modifier = Modifier
                                                .fillMaxWidth()
                                                .height(8.dp)
                                                .clip(RoundedCornerShape(4.dp)),
                                        color = statusColor,
                                        trackColor = Color(0xFFEADDFF)
                                )

                                Spacer(modifier = Modifier.height(16.dp))

                                Text(
                                        text = "${porcentaje.toInt()} % de ocupación",
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = statusColor
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                        text = "Aforo disponible",
                                        fontSize = 12.sp,
                                        color = Color(0xFF49454F)
                                )
                        }
                }

                Spacer(modifier = Modifier.height(24.dp))

                Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                        OutlinedButton(
                                onClick = { if (personasEnSala > 0) personasEnSala-- },
                                modifier = Modifier
                                        .weight(1f)
                                        .height(56.dp),
                                colors = ButtonDefaults.outlinedButtonColors(contentColor = Color(0xFF6750A4))
                        ) {
                                Text("- Sale", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                        }

                        Button(
                                onClick = {
                                        if (personasEnSala < capacidadTotal) {
                                                personasEnSala++
                                                visitasDelDia++
                                        }
                                },
                                modifier = Modifier
                                        .weight(1f)
                                        .height(56.dp),
                                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6750A4))
                        ) {
                                Text("+ Entra", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                        }
                }

                Spacer(modifier = Modifier.height(24.dp))

                Surface(
                        shape = RoundedCornerShape(16.dp),
                        color = Color(0xFFF3EDF7),
                        modifier = Modifier.fillMaxWidth()
                ) {
                        Row(
                                modifier = Modifier
                                        .padding(24.dp)
                                        .fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                        ) {
                                Column {
                                        Text(text = "Visitas del día", fontSize = 16.sp, color = Color(0xFF49454F))
                                        Text(text = "Solo suma las entradas", fontSize = 12.sp, color = Color(0xFF49454F))
                                }
                                Text(
                                        text = "$visitasDelDia",
                                        fontSize = 40.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = Color(0xFF1D1B20)
                                )
                        }
                }

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
                        onClick = {
                                personasEnSala = 0
                                visitasDelDia = 0
                        },
                        modifier = Modifier
                                .fillMaxWidth()
                                .height(56.dp),
                        colors = ButtonDefaults.outlinedButtonColors(contentColor = Color(0xFFB3261E)),
                        border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFB3261E))
                ) {
                        Text("Cerrar día", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                }

                Spacer(modifier = Modifier.height(16.dp))
        }
}

@Composable
fun LegendItem(color: Color, text: String) {
        Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                        modifier = Modifier
                                .size(10.dp)
                                .clip(CircleShape)
                                .background(color)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(text = text, fontSize = 11.sp, color = Color(0xFF49454F))
        }
}

@Preview(showBackground = true)
@Composable
fun ScreenPreview() {
        InicioScreen()
}