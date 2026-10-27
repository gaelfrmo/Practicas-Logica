package Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun InicioScreen(modifier: Modifier = Modifier) {
        var board by remember { mutableStateOf(List(9) { "" }) }
        var currentPlayer by remember { mutableStateOf("X") }
        var winner by remember { mutableStateOf<String?>(null) }

        fun checkGameStatus(currentBoard: List<String>): String? {
                val winningCombinations = listOf(
                        listOf(0, 1, 2), listOf(3, 4, 5), listOf(6, 7, 8),
                        listOf(0, 3, 6), listOf(1, 4, 7), listOf(2, 5, 8),
                        listOf(0, 4, 8), listOf(2, 4, 6)
                )

                for (combo in winningCombinations) {
                        val (a, b, c) = combo
                        if (currentBoard[a].isNotEmpty() && currentBoard[a] == currentBoard[b] && currentBoard[a] == currentBoard[c]) {
                                return currentBoard[a]
                        }
                }

                return if (currentBoard.none { it.isEmpty() }) "Empate" else null
        }

        fun resetGame() {
                board = List(9) { "" }
                currentPlayer = "X"
                winner = null
        }

        Column(
                modifier = modifier
                        .fillMaxSize()
                        .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
        ) {
                Text(
                        text = "Juego del Gato",
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF333333)
                )

                Spacer(modifier = Modifier.height(16.dp))

                val statusText = when (winner) {
                        "Empate" -> "¡Es un empate!"
                        "X", "O" -> "¡Ganador: $winner!"
                        else -> "Turno de: $currentPlayer"
                }

                Text(
                        text = statusText,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Medium,
                        color = if (winner != null && winner != "Empate") Color(0xFF2E7D32) else Color.DarkGray
                )

                Spacer(modifier = Modifier.height(24.dp))

                Column(
                        modifier = Modifier
                                .fillMaxWidth(0.9f)
                                .aspectRatio(1f)
                                .background(Color(0xFF333333), shape = RoundedCornerShape(12.dp))
                                .padding(8.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                        for (row in 0..2) {
                                Row(
                                        modifier = Modifier
                                                .fillMaxWidth()
                                                .weight(1f),
                                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                                ) {
                                        for (col in 0..2) {
                                                val index = row * 3 + col
                                                Box(
                                                        modifier = Modifier
                                                                .weight(1f)
                                                                .fillMaxSize()
                                                                .clip(RoundedCornerShape(8.dp))
                                                                .background(Color.White)
                                                                .clickable {
                                                                        if (board[index].isEmpty() && winner == null) {
                                                                                val newBoard = board.toMutableList()
                                                                                newBoard[index] = currentPlayer
                                                                                board = newBoard

                                                                                val gameResult = checkGameStatus(newBoard)
                                                                                if (gameResult != null) {
                                                                                        winner = gameResult
                                                                                } else {
                                                                                        currentPlayer = if (currentPlayer == "X") "O" else "X"
                                                                                }
                                                                        }
                                                                },
                                                        contentAlignment = Alignment.Center
                                                ) {
                                                        Text(
                                                                text = board[index],
                                                                fontSize = 48.sp,
                                                                fontWeight = FontWeight.Bold,
                                                                color = when (board[index]) {
                                                                        "X" -> Color(0xFFE53935)
                                                                        "O" -> Color(0xFF1E88E5)
                                                                        else -> Color.Black
                                                                }
                                                        )
                                                }
                                        }
                                }
                        }
                }

                Spacer(modifier = Modifier.height(32.dp))

                Button(
                        onClick = { resetGame() },
                        modifier = Modifier.size(width = 180.dp, height = 50.dp)
                ) {
                        Text(text = "Reiniciar", fontSize = 18.sp)
                }
        }
}

@Preview(showBackground = true)
@Composable
fun ScreenPreview() {
        InicioScreen(modifier = Modifier)
}