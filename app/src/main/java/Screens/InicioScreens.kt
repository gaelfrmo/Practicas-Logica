package Screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun InicioScreen(modifier: Modifier = Modifier) {
    var contador by rememberSaveable { mutableIntStateOf(0) }

    Column(modifier = modifier) {
        Text(text = "$contador")
        Button(onClick = { contador++ }) {
            Text("Sumar")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ScreenPreview() {
    InicioScreen()
}
