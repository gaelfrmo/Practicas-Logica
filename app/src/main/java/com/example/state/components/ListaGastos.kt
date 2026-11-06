package com.example.state.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.state.model.Gasto

@Composable
fun ListaGastos(gastos: List<Gasto>, onEliminar: (Gasto) -> Unit, modifier: Modifier = Modifier) {
    Column(modifier = modifier.fillMaxWidth()) {
        Text(text = "Gastos recientes", fontWeight = FontWeight.Bold, fontSize = 16.sp)
        Spacer(modifier = Modifier.height(8.dp))

        if (gastos.isEmpty()) {
            Text(text = "No hay gastos registrados aún.", color = Color.Gray, fontSize = 14.sp)
        } else {
            LazyColumn(modifier = Modifier.fillMaxWidth().height(180.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
                items(gastos) { gasto ->
                    Card(modifier = Modifier.fillMaxWidth(), colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F5F5))) {
                        Row(modifier = Modifier.fillMaxWidth().padding(12.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                            Column {
                                Text(text = gasto.concepto, fontWeight = FontWeight.SemiBold)
                                Text(text = gasto.categoria.nombre, fontSize = 12.sp, color = Color.DarkGray)
                            }
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(text = "-$${gasto.monto}", fontWeight = FontWeight.Bold, color = Color.Red)
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = "❌",
                                    modifier = Modifier.clickable { onEliminar(gasto) }.padding(4.dp),
                                    fontSize = 16.sp
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}