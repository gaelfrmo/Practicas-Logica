package Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

enum class Interlineado(val factor: Float) {
        COMPACTO(1.0f), NORMAL(1.3f), AMPLIO(1.6f)
}

@Composable
fun LecturaScreen(modifier: Modifier = Modifier) {
        var tamanoLetra by rememberSaveable { mutableFloatStateOf(22f) }
        var interlineado by rememberSaveable { mutableStateOf(Interlineado.NORMAL) }
        var modoOscuro by rememberSaveable { mutableStateOf(true) }
        var usaSerif by rememberSaveable { mutableStateOf(true) }

        Column(
                modifier = modifier
                        .fillMaxSize()
                        .background(Color(0xFFFCF8FF))
                        .padding(24.dp)
        ) {
                Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                ) {
                        Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Volver",
                                modifier = Modifier.size(24.dp)
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        Text(
                                text = "Ajustes de lectura",
                                fontSize = 22.sp,
                                color = Color(0xFF1D1B20)
                        )
                }

                Spacer(modifier = Modifier.height(32.dp))

                ControlesLectura(
                        tamano = tamanoLetra,
                        onTamanoChange = { tamanoLetra = it },
                        interlineado = interlineado,
                        onInterlineadoChange = { interlineado = it },
                        modoOscuro = modoOscuro,
                        onModoOscuroChange = { modoOscuro = it },
                        usaSerif = usaSerif,
                        onUsaSerifChange = { usaSerif = it }
                )

                Spacer(modifier = Modifier.height(32.dp))

                VistaPrevia(
                        texto = "Era una noche tranquila en el pueblo cuando las campanas comenzaron a sonar, y nadie supo por qué.",
                        tamano = tamanoLetra,
                        interlineado = interlineado,
                        modoOscuro = modoOscuro,
                        usaSerif = usaSerif
                )

                Spacer(modifier = Modifier.weight(1f))

                OutlinedButton(
                        onClick = {
                                tamanoLetra = 22f
                                interlineado = Interlineado.NORMAL
                                modoOscuro = true
                                usaSerif = true
                        },
                        modifier = Modifier
                                .fillMaxWidth()
                                .height(56.dp),
                        colors = ButtonDefaults.outlinedButtonColors(contentColor = Color(0xFF6750A4))
                ) {
                        Text("Restablecer", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                }
        }
}

@Composable
fun ControlesLectura(
        tamano: Float, onTamanoChange: (Float) -> Unit,
        interlineado: Interlineado, onInterlineadoChange: (Interlineado) -> Unit,
        modoOscuro: Boolean, onModoOscuroChange: (Boolean) -> Unit,
        usaSerif: Boolean, onUsaSerifChange: (Boolean) -> Unit
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
                        Interlineado.entries.forEach { opcion -> // <-- AQUÍ SE USA ENTRIES EN LUGAR DE VALUES()
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

@Composable
fun VistaPrevia(
        texto: String, tamano: Float, interlineado: Interlineado,
        modoOscuro: Boolean, usaSerif: Boolean
) {
        Column {
                Text(
                        text = "VISTA PREVIA",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF49454F)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Box(
                        modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(16.dp))
                                .background(if (modoOscuro) Color(0xFF212124) else Color.White)
                                .padding(24.dp)
                ) {
                        Text(
                                text = texto,
                                fontSize = tamano.sp,
                                lineHeight = (tamano * interlineado.factor).sp,
                                color = if (modoOscuro) Color(0xFFE6E1E5) else Color(0xFF1D1B20),
                                fontFamily = if (usaSerif) FontFamily.Serif else FontFamily.SansSerif
                        )
                }
        }
}

@Preview(showBackground = true)
@Composable
fun ScreenPreview() {
        LecturaScreen()
}