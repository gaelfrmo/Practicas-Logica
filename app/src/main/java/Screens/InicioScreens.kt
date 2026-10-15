package Screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun InicioScreen(modifier: Modifier = Modifier) {
    var num1 by rememberSaveable { mutableStateOf("") }
    var num2 by rememberSaveable { mutableStateOf("") }
    var operacion by rememberSaveable { mutableStateOf("") }
    var resultado by rememberSaveable { mutableStateOf("") }
    fun operacion() {
        when (operacion) {
            "+" -> resultado = (num1.toInt() + num2.toInt()).toString()
            "-" -> resultado = (num1.toInt() - num2.toInt()).toString()
            "*" -> resultado = (num1.toInt() * num2.toInt()).toString()
            "/" -> resultado = (num1.toInt() / num2.toInt()).toString()
        }
    }
    val pantalla = "$num1 $operacion $num2"

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Bottom
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Button(
                modifier = Modifier
                    .width(60.dp)
                    .height(50.dp),
                onClick = { num1 += "C" }) { Text("C", fontSize = 20.sp) }
            Button(
                modifier = Modifier
                    .width(60.dp)
                    .height(50.dp),
                onClick = { num1 += "√" }) { Text("√", fontSize = 20.sp) }
            Button(
                modifier = Modifier
                    .width(60.dp)
                    .height(50.dp),
                onClick = { num1 += "%" }) { Text("%", fontSize = 20.sp) }
            Button(
                modifier = Modifier
                    .width(60.dp)
                    .height(50.dp),
                onClick = { num1 += "÷" }) { Text("÷", fontSize = 20.sp) }
        }

        Spacer(modifier = Modifier.padding(10.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Button(
                modifier = Modifier
                    .width(60.dp)
                    .height(50.dp),
                onClick = { num1 += "4" }) { Text("7", fontSize = 20.sp) }
            Button(
                modifier = Modifier
                    .width(60.dp)
                    .height(50.dp),
                onClick = { num1 += "5" }) { Text("8", fontSize = 20.sp) }
            Button(
                modifier = Modifier
                    .width(60.dp)
                    .height(50.dp),
                onClick = { num1 += "6" }) { Text("9", fontSize = 20.sp) }
            Button(
                modifier = Modifier
                    .width(60.dp)
                    .height(50.dp),
                onClick = { num1 += "+" }) { Text("x", fontSize = 20.sp) }
        }

        Spacer(modifier = Modifier.padding(10.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Button(
                modifier = Modifier
                    .width(60.dp)
                    .height(50.dp),
                onClick = { num1 += "7" }) { Text("4", fontSize = 20.sp) }
            Button(
                modifier = Modifier
                    .width(60.dp)
                    .height(50.dp),
                onClick = { num1 += "8" }) { Text("5", fontSize = 20.sp) }
            Button(
                modifier = Modifier
                    .width(60.dp)
                    .height(50.dp),
                onClick = { num1 += "9" }) { Text("6", fontSize = 20.sp) }
            Button(
                modifier = Modifier
                    .width(60.dp)
                    .height(50.dp),
                onClick = { num1 += "-" }) { Text("-", fontSize = 20.sp) }
        }

        Spacer(modifier = Modifier.padding(10.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Button(
                modifier = Modifier
                    .width(60.dp)
                    .height(50.dp),
                onClick = { num1 += "7" }) { Text("1", fontSize = 20.sp) }
            Button(
                modifier = Modifier
                    .width(60.dp)
                    .height(50.dp),
                onClick = { num1 += "8" }) { Text("2", fontSize = 20.sp) }
            Button(
                modifier = Modifier
                    .width(60.dp)
                    .height(50.dp),
                onClick = { num1 += "9" }) { Text("3", fontSize = 20.sp) }
            Button(
                modifier = Modifier
                    .width(60.dp)
                    .height(50.dp),
                onClick = { num1 += "-" }) { Text("+", fontSize = 20.sp) }
        }

        Spacer(modifier = Modifier.padding(10.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Button(
                modifier = Modifier
                    .width(60.dp)
                    .height(50.dp),
                onClick = { num1 += "7" }) { Text("0", fontSize = 20.sp) }
            Button(
                modifier = Modifier
                    .width(60.dp)
                    .height(50.dp),
                onClick = { num1 += "8" }) { Text(",", fontSize = 20.sp) }
            Button(
                modifier = Modifier
                    .width(160.dp)
                    .height(50.dp),
                onClick = { num1 += "9" }) { Text("=", fontSize = 20.sp) }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun InicioPreview() {
    InicioScreen()
}
