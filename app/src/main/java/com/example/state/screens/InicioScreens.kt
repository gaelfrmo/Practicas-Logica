package com.example.state.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.state.Components.listaTareas
import com.example.state.Components.nuevaTarea
import com.example.state.viewmodel.TareasViewModel

@Composable
fun InicioScreen(
        modifier: Modifier = Modifier,
        vm: TareasViewModel = viewModel()
) {
        Column(
                modifier = modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
        ) {
                Text(text = "Mis pendientes", fontSize = 20.sp)

                Spacer(modifier = Modifier.padding(16.dp))

                nuevaTarea(
                        valorText = vm.texto,
                        onValueChange = { vm.onTextoChange(it) },
                        onAgregarClick = { vm.agregarTarea() }
                )

                Row(
                        modifier = Modifier
                                .padding(10.dp)
                                .height(40.dp),
                        verticalAlignment = Alignment.CenterVertically
                ) {
                        Text(text = "Prioridad   ", fontSize = 20.sp)
                        Button(onClick = { vm.onPrioridadChange("Alta") }) {
                                Text(
                                        text = if (vm.prioridadSeleccionada == "Alta") "√ Alta" else "Alta",
                                        fontSize = 17.sp
                                )
                        }
                        Button(onClick = { vm.onPrioridadChange("Media") }) {
                                Text(
                                        text = if (vm.prioridadSeleccionada == "Media") "√ Media" else "Media",
                                        fontSize = 17.sp
                                )
                        }
                        Button(onClick = { vm.onPrioridadChange("Baja") }) {
                                Text(
                                        text = if (vm.prioridadSeleccionada == "Baja") "√ Baja" else "Baja",
                                        fontSize = 17.sp
                                )
                        }
                        Spacer(modifier = Modifier.padding(16.dp))
                }

                Row(
                        modifier = Modifier
                                .padding(10.dp)
                                .height(40.dp),
                        verticalAlignment = Alignment.CenterVertically
                ) {
                        Button(onClick = { vm.onFiltroChange("Todas") }) {
                                Text(
                                        text = if (vm.filtroSeleccionado == "Todas") "√ Todas" else "Todas",
                                        fontSize = 17.sp
                                )
                        }
                        Button(onClick = { vm.onFiltroChange("Pendientes") }) {
                                Text(
                                        text = if (vm.filtroSeleccionado == "Pendientes") "√ Pendientes" else "Pendientes",
                                        fontSize = 17.sp
                                )
                        }
                        Button(onClick = { vm.onFiltroChange("Hechas") }) {
                                Text(
                                        text = if (vm.filtroSeleccionado == "Hechas") "√ Hechas" else "Hechas",
                                        fontSize = 17.sp
                                )
                        }
                }

                HorizontalDivider(modifier = Modifier.padding(12.dp))

                LazyColumn(
                        modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f)
                                .padding(horizontal = 16.dp)
                ) {
                        items(vm.tareas) { unaTarea ->
                                HorizontalDivider(modifier = Modifier.padding(16.dp))
                                listaTareas(
                                        textTarea = unaTarea,
                                        onEliminarClick = { vm.eliminarTarea(unaTarea) }
                                )
                        }
                }

                Text(text = "${vm.tareas.size} pendientes")
                HorizontalDivider(modifier = Modifier.padding(16.dp))
                Button(onClick = { vm.borrarCompletadas() }) {
                        Text(text = "Borrar completadas")
                }
        }
}

@Preview(showBackground = true)
@Composable
fun ScreenPreview() {
        InicioScreen(modifier = Modifier)
}