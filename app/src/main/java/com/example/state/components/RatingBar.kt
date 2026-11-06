package com.example.state.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun RatingBar(
    title: String,
    rating: Int,
    onRatingChange: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val descripcion = when (rating) {
        1 -> "Malo"
        2 -> "Regular"
        3 -> "Bueno"
        4 -> "Muy bueno"
        5 -> "Excelente"
        else -> "Sin calificar"
    }

    Column(modifier = modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = title, fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
        Spacer(modifier = Modifier.height(4.dp))
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            for (i in 1..5) {
                Text(
                    text = if (i <= rating) "★" else "☆",
                    fontSize = 30.sp,
                    color = if (i <= rating) Color(0xFFFFC107) else Color.Gray,
                    modifier = Modifier
                        .clickable { onRatingChange(i) }
                        .padding(horizontal = 4.dp)
                )
            }
        }
        Spacer(modifier = Modifier.height(2.dp))
        Text(text = descripcion, fontSize = 13.sp, color = Color.DarkGray)
    }
}