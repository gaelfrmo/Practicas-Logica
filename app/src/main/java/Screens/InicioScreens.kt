package Screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.sqrt

@Composable
fun InicioScreen(modifier: Modifier = Modifier) {
    var num1 by rememberSaveable { mutableStateOf("") }
    var num2 by rememberSaveable { mutableStateOf("") }
    var operacion by rememberSaveable { mutableStateOf("") }
    var resultado by rememberSaveable { mutableStateOf("") }

    val pantalla = when {
        resultado.isNotEmpty() -> resultado
        num1.isEmpty() && operacion.isEmpty() -> "0"
        else -> "$num1 $operacion $num2".trim()
    }

    fun onNumberClick(number: String) {
        if (resultado.isNotEmpty()) {
            resultado = ""
            num1 = if (number == ".") "0." else number
            operacion = ""
            num2 = ""
        } else if (operacion.isEmpty()) {
            if (number == "." && num1.contains(".")) return
            if (num1.isEmpty() && number == ".") {
                num1 = "0."
            } else {
                num1 += number
            }
        } else {
            if (number == "." && num2.contains(".")) return
            if (num2.isEmpty() && number == ".") {
                num2 = "0."
            } else {
                num2 += number
            }
        }
    }

    fun onOperatorClick(op: String) {
        if (op == "√") {
            val valor = (if (resultado.isNotEmpty()) resultado else if (num1.isNotEmpty()) num1 else "0").toDoubleOrNull()
            if (valor != null && valor >= 0) {
                val res = sqrt(valor)
                resultado = if (res % 1.0 == 0.0) res.toLong().toString() else res.toString()
                num1 = resultado
                num2 = ""
                operacion = ""
            } else {
                resultado = "Error"
            }
            return
        }

        if (resultado.isNotEmpty()) {
            num1 = resultado
            resultado = ""
            num2 = ""
        }
        if (num1.isNotEmpty()) {
            operacion = op
        }
    }

    fun onCalculate() {
        val n1 = num1.toDoubleOrNull()
        val n2 = num2.toDoubleOrNull()

        if (n1 != null && n2 != null && operacion.isNotEmpty()) {
            val res = when (operacion) {
                "+" -> n1 + n2
                "-" -> n1 - n2
                "*" -> n1 * n2
                "÷" -> if (n2 != 0.0) n1 / n2 else Double.NaN
                "%" -> n1 % n2
                else -> 0.0
            }

            resultado = if (res.isNaN()) {
                "Error"
            } else if (res % 1.0 == 0.0) {
                res.toLong().toString()
            } else {
                res.toString()
            }
        }
    }

    fun onClear() {
        num1 = ""
        num2 = ""
        operacion = ""
        resultado = ""
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(bottom = 16.dp),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.End
        ) {
            Text(
                text = pantalla,
                fontSize = 44.sp,
                textAlign = TextAlign.End,
                modifier = Modifier.fillMaxWidth()
            )
        }

        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Button(
                    modifier = Modifier.width(60.dp).height(50.dp),
                    onClick = { onClear() }
                ) { Text("C", fontSize = 20.sp) }

                Button(
                    modifier = Modifier.width(60.dp).height(50.dp),
                    onClick = { onOperatorClick("√") }
                ) { Text("√", fontSize = 20.sp) }

                Button(
                    modifier = Modifier.width(60.dp).height(50.dp),
                    onClick = { onOperatorClick("%") }
                ) { Text("%", fontSize = 20.sp) }

                Button(
                    modifier = Modifier.width(60.dp).height(50.dp),
                    onClick = { onOperatorClick("÷") }
                ) { Text("÷", fontSize = 20.sp) }
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Button(
                    modifier = Modifier.width(60.dp).height(50.dp),
                    onClick = { onNumberClick("7") }
                ) { Text("7", fontSize = 20.sp) }

                Button(
                    modifier = Modifier.width(60.dp).height(50.dp),
                    onClick = { onNumberClick("8") }
                ) { Text("8", fontSize = 20.sp) }

                Button(
                    modifier = Modifier.width(60.dp).height(50.dp),
                    onClick = { onNumberClick("9") }
                ) { Text("9", fontSize = 20.sp) }

                Button(
                    modifier = Modifier.width(60.dp).height(50.dp),
                    onClick = { onOperatorClick("*") }
                ) { Text("*", fontSize = 20.sp) }
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Button(
                    modifier = Modifier.width(60.dp).height(50.dp),
                    onClick = { onNumberClick("4") }
                ) { Text("4", fontSize = 20.sp) }

                Button(
                    modifier = Modifier.width(60.dp).height(50.dp),
                    onClick = { onNumberClick("5") }
                ) { Text("5", fontSize = 20.sp) }

                Button(
                    modifier = Modifier.width(60.dp).height(50.dp),
                    onClick = { onNumberClick("6") }
                ) { Text("6", fontSize = 20.sp) }

                Button(
                    modifier = Modifier.width(60.dp).height(50.dp),
                    onClick = { onOperatorClick("-") }
                ) { Text("-", fontSize = 20.sp) }
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Button(
                    modifier = Modifier.width(60.dp).height(50.dp),
                    onClick = { onNumberClick("1") }
                ) { Text("1", fontSize = 20.sp) }

                Button(
                    modifier = Modifier.width(60.dp).height(50.dp),
                    onClick = { onNumberClick("2") }
                ) { Text("2", fontSize = 20.sp) }

                Button(
                    modifier = Modifier.width(60.dp).height(50.dp),
                    onClick = { onNumberClick("3") }
                ) { Text("3", fontSize = 20.sp) }

                Button(
                    modifier = Modifier.width(60.dp).height(50.dp),
                    onClick = { onOperatorClick("+") }
                ) { Text("+", fontSize = 20.sp) }
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Button(
                    modifier = Modifier.width(60.dp).height(50.dp),
                    onClick = { onNumberClick("0") }
                ) { Text("0", fontSize = 20.sp) }

                Button(
                    modifier = Modifier.width(60.dp).height(50.dp),
                    onClick = { onNumberClick(".") }
                ) { Text(".", fontSize = 20.sp) }

                Button(
                    modifier = Modifier.width(160.dp).height(50.dp),
                    onClick = { onCalculate() }
                ) { Text("=", fontSize = 20.sp) }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun InicioPreview() {
    InicioScreen()
}
