package Screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
@Composable
fun InicioScreen(modifier: Modifier = Modifier) {
        var xtext by remember { mutableStateOf("") }
        var ytext by remember { mutableStateOf("") }
        var resultado by remember { mutableStateOf("") }

        Column(modifier = modifier.padding(16.dp)) {
            OutlinedTextField(
                value = xtext,
                onValueChange = { xtext = it },
                label = { Text("Letra o texto a repetir") }
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = ytext,
                onValueChange = { ytext = it },
                label = { Text("Cantidad de veces") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = {
                val veces = ytext.toIntOrNull() ?: 0
                resultado = xtext.repeat(veces)
            }) {
                Text(text = "Repetir")
            }

            Spacer(modifier = Modifier.height(16.dp))

            if (resultado.isNotEmpty()) {
                Text(text = "Resultado: $resultado")
            }
        }
    }


@Preview(showBackground = true)
@Composable
fun ScreenPreview() {
        InicioScreen(modifier = Modifier)
}