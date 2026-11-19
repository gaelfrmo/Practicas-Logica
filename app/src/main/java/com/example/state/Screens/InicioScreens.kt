package com.example.state.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.state.components.ControlesLectura
import com.example.state.components.VistaPrevia
import com.example.state.viewmodel.LecturaViewModel

@Composable
fun LecturaScreen(
        modifier: Modifier = Modifier,
        vm: LecturaViewModel = viewModel()
) {
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
                        tamano = vm.tamanoLetra,
                        onTamanoChange = { vm.actualizarTamano(it) },
                        interlineado = vm.interlineado,
                        onInterlineadoChange = { vm.actualizarInterlineado(it) },
                        modoOscuro = vm.modoOscuro,
                        onModoOscuroChange = { vm.actualizarModoOscuro(it) },
                        usaSerif = vm.usaSerif,
                        onUsaSerifChange = { vm.actualizarFuente(it) }
                )

                Spacer(modifier = Modifier.height(32.dp))

                VistaPrevia(
                        texto = "Era una noche tranquila en el pueblo cuando las campanas comenzaron a sonar, y nadie supo por qué.",
                        tamano = vm.tamanoLetra,
                        interlineado = vm.interlineado,
                        modoOscuro = vm.modoOscuro,
                        usaSerif = vm.usaSerif
                )

                Spacer(modifier = Modifier.weight(1f))

                OutlinedButton(
                        onClick = { vm.restablecer() },
                        modifier = Modifier
                                .fillMaxWidth()
                                .height(56.dp),
                        colors = ButtonDefaults.outlinedButtonColors(contentColor = Color(0xFF6750A4))
                ) {
                        Text("Restablecer", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                }
        }
}

@Preview(showBackground = true)
@Composable
fun ScreenPreview() {
        LecturaScreen()
}