package com.example.state.Components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun nuevaTarea(valorText: String,
               onValueChange: (String) -> Unit,
               onAgregarClick: () -> Unit){
    Row(modifier = Modifier.padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        OutlinedTextField(
            value = valorText, onValueChange = onValueChange,
            label = { Text("Nueva tarea")}, modifier = Modifier.weight(1f))

        Button(onClick = onAgregarClick,
            enabled = valorText.isNotBlank()
            ,modifier = Modifier.height(50.dp).weight(.3f)
        ) {
            Text(text = "+" ,modifier = Modifier, fontSize = 30.sp)
        }
    }
}


