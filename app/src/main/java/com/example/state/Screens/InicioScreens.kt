package com.example.state.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.state.components.CampoTexto
import com.example.state.components.ConfirmacionScreen
import com.example.state.components.SelectorTaller
import com.example.state.viewmodel.InscripcionViewModel

@Composable
fun InicioScreen(
        modifier: Modifier = Modifier,
        vm: InscripcionViewModel = viewModel()
) {
        if (vm.inscrito) {
                ConfirmacionScreen(
                        nombre = vm.nombre,
                        correo = vm.correo,
                        edad = vm.edad,
                        taller = vm.taller,
                        onNueva = { vm.nuevaInscripcion() },
                        modifier = modifier
                )
        } else {
                Column(
                        modifier = modifier
                                .fillMaxSize()
                                .padding(24.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                        Text(
                                text = "Inscripción a talleres",
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.Black
                        )

                        Text(
                                text = "Casa del Lago • cupo limitado",
                                fontSize = 14.sp,
                                color = Color.Gray
                        )

                        Spacer(modifier = Modifier.height(4.dp))

                        CampoTexto(
                                label = "Nombre completo",
                                value = vm.nombre,
                                onValueChange = { vm.onNombreChange(it) },
                                error = vm.errorNombre
                        )

                        CampoTexto(
                                label = "Correo",
                                value = vm.correo,
                                onValueChange = { vm.onCorreoChange(it) },
                                error = vm.errorCorreo,
                                keyboardType = KeyboardType.Email
                        )

                        CampoTexto(
                                label = "Edad",
                                value = vm.edad,
                                onValueChange = { vm.onEdadChange(it) },
                                error = vm.errorEdad,
                                keyboardType = KeyboardType.Number
                        )

                        SelectorTaller(
                                opciones = vm.talleres,
                                seleccionado = vm.taller,
                                onSeleccionar = { vm.onTallerChange(it) }
                        )

                        Column {
                                Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        modifier = Modifier.fillMaxWidth()
                                ) {
                                        Checkbox(
                                                checked = vm.aceptaPrivacidad,
                                                onCheckedChange = { vm.onPrivacidadChange(it) }
                                        )
                                        Spacer(modifier = Modifier.width(8.dp))
                                        Text(
                                                text = "Acepto el aviso de privacidad",
                                                fontSize = 14.sp,
                                                color = Color.DarkGray
                                        )
                                }
                                if (vm.errorAviso != null) {
                                        Text(
                                                text = vm.errorAviso!!,
                                                color = Color.Red,
                                                fontSize = 12.sp,
                                                modifier = Modifier.padding(start = 16.dp)
                                        )
                                }
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        Button(
                                onClick = { vm.inscribir() },
                                modifier = Modifier
                                        .fillMaxWidth()
                                        .height(50.dp),
                                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6750A4))
                        ) {
                                Text(text = "Inscribirme", fontSize = 16.sp, color = Color.White)
                        }

                        Box(
                                modifier = Modifier.fillMaxWidth(),
                                contentAlignment = Alignment.Center
                        ) {
                                Text(
                                        text = "Quedan 6 lugares en este taller",
                                        fontSize = 12.sp,
                                        color = Color.Gray
                                )
                        }
                }
        }
}

@Preview(showBackground = true)
@Composable
fun ScreenPreview() {
        InicioScreen()
}