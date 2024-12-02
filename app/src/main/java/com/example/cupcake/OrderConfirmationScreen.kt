package com.example.cupcake

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CheckCircle
import androidx.navigation.NavController


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrderConfirmationScreen(navController: NavController,userViewModel: UserViewModel) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Pedido Concluído") },
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
                    .padding(innerPadding),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                // Mensagem de pedido concluído
                Text(
                    text = "Seu pedido foi concluído com sucesso!",
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier.padding(16.dp)
                )

                // Imagem ou ícone de sucesso (opcional)
                Icon(
                    imageVector = Icons.Filled.CheckCircle,
                    contentDescription = "Sucesso",
                    modifier = Modifier.size(120.dp),
                    tint = MaterialTheme.colorScheme.primary
                )

                // Exibir nome e endereço do usuário ou um aviso
                if (userViewModel.name.value.isNotEmpty() && userViewModel.address.value.isNotEmpty()) {
                    Text(
                        text = "Nome: ${userViewModel.name.value}",
                        style = MaterialTheme.typography.headlineSmall,
                        modifier = Modifier.padding(8.dp)
                    )
                    Text(
                        text = "Endereço: ${userViewModel.address.value}",
                        style = MaterialTheme.typography.headlineSmall,
                        modifier = Modifier.padding(8.dp)
                    )
                } else {
                    Text(
                        text = "Aviso: Informações de usuário incompletas.",
                        style = MaterialTheme.typography.headlineSmall.copy(color = Color.Red),
                        modifier = Modifier.padding(8.dp)
                    )
                }

                Spacer(modifier = Modifier.height(32.dp))

                // Botão para voltar à tela inicial ou carrinho
                Button(
                    onClick = {navController.navigate("home") },
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text("Voltar para o catálogo")
                }
            }
        }
    )
}
