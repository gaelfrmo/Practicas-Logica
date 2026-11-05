package com.example.state.screens

import android.R.attr.enabled
import androidx.compose.foundation.background
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
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.unit.dp
import com.example.state.Components.listaTareas
import com.example.state.Components.nuevaTarea


@Composable
fun InicioScreen(modifier: Modifier = Modifier) {
        val tarea = remember { mutableStateListOf("Entregar proyecto POO","Leer capitulo 4","Taller de calculo","Preparar expocision" ) }
        var texto by remember { mutableStateOf("") }
        val mostrarDialogo = remember { mutableStateOf(false) }
        val filtro = remember { mutableStateOf("") }

        Column(modifier = modifier.fillMaxSize(),horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "Mis pendientes", fontSize = 20.sp)

                Spacer(modifier = Modifier.padding(16.dp))

                nuevaTarea(
                        valorText = texto, onValueChange = { texto = it },
                        onAgregarClick = {
                                if (texto.isNotBlank()){
                                        tarea.add(texto)
                                        texto = ""
                                }
                        }
                )
                Row(modifier=Modifier.padding(10.dp).height(40.dp), verticalAlignment = Alignment.CenterVertically) {
                        Text(text= "Prioridad   ", fontSize = 20.sp)
                        Button(onClick = {}) {
                                Text(text = "√ Alta", fontSize = 17.sp)
                        }
                        Button(onClick = {}) {
                                Text(text = "Media",fontSize = 17.sp)
                        }
                        Button(onClick = {}) {
                                Text(text = "Baja", fontSize = 17.sp)
                        }
                        Spacer(modifier= Modifier.padding(16.dp))
                }
                Row(modifier=Modifier.padding(10.dp).height(40.dp), verticalAlignment = Alignment.CenterVertically)  {
                        Button(onClick = {}, modifier= Modifier ) {
                                Text(text = "√ Todas", fontSize = 17.sp)
                        }
                        Button(onClick = {}) {
                                Text(text = "Pendientes",fontSize = 17.sp)
                        }
                        Button(onClick = {}) {
                                Text(text = "Hechas", fontSize = 17.sp)
                        }
                }
                Divider(modifier = Modifier.padding(12.dp))
                LazyColumn(modifier = Modifier.fillMaxWidth().weight(1f).padding(horizontal = 16.dp)) {
                        items(tarea) { unaTarea ->
                                Divider(modifier = Modifier.padding(16.dp))
                                listaTareas(textTarea = unaTarea, onEliminarClick = {tarea.remove(unaTarea)})
                        }
                }
                Text(text = "3 pendientes de 4")
                Divider(modifier = Modifier.padding(16.dp))
                Button(onClick = {  }, modifier = Modifier, ) {
                        Text(text = "Borrar completadas")
                }
        }
}

@Preview(showBackground = true)
@Composable
fun ScreenPreview() {
        InicioScreen(modifier = Modifier)
}