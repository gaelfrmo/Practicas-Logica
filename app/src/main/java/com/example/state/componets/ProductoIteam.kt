package com.example.state.componets

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.state.model.Producto

@Composable
fun ProductoItem(
    producto: Producto,
    cantidad: Int,
    onCantidadChange: (Int) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(text = producto.nombre, fontSize = 16.sp)
            Text(text = "$${producto.precio} c/u", fontSize = 12.sp)
        }

        Row(verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = { if (cantidad > 0) onCantidadChange(cantidad - 1) }) {
                Text(text = "–", fontSize = 18.sp)
            }

            Text(text = cantidad.toString(), fontSize = 16.sp, modifier = Modifier.padding(horizontal = 8.dp))

            IconButton(onClick = { if (cantidad < 10) onCantidadChange(cantidad + 1) }) {
                Text(text = "+", fontSize = 18.sp)
            }
        }
    }
}