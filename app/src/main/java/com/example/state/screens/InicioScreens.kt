package com.example.state.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.state.components.FormularioGasto
import com.example.state.components.ListaGastos
import com.example.state.components.ResumenPresupuesto
import com.example.state.model.Gasto

@Composable
fun InicioScreens(modifier: Modifier = Modifier) {
        var ingresoTexto by remember { mutableStateOf("4500") }
        val gastos = remember { mutableStateListOf<Gasto>() }
        var idCounter by remember { mutableStateOf(1) }

        val ingresoNum = ingresoTexto.toDoubleOrNull() ?: 0.0

        Column(
                modifier = modifier
                        .fillMaxSize()
                        .padding(16.dp)
                        .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
        ) {
                Text(text = "Mi presupuesto", fontSize = 22.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(16.dp))
                OutlinedTextField(
                        value = ingresoTexto,
                        onValueChange = { ingresoTexto = it },
                        label = { Text("Ingreso Mensual (Beca)") },
                        modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(16.dp))
                ResumenPresupuesto(ingreso = ingresoNum, gastos = gastos)
                Spacer(modifier = Modifier.height(16.dp))
                FormularioGasto(
                        onAgregar = { concepto, monto, categoria ->
                                val nuevoGasto = Gasto(
                                        id = idCounter++,
                                        concepto = concepto,
                                        monto = monto,
                                        categoria = categoria
                                )
                                gastos.add(nuevoGasto)
                        }
                )
                Spacer(modifier = Modifier.height(16.dp))
                ListaGastos(
                        gastos = gastos,
                        onEliminar = { gastoAEliminar ->
                                gastos.remove(gastoAEliminar)
                        }
                )
        }
}
@Preview(showBackground = true)
@Composable
fun inicioScreenPreview(){
        InicioScreens()
}
