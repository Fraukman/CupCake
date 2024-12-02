package com.example.cupcake

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.navigation.NavController


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectedCupCakeScreen(itemName: String, navController: NavController?, cart: MutableList<Item>) {


    val item = items.find { it.name == itemName } ?: Item("Item Desconhecido", R.drawable.ic_launcher_foreground, 00.00f, "Desconhecido", "Desconhecido", 0)
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(item.name) }
            )
        },
        content = { innerPadding ->
            Box(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize(),
                contentAlignment = Alignment.TopCenter
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Nome do Cupcake no topo
                    Text(
                        text = item.name,
                        style = MaterialTheme.typography.headlineSmall,
                        modifier = Modifier.padding(vertical = 16.dp)
                    )

                    // Imagem Grande do Cupcake e detalhes ao lado
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(min = 200.dp)
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // Imagem do Cupcake
                        Image(
                            painter = painterResource(item.imageRes), // Substitua pela imagem do cupcake
                            contentDescription = item.name,
                            modifier = Modifier
                                .size(200.dp) // Imagem grande
                                .padding(end = 16.dp)
                        )

                        // Detalhes ao lado da imagem
                        Column(
                            horizontalAlignment = Alignment.Start
                        ) {
                            val formattedItemPrice = "R$ %.2f".format(item.price)
                            Text(
                                text = "Preço: ${formattedItemPrice}",
                                style = MaterialTheme.typography.bodyMedium
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = "Sabor: ${item.flavor}", // Exemplo de detalhe
                                style = MaterialTheme.typography.bodyMedium
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = "Tamanho: ${item.size}", // Exemplo de detalhe
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                    }

                    // Seção de Avaliações
                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "Avaliações:",
                        style = MaterialTheme.typography.headlineSmall,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )

                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // Ícones de Estrelas para Avaliação
                        StarRating(item.rating) // Aqui você pode ajustar o rating dinamicamente
                    }

                    // Espaço abaixo para empurrar o botão para a parte inferior
                    Spacer(modifier = Modifier.weight(1f))

                    // Botão Adicionar ao Carrinho
                    Button(
                        onClick = {
                            cart.add(item)
                            navController?.popBackStack()
                                  },
                        modifier = Modifier.fillMaxWidth().padding(horizontal = 32.dp)
                    ) {
                        Text(text = "Adicionar ao Carrinho")
                    }
                }
            }
        }
    )
}

@Composable
fun StarRating(rating: Int) {
    Row {
        for (i in 1..5) {
            if (i <= rating) {
                Icon(
                    imageVector = Icons.Filled.Star,
                    contentDescription = "Star",
                    modifier = Modifier.size(24.dp),
                    tint = Color.Yellow
                )
            } else {
                Icon(
                    imageVector = Icons.Filled.Star,
                    contentDescription = "Empty Star",
                    modifier = Modifier.size(24.dp),
                    tint = Color.Gray
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SelectedCupcakeScreenPreview() {
    // Item de exemplo para a preview
    val sampleItem = Item(
        name = "Cupcake Chocolate",
        imageRes = R.drawable.ic_launcher_background, // Certifique-se de que `sample_image` exista
        price = 15.00f,
        flavor = "Chocolate",
        size = "Grande",
        rating = 4
    )

    // Mock para o carrinho (não persistente na preview)
    val mockCart = mutableListOf<Item>()

    // Layout da preview
    SelectedCupCakeScreen(
        itemName = sampleItem.name,
        navController = null, // Passando null, pois a navegação não é relevante na preview
        cart = mockCart
    )
}