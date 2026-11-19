package com.example.state.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.state.model.Interlineado

@Composable
fun ControlesLectura(
    tamano: Float,
    onTamanoChange: (Float) -> Unit,
    interlineado: Interlineado,
    onInterlineadoChange: (Interlineado) -> Unit,
    modoOscuro: Boolean,
    onModoOscuroChange: (Boolean) -> Unit,
    usaSerif: Boolean,
    onUsaSerifChange: (Boolean) -> Unit
) {
    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Tamaño de letra", fontSize = 16.sp, color = Color(0xFF49454F))
            Text("${tamano.toInt()} sp", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color(0xFF6750A4))
        }
        Slider(
            value = tamano,
            onValueChange = onTamanoChange,
            valueRange = 12f..36f,
            colors = SliderDefaults.colors(
                thumbColor = Color(0xFF6750A4),
                activeTrackColor = Color(0xFF6750A4)
            )
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("A", fontSize = 12.sp, color = Color(0xFF49454F))
            Text("A", fontSize = 24.sp, color = Color(0xFF49454F))
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text("Interlineado", fontSize = 16.sp, color = Color(0xFF49454F))
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Interlineado.entries.forEach { opcion ->
                Row(verticalAlignment = Alignment.CenterVertically) {
                    RadioButton(
                        selected = (interlineado == opcion),
                        onClick = { onInterlineadoChange(opcion) },
                        colors = RadioButtonDefaults.colors(selectedColor = Color(0xFF6750A4))
                    )
                    Text(
                        text = opcion.name.lowercase().replaceFirstChar { it.uppercase() },
                        fontSize = 14.sp,
                        color = Color(0xFF49454F)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Modo oscuro", fontSize = 16.sp, color = Color(0xFF49454F))
            Switch(
                checked = modoOscuro,
                onCheckedChange = onModoOscuroChange,
                colors = SwitchDefaults.colors(checkedTrackColor = Color(0xFF6750A4))
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Fuente", fontSize = 16.sp, color = Color(0xFF49454F))
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                FilterChip(
                    selected = usaSerif,
                    onClick = { onUsaSerifChange(true) },
                    label = { Text("Serif") },
                    leadingIcon = if (usaSerif) { { Icon(Icons.Default.Check, contentDescription = null) } } else null
                )
                FilterChip(
                    selected = !usaSerif,
                    onClick = { onUsaSerifChange(false) },
                    label = { Text("Sans") },
                    leadingIcon = if (!usaSerif) { { Icon(Icons.Default.Check, contentDescription = null) } } else null
                )
            }
        }
    }
}