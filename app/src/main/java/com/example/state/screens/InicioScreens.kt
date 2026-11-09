package com.example.state.screens

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import com.example.state.model.Producto
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.state.components.ProductoItem
import com.example.state.components.ResumenCarrito

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokemartScreen(modifier: Modifier = Modifier){
        val catalogo = remember { listOf(
                Producto("Pocion",20.0),
                Producto("Pokebola",25.0),
                Producto("Antidota",12.5),
                Producto("Revivir",60.0),
        )}
        val cantidades = remember { mutableStateListOf(0, 0,0,0) }
        val totalArticulo = cantidades.sum()
        val subtotal = catalogo.indices.sumOf { catalogo[it].precio * cantidades[it] }
        val descuento = if (subtotal >= 150.0) subtotal * 0.10 else 0.0
        val subtotalConDescuento = subtotal - descuento
        val envio = when {
                totalArticulo == 0 -> 0.0
                subtotalConDescuento >= 120.0 ->0.0
                else -> 15.0
        }
        val totalFinal = subtotalConDescuento + envio
        Scaffold(
                modifier = modifier,
                topBar = { CenterAlignedTopAppBar(title = {Text("Pokemart")})},
                bottomBar = {
                        ResumenCarrito(
                                articulos = totalArticulo,
                                subtotal = subtotal,
                                descuento = descuento,
                                envio = envio,
                                total = totalFinal,
                                onVaciarCarrito = { for (i in cantidades.indices){
                                cantidades[i]=0}
                                },
                                onConfirmarPedido = { cantidades.fill(0) }                        )
                }
        ) { paddingValues ->
                LazyColumn(modifier = Modifier.padding(paddingValues).fillMaxWidth()) {
                        items(catalogo.size) { index ->
                                ProductoItem(
                                        producto = catalogo[index],
                                        cantidad = cantidades[index],
                                        onCantidadChange = { nuevaCantidad ->
                                                cantidades[index] = nuevaCantidad
                                        }
                                )
                        }
                }
        }
}

@Preview(showBackground = true)
@Composable
fun PokemartScreenPreview(){
        PokemartScreen()
}