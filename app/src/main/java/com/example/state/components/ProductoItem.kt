package com.example.state.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.FilledTonalIconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.state.model.Producto

@Composable
fun ProductoItem(producto: Producto,
                 cantidad: Int,
                 onCantidadChange: (Int) -> Unit) {
    Card(modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp,vertical = 4.dp)) {
    Row(modifier = Modifier.padding(12.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalAlignment = Alignment.CenterVertically) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = producto.nombre,
            )
            Text(
                text = "$${producto.precio}",
            )
        }
            FilledTonalIconButton(
                onClick = { if (cantidad > 0) onCantidadChange(cantidad - 1) },
                enabled = cantidad > 0
            ) {
                Text(text = "-")
            }
            Text("$cantidad")
            FilledTonalIconButton(
                onClick = { if (cantidad < 10) onCantidadChange(cantidad + 1) },
                enabled = cantidad < 10
            ) {
                Text(text = "+")
            }
        }
    }
}