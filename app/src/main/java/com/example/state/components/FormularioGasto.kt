package com.example.state.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.state.model.Categoria

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormularioGasto(onAgregar: (String, Double, Categoria) -> Unit, modifier: Modifier = Modifier) {
    var concepto by remember { mutableStateOf("") }
    var montoTexto by remember { mutableStateOf("") }
    var categoriaSeleccionada by remember { mutableStateOf(Categoria.COMIDA) }
    var expanded by remember { mutableStateOf(false) }

    val montoValido = montoTexto.toDoubleOrNull() != null && (montoTexto.toDoubleOrNull() ?: 0.0) > 0.0
    val formularioValido = concepto.isNotBlank() && montoValido

    Column(modifier = modifier.fillMaxWidth()) {
        OutlinedTextField(value = concepto, onValueChange = { concepto = it }, label = { Text("Concepto (ej. Tacos)") }, modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(value = montoTexto, onValueChange = { montoTexto = it }, label = { Text("Monto ($)") }, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number), modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(8.dp))

        ExposedDropdownMenuBox(expanded = expanded, onExpandedChange = { expanded = !expanded }, modifier = Modifier.fillMaxWidth()) {
            OutlinedTextField(
                value = categoriaSeleccionada.nombre,
                onValueChange = {},
                readOnly = true,
                label = { Text("Categoría") },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                modifier = Modifier.fillMaxWidth().menuAnchor()
            )
            ExposedDropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                Categoria.entries.forEach { cat ->
                    DropdownMenuItem(text = { Text(cat.nombre) }, onClick = {
                        categoriaSeleccionada = cat
                        expanded = false
                    })
                }
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        Button(onClick = {
            val montoNum = montoTexto.toDoubleOrNull() ?: 0.0
            onAgregar(concepto, montoNum, categoriaSeleccionada)
            concepto = ""
            montoTexto = ""
        }, enabled = formularioValido, modifier = Modifier.fillMaxWidth()) {
            Text("Agregar Gasto")
        }
    }
}