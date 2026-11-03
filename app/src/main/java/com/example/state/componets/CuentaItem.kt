package com.example.state.componets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.state.model.Cuenta

@Composable
fun StepperPersonas(personas: Int, onChange: (Int) -> Unit){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = "Personas", fontSize = 16.sp)

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            IconButton(
                onClick = { if (personas > 1) onChange(personas - 1) },
                enabled = personas > 1
            ) {
                Text(text = "–", fontSize = 18.sp)
            }

            Text(
                text = personas.toString(),
                fontSize = 16.sp,
                modifier = Modifier.padding(horizontal = 4.dp)
            )

            IconButton(
                onClick = { if (personas < 20) onChange(personas + 1) },
                enabled = personas < 20
            ) {
                Text(text = "+", fontSize = 18.sp)
            }
        }
    }
    Divider(modifier = Modifier)
}
