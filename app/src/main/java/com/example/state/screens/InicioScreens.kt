package com.example.state.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.state.componets.ProductoItem
import com.example.state.model.Producto
import com.example.state.R
import androidx.compose.ui.res.colorResource

@Composable
fun InicioScreen(modifier: Modifier = Modifier) {
        val catalogo = remember {
                listOf(
                        Producto("Tortillas 1 kg", 24.0),
                         Producto("Leche 1 L", 27.5),
                        Producto("Huevo 12 pz", 52.0),
                         Producto("Frijol 1 kg", 38.0)
                )
        }
        val cantidades = remember { mutableStateListOf(0, 0, 0, 0) }
        val totalArticulos = cantidades.sum()
        val subtotal = catalogo.indices.sumOf { index ->
                catalogo[index].precio * cantidades[index]
        }
        val descuento = if (subtotal >= 200.0) subtotal * 0.10 else 0.0
        val subtotalConDescuento = subtotal - descuento
        val envio = when {
                totalArticulos == 0 -> 0.0
                subtotalConDescuento >= 150.0 -> 0.0
                else -> 25.0
        }

        val totalFinal = subtotalConDescuento + envio

        Column(
                modifier = modifier
                        .fillMaxSize()
                        .padding(24.dp),
                verticalArrangement = Arrangement.SpaceBetween
        ) {
                Column {
                        Text(text = "Tienda Don Pepe", fontSize = 24.sp)
                        Spacer(modifier = Modifier.height(16.dp))
                        catalogo.forEachIndexed { index, producto ->
                                ProductoItem(
                                        producto = producto,
                                        cantidad = cantidades[index],
                                        onCantidadChange = { nuevaCantidad ->
                                                cantidades[index] = nuevaCantidad
                                        }
                                )
                        HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
                        }
                }

                Card(modifier = Modifier.fillMaxWidth().height(300.dp)) {
                        Column(modifier = Modifier.padding(16.dp)) {
                                Text(text = "Artículos:  $totalArticulos")
                                Text(text = "Subtotal:  $$subtotal")
                                Text(text = "Descuento 10%:   -$$descuento")
                                Text(text = "Envío: ${if (envio == 0.0) "GRATIS" else "$$envio"}")
                                Divider(modifier = Modifier.padding(vertical = 8.dp))
                                Text(text = "TOTAL: $$totalFinal", fontSize = 18.sp)
                        }
                }
                Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                        OutlinedButton(
                                onClick = {
                                        for (i in cantidades.indices) {
                                                cantidades[i] = 0
                                        }
                                },
                                modifier = Modifier.weight(1f)
                        ) {
                                Text(text = "Vaciar")
                        }

                        Button(
                                onClick = {},
                                colors = ButtonDefaults.buttonColors(
                                containerColor = colorResource(id = R.color.purple_700),
                                contentColor = Color.White
                                ),
                                enabled = totalArticulos > 0,
                                modifier = Modifier.weight(1f)
                        ) {
                                Text(text = "Confirmar pedido")
                        }
                }
        }
}

@Preview(showBackground = true)
@Composable
fun ScreenPreview() {
        InicioScreen(modifier = Modifier)
}