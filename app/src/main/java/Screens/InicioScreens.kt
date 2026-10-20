package Screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun InicioScreen(
        modifier: Modifier = Modifier,
        textoIngresado: String,
        elementos: List<String>,
        onTextoChange: (String) -> Unit,
        onAgregar: () -> Unit
) {
        Column(
                modifier = modifier
                        .fillMaxSize()
                        .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
                Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                        TextField(
                                value = textoIngresado,
                                onValueChange = onTextoChange,
                                label = { Text("Nuevo elemento") },
                                modifier = Modifier.weight(1f)
                        )

                        Button(onClick = onAgregar) {
                                Text("Agregar")
                        }
                }

                Text(text = "Lista de elementos:")
                LazyColumn(
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                        items(elementos) { elemento ->
                                Text(text = "• $elemento")
                        }
                }
        }
}

@Preview(showBackground = true)
@Composable
fun ScreenPreview() {
        InicioScreen(
                textoIngresado = "Elemento de prueba",
                elementos = listOf("Primer ítem", "Segundo ítem", "Tercer ítem"),
                onTextoChange = {},
                onAgregar = {}
        )
}