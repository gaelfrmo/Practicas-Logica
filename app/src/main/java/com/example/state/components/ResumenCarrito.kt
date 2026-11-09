package com.example.state.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ResumenCarrito(articulos: Int,
                   subtotal: Double,
                   descuento: Double,
                   envio: Double,
                   total: Double,
                onVaciarCarrito: () -> Unit,
                onConfirmarPedido: () -> Unit){
    Card(modifier = Modifier) {
        Column(modifier = Modifier.fillMaxWidth().padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp)) {
            Text("Articulos: $articulos")
            Text("Subtotal: $subtotal")
            Text("Descuento (10%): $descuento")
            Text("Envio: $envio")
            Divider(modifier = Modifier.padding(vertical = 4.dp))
            Text("Total: $total", fontSize = 34.sp)
        }
        Row(modifier = Modifier.padding(top = 8.dp).fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Button(
                onClick = onVaciarCarrito,
                enabled = articulos > 0,
                modifier = Modifier.weight(1f)
            ) {
                Text("Vaciar carrito")
            }
            Button(
                onClick = onConfirmarPedido,
                enabled = articulos > 0,
                modifier = Modifier.weight(1f)
            ) {
                Text("Confirmar pedido")

            }
        }
    }
}