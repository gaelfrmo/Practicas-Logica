package com.example.state.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AforoIndicatorCard(
    personasEnSala: Int,
    capacidadTotal: Int,
    porcentaje: Float,
    progress: Float,
    puedeEntrar: Boolean
) {
    val statusColor = when {
        porcentaje < 70 -> Color(0xFF2A7D33)
        porcentaje < 100 -> Color(0xFFF9A825)
        else -> Color(0xFFC62828)
    }

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
                text = if (puedeEntrar) "Aforo disponible" else "Sala llena",
                fontSize = 12.sp,
                color = Color(0xFF49454F)
            )
        }
    }
}

@Composable
fun VisitasCard(visitasDelDia: Int) {
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