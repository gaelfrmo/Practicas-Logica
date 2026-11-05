package com.example.state.Components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun listaTareas(textTarea: String,
                onEliminarClick:() -> Unit) {
    Card(modifier = Modifier.fillMaxSize()) {
        Text(text = textTarea, modifier = Modifier, fontSize = 30.sp)
    }
    Button(onClick = onEliminarClick,
        enabled = textTarea.isNotBlank()
        ,modifier = Modifier.height(40.dp)) {
        Text(text = "Eliminar" ,modifier = Modifier, fontSize = 20.sp)
    }
}