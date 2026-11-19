package com.example.state.screens

import android.R
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.state.Components.camposItem
import com.example.state.models.loginEstado
import com.example.state.viewmodel.LoginViewModel

@Composable
fun InicioScreen(
        modifier: Modifier = Modifier,
        vm: LoginViewModel = viewModel()
) {
        when (vm.estadoLogin) {
                loginEstado.EXITO -> {
                        HomeScreen(
                                email = vm.textoCorreo,
                                onLogout = { vm.cerrarSesion() }
                        )
                }
                else -> {
                        Column(
                                modifier = modifier.fillMaxSize(),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                        ) {
                                Box(
                                        modifier = Modifier
                                                .clip(CircleShape)
                                                .size(90.dp)
                                                .background(colorResource(R.color.white)),
                                        contentAlignment = Alignment.Center
                                ) {
                                        Text(
                                                text = "+",
                                                fontSize = 50.sp,
                                                color = Color.Red
                                        )
                                }
                                Text(text = "Clinica Vida", fontSize = 30.sp)
                                Text(text = "Consulta tus citas medicas")
                                camposItem(
                                        Correo = vm.textoCorreo,
                                        onCorreoChange = { vm.onCorreoChange(it) },
                                        Contraseña = vm.textoContrasena,
                                        onContraseñaChange = { vm.onContrasenaChange(it) },
                                        recordarme = vm.recordarme,
                                        onRecordarmeChange = { vm.onRecordarmeChange(it) }
                                )

                                if (vm.mensajeError.isNotEmpty()) {
                                        Spacer(modifier = Modifier.height(4.dp))
                                        Text(text = vm.mensajeError, color = Color.Red, fontSize = 12.sp)
                                }
                                Spacer(modifier = Modifier.height(8.dp))
                                Button(
                                        onClick = { vm.iniciarSesion() },
                                        enabled = vm.estadoLogin != loginEstado.BLOQUEADO,
                                        modifier = Modifier
                                                .width(300.dp)
                                                .height(40.dp)
                                ) {
                                        Text(text = "Entrar", fontSize = 18.sp)
                                }
                                Spacer(modifier = Modifier.padding(10.dp))
                                Text(text = "Olvidaste tu contraseña?")
                        }
                }
        }
}

@Composable
fun HomeScreen(email: String, onLogout: () -> Unit) {
        Column(
                modifier = Modifier
                        .fillMaxSize()
                        .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
        ) {
                Text(text = "¡Bienvenido!", fontSize = 30.sp)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = email, fontSize = 16.sp, color = Color.Gray)

                Spacer(modifier = Modifier.height(24.dp))

                Button(
                        onClick = onLogout,
                        modifier = Modifier.width(200.dp)
                ) {
                        Text(text = "Cerrar sesión")
                }
        }
}

@Preview(showBackground = true)
@Composable
fun ScreenPreview() {
        InicioScreen(modifier = Modifier)
}