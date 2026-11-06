package com.example.state.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.state.model.Gasto

@Composable
fun ResumenPresupuesto(
    ingreso: Double,
    gastos: List<Gasto>,
    modifier: Modifier = Modifier
) {
    val totalGastado = gastos.sumOf { it.monto }
    val saldo = ingreso - totalGastado
    val porcentaje = if (ingreso > 0.0) (totalGastado / ingreso) else 0.0
    val progresoFloat = porcentaje.coerceIn(0.0, 1.0).toFloat()
    val gastoPorCategoria = gastos.groupBy { it.categoria }
        .mapValues { entry -> entry.value.sumOf { it.monto } }
    val categoriaTop = gastoPorCategoria.maxByOrNull { it.value }?.key
    val colorBarra = when {
        porcentaje > 1.0 -> Color.Red
        porcentaje >= 0.8 -> Color(0xFFFFC107)
        else -> Color(0xFF4CAF50)
    }
    Card(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFFAFAFA)),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Resumen Financiero", fontSize = 18.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))

            Text(text = "Total Gastado: $$totalGastado")
            Text(text = "Saldo Disponible: $$saldo", color = if (saldo < 0) Color.Red else Color.Black)
            Text(text = "Categoría con mayor gasto: ${categoriaTop?.nombre ?: "Ninguna"}")
            Spacer(modifier = Modifier.height(12.dp))
            Text(text = "Presupuesto usado: ${(porcentaje * 100).toInt()}%")
            Spacer(modifier = Modifier.height(4.dp))
            LinearProgressIndicator(
                progress = { progresoFloat },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(8.dp),
                color = colorBarra,
            )
            if (porcentaje > 1.0) {
                Spacer(modifier = Modifier.height(10.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFFFFCDD2), shape = RoundedCornerShape(8.dp))
                        .padding(8.dp)
                ) {
                    Text(
                        text = "ALERTA ROJA!!!!!, Has excedido tu presupuesto por $${totalGastado - ingreso}",
                        color = Color.Red,
                        fontWeight = FontWeight.Bold,
                        fontSize = 13.sp
                    )
                }
            } else if (porcentaje >= 0.8) {
                Spacer(modifier = Modifier.height(10.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFFFFF9C4), shape = RoundedCornerShape(8.dp))
                        .padding(8.dp)
                ) {
                    Text(
                        text = "Alerta amarilla!!, has gastado el 80% o más de tu beca",
                        color = Color(0xFF856404),
                        fontWeight = FontWeight.Bold,
                        fontSize = 13.sp
                    )
                }
            }
        }
    }
}