package Screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun InicioScreen(modifier: Modifier = Modifier) {
        var nombre by remember { mutableStateOf("Ana Lopez") }
        var correo by remember { mutableStateOf("ana.lopez@") }
        var edad by remember { mutableStateOf("25") }
        var taller by remember { mutableStateOf("Fotografía") }
        var aceptaPrivacidad by remember { mutableStateOf(true) }

        val correoValido = android.util.Patterns.EMAIL_ADDRESS.matcher(correo).matches()

        Column(
                modifier = modifier
                        .fillMaxSize()
                        .padding(24.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
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

                OutlinedTextField(
                        value = nombre,
                        onValueChange = { nombre = it },
                        label = { Text("Nombre completo") },
                        modifier = Modifier.fillMaxWidth()
                )

                Column(modifier = Modifier.fillMaxWidth()) {
                        OutlinedTextField(
                                value = correo,
                                onValueChange = { correo = it },
                                label = { Text("Correo") },
                                isError = !correoValido && correo.isNotEmpty(),
                                modifier = Modifier.fillMaxWidth(),
                                colors = OutlinedTextFieldDefaults.colors(
                                        focusedBorderColor = if (!correoValido && correo.isNotEmpty()) Color.Red else MaterialTheme.colorScheme.primary,
                                        unfocusedBorderColor = if (!correoValido && correo.isNotEmpty()) Color.Red else Color.Gray
                                )
                        )
                        if (!correoValido && correo.isNotEmpty()) {
                                Text(
                                        text = "Ingresa un correo válido",
                                        color = Color.Red,
                                        fontSize = 12.sp,
                                        modifier = Modifier.padding(start = 4.dp, top = 4.dp)
                                )
                        }
                }

                OutlinedTextField(
                        value = edad,
                        onValueChange = { edad = it },
                        label = { Text("Edad") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                        value = taller,
                        onValueChange = { taller = it },
                        label = { Text("Taller") },
                        trailingIcon = {
                                Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = "Desplegar")
                        },
                        modifier = Modifier.fillMaxWidth(),
                        readOnly = true
                )

                Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                ) {
                        Checkbox(
                                checked = aceptaPrivacidad,
                                onCheckedChange = { aceptaPrivacidad = it }
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                                text = "Acepto el aviso de privacidad",
                                fontSize = 14.sp,
                                color = Color.DarkGray
                        )
                }

                Spacer(modifier = Modifier.height(8.dp))

                Button(
                        onClick = { },
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

@Preview(showBackground = true)
@Composable
fun ScreenPreview() {
        InicioScreen()
}