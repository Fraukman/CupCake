package com.example.cupcake

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(navController: NavHostController, cart: List<Item>) {

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Catálogo") },
                actions = {
                    IconButton(onClick = { navController.navigate("register") }) {
                        Icon(
                            imageVector = androidx.compose.material.icons.Icons.Default.AccountCircle,
                            contentDescription = "Usuário"
                        )
                    }
                }
            )
        },
        content = { innerPadding ->
            Box(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
            ) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 72.dp) // Espaço reservado para os botões
                ) {
                    items(items) { item ->
                        ItemRow(item = item, onClick = { itemName ->
                            navController.navigate("selectedCupcake/$itemName")
                        })
                    }
                }

                // Botões na parte inferior
                Row(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Button(
                        onClick = {
                            navController.navigate("cart")
                        },
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("Carrinho (${cart.size})")
                    }

                    Spacer(modifier = Modifier.width(16.dp)) // Espaço entre os botões

                    Button(
                        onClick = { navController.navigate("customizeCupcake") },
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("Personalizar")
                    }
                }
            }
        }
    )
}

@Composable
fun ItemRow(item: Item,onClick: (String) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick(item.name) }
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(item.imageRes),
            contentDescription = item.name,
            modifier = Modifier
                .size(64.dp)
                .padding(8.dp),

            contentScale = ContentScale.Crop
        )
        Text(
            text = item.name,
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(8.dp),
            textAlign = TextAlign.Start
        )
    }
}

@Composable
fun ItemCard(item: Item, navController: NavController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                // Navegar para a tela de detalhes, passando o item inteiro
                navController.navigate("selected_cupcake/${item.name}")
            }
            .padding(8.dp)
    ) {
        Image(
            painter = painterResource(id = item.imageRes),
            contentDescription = item.name,
            modifier = Modifier.size(50.dp).padding(end = 16.dp)
        )
        Column {
            Text(text = item.name)
            Text(text = "Preço: R$ ${item.price}")
        }
    }
}

