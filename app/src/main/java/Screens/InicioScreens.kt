package Screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun InicioScreen(modifier: Modifier = Modifier) {
        var xtext by remenber { mutableStateOf("")}
        var ytext by remenber { mutableStateOf("")}
        Column(modifier = modifier) {
                TextField(
                        value = xtext,
                        onValueChange = {
                                miclick()
                        }
                )
                Button(onClick = miclick) {
                        Text(text = "Sumar")
                }
        }
}


@Preview(showBackground = true)
@Composable
fun ScreenPreview() {
        InicioScreen(modifier = Modifier)
}