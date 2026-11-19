package com.example.state.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.state.model.Interlineado

@Composable
fun VistaPrevia(
    texto: String,
    tamano: Float,
    interlineado: Interlineado,
    modoOscuro: Boolean,
    usaSerif: Boolean
) {
    Column {
        Text(
            text = "VISTA PREVIA",
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF49454F)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .background(if (modoOscuro) Color(0xFF212124) else Color.White)
                .padding(24.dp)
        ) {
            Text(
                text = texto,
                fontSize = tamano.sp,
                lineHeight = (tamano * interlineado.factor).sp,
                color = if (modoOscuro) Color(0xFFE6E1E5) else Color(0xFF1D1B20),
                fontFamily = if (usaSerif) FontFamily.Serif else FontFamily.SansSerif
            )
        }
    }
}