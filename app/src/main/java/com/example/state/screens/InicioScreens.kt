package com.example.state.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.state.model.Cuenta
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.state.componets.ResultadoCuenta
import com.example.state.componets.StepperPersonas

@Composable
fun InicioScreen(modifier: Modifier = Modifier) {
        var montoTexto by remember { mutableStateOf("") }
        var propinaPct by remember { mutableStateOf(20) }
        var personas by remember { mutableStateOf(1) }

        val monto = montoTexto.toDoubleOrNull() ?: 0.0
        val propinaTotal = monto * (propinaPct / 100.0)
        val totalConPropina = monto + propinaTotal
        val divisor = if (personas > 0) personas else 1
        val montoPorPersona = totalConPropina / divisor

        val resultado = Cuenta(
            propinaTotal = propinaTotal,
            totalConPropina = totalConPropina,
            montoPorPersona = montoPorPersona,
            sobrantePorRedondeo = 0.0
        )
        Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(text = "Cuenta dividida", fontSize = 30.sp)
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = montoTexto,
                onValueChange = { montoTexto = it },
                label = { Text("Monto de la cuenta") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )
            Text(text = "Propina", fontSize = 19.sp)
            Row(modifier= Modifier.padding(2.dp),
                horizontalArrangement = Arrangement.SpaceBetween) {
                Button(onClick = { propinaPct = 10 }) {
                    Text(text = "10%")
                }
                Button(onClick = { propinaPct = 15 }) {
                    Text(text = "15%")
                }
                Button(onClick = { propinaPct = 20 }) {
                    Text(text = "20%")
                }
                Button(onClick = { propinaPct = 25 }) {
                    Text(text = "Otro")
                }
            }
            Text(text = "personas",fontSize = 19.sp)
            StepperPersonas(
                personas = personas,
                onChange= {personas = it}
            )
            Spacer(modifier = Modifier.height(16.dp))
            ResultadoCuenta(
                propina = resultado.propinaTotal,
                totalConPropina = resultado.totalConPropina,
                montoPorPersona = resultado.montoPorPersona,
                personas = personas,
                sobrante = resultado.sobrantePorRedondeo
            )
        }
}

@Preview(showBackground = true)
@Composable
fun ScreenPreview() {
        InicioScreen(modifier = Modifier)
}