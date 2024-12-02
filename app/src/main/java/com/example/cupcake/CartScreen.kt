package com.example.cupcake

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController

@Composable
fun CartScreen(navController: NavController, cart: MutableList<Item> ,userViewModel: UserViewModel) {
    var totalPrice by remember { mutableStateOf(0f) }

    // Calcular o preço total
    LaunchedEffect(cart) {
        totalPrice = cart.map { it.price }.sum()
    }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {
        // Exibe os itens do carrinho
        cart.forEach { item ->  // Usando forEach aqui
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // Exibe o nome e preço do item
                Column(modifier = Modifier.weight(1f)) {
                    Text(text = item.name, fontSize = 18.sp)
                    Text(text = "Preço: R$ ${item.price}", fontSize = 16.sp)
                }

                // Botão de remoção
                IconButton(onClick = { cart.remove(item)
                }) {
                    Icon(imageVector = Icons.Filled.Delete, contentDescription = "Remover item")
                }
            }
        }

        // Exibe o valor total
        Text(
            text = "Total: R$ ${"%.2f".format(totalPrice)}",
            fontSize = 18.sp,
            modifier = Modifier.align(Alignment.End)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Botão de finalizar pedido
        Button(onClick = {
            navController.navigate("orderConfirmation")
        }, modifier = Modifier.fillMaxWidth()) {
            Text("Finalizar Pedido", fontSize = 18.sp)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CartScreenPreview() {

}
