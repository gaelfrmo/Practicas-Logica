package com.example.state.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.state.components.FormularioGasto
import com.example.state.components.ListaGastos
import com.example.state.components.ResumenPresupuesto
import com.example.state.viewmodel.PresupuestoViewModel

@Composable
fun InicioScreens(
        modifier: Modifier = Modifier,
        vm: PresupuestoViewModel = viewModel()
) {
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
                        value = vm.ingresoTexto,
                        onValueChange = { vm.onIngresoChange(it) },
                        label = { Text("Ingreso Mensual (Beca)") },
                        modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(16.dp))
                ResumenPresupuesto(ingreso = vm.ingresoNum, gastos = vm.gastos)
                Spacer(modifier = Modifier.height(16.dp))
                FormularioGasto(
                        onAgregar = { concepto, monto, categoria ->
                                vm.agregarGasto(concepto, monto, categoria)
                        }
                )
                Spacer(modifier = Modifier.height(16.dp))
                ListaGastos(
                        gastos = vm.gastos,
                        onEliminar = { gastoAEliminar ->
                                vm.eliminarGasto(gastoAEliminar)
                        }
                )
        }
}

@Preview(showBackground = true)
@Composable
fun inicioScreenPreview() {
        InicioScreens()
}