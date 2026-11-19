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
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.state.componets.ResultadoCuenta
import com.example.state.componets.StepperPersonas
import com.example.state.viewmodel.CuentaViewModel

@Composable
fun InicioScreen(
    modifier: Modifier = Modifier,
    vm: CuentaViewModel = viewModel()
) {
    val resultado = vm.resultado

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(text = "Cuenta dividida", fontSize = 30.sp)
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = vm.montoTexto,
            onValueChange = { vm.onMontoChange(it) },
            label = { Text("Monto de la cuenta") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )
        Text(text = "Propina", fontSize = 19.sp)
        Row(
            modifier = Modifier.padding(2.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(onClick = { vm.onPropinaChange(10) }) {
                Text(text = "10%")
            }
            Button(onClick = { vm.onPropinaChange(15) }) {
                Text(text = "15%")
            }
            Button(onClick = { vm.onPropinaChange(20) }) {
                Text(text = "20%")
            }
            Button(onClick = { vm.onPropinaChange(25) }) {
                Text(text = "Otro")
            }
        }
        Text(text = "personas", fontSize = 19.sp)
        StepperPersonas(
            personas = vm.personas,
            onChange = { vm.onPersonasChange(it) }
        )
        Spacer(modifier = Modifier.height(16.dp))
        ResultadoCuenta(
            propina = resultado.propinaTotal,
            totalConPropina = resultado.totalConPropina,
            montoPorPersona = resultado.montoPorPersona,
            personas = vm.personas,
            sobrante = resultado.sobrantePorRedondeo
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ScreenPreview() {
    InicioScreen(modifier = Modifier)
}