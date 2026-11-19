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
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.state.R
import com.example.state.componets.ProductoItem
import com.example.state.viewmodel.CarritoViewModel

@Composable
fun InicioScreen(
        modifier: Modifier = Modifier,
        vm: CarritoViewModel = viewModel()
) {
        Column(
                modifier = modifier
                        .fillMaxSize()
                        .padding(24.dp),
                verticalArrangement = Arrangement.SpaceBetween
        ) {
                Column {
                        Text(text = "Tienda Don Pepe", fontSize = 24.sp)
                        Spacer(modifier = Modifier.height(16.dp))
                        vm.catalogo.forEachIndexed { index, producto ->
                                ProductoItem(
                                        producto = producto,
                                        cantidad = vm.cantidades[index],
                                        onCantidadChange = { nuevaCantidad ->
                                                vm.actualizarCantidad(index, nuevaCantidad)
                                        }
                                )
                                HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
                        }
                }

                Card(
                        modifier = Modifier
                                .fillMaxWidth()
                                .height(300.dp)
                ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                                Text(text = "Artículos:  ${vm.totalArticulos}")
                                Text(text = "Subtotal:  $${vm.subtotal}")
                                Text(text = "Descuento 10%:   -$${vm.descuento}")
                                Text(text = "Envío: ${if (vm.envio == 0.0) "GRATIS" else "$${vm.envio}"}")
                                HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
                                Text(text = "TOTAL: $${vm.totalFinal}", fontSize = 18.sp)
                        }
                }

                Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                        OutlinedButton(
                                onClick = { vm.vaciarCarrito() },
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
                                enabled = vm.totalArticulos > 0,
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