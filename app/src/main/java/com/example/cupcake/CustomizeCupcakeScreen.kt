package com.example.cupcake

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomizeCupcakeScreen(navController: NavController, cart: MutableList<Item>) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Personalizar Cupcake") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Voltar")
                    }
                }
            )
        },
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                var selectedBaseFlavor by remember { mutableStateOf("") }
                var selectedFrostingFlavor by remember { mutableStateOf("") }
                var customMessage by remember { mutableStateOf("") }

                // Campos de personalização
                Text(text = "Escolha o sabor da massa", style = MaterialTheme.typography.headlineSmall)
                DropdownMenuField(
                    items = listOf("Baunilha", "Chocolate", "Red Velvet"),
                    selectedItem = selectedBaseFlavor,
                    onItemSelected = { selectedBaseFlavor = it }
                )

                Text(text = "Escolha o sabor da cobertura", style = MaterialTheme.typography.headlineSmall)
                DropdownMenuField(
                    items = listOf("Morango", "Chocolate", "Limão", "Doce de Leite"),
                    selectedItem = selectedFrostingFlavor,
                    onItemSelected = { selectedFrostingFlavor = it }
                )

                Text(text = "Mensagem personalizada", style = MaterialTheme.typography.headlineSmall)
                OutlinedTextField(
                    value = customMessage,
                    onValueChange = { customMessage = it },
                    placeholder = { Text("Escreva sua mensagem aqui...") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.weight(1f)) // Empurra os botões para o final

                // Botões
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Button(onClick = { navController.popBackStack() }) {
                        Text("Cancelar")
                    }
                    Button(onClick = {
                        val customizedCupcake = Item(
                            name = "Cupcake Personalizado",
                            imageRes = R.drawable.ic_launcher_background, // Substitua com uma imagem genérica ou personalizada
                            price = 15.00f, // Defina um preço fixo ou calcule dinamicamente
                            flavor = "Personalizado",
                            size = "Único",
                            rating = 5, // Valor padrão para itens personalizados
                            baseFlavor = selectedBaseFlavor,
                            frostingFlavor = selectedFrostingFlavor,
                            customMessage = customMessage
                        )
                        cart.add(customizedCupcake) // Adiciona ao carrinho
                        navController.popBackStack() // Volta para a tela anterior
                    }) {
                        Text("Confirmar")
                    }
                }
            }
        }
    )
}


@Composable
fun DropdownMenuField(
    items: List<String>,
    selectedItem: String,
    onItemSelected: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    Box {
        OutlinedTextField(
            value = selectedItem,
            onValueChange = { },
            readOnly = true,
            placeholder = { Text("Selecione...") },
            trailingIcon = {
                IconButton(onClick = { expanded = !expanded }) {
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "Expandir")
                }
            },
            modifier = Modifier.fillMaxWidth()
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            items.forEach { item ->
                DropdownMenuItem(
                    onClick = {
                    onItemSelected(item)
                    expanded = false
                },
                    text = {Text(text = item)})
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CustomizeCupcakeScreenPreview() {
    val dummyCart = remember { mutableStateListOf<Item>() } // Simula o carrinho
    val navController = rememberNavController() // Simula o controle de navegação

    CustomizeCupcakeScreen(navController = navController, cart = dummyCart)
}