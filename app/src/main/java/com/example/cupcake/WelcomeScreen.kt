package com.example.cupcake

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun WelcomeScreen(navController: NavController) {
    Scaffold(
        content = { innerPadding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo), // Substitua com a sua imagem
                    contentDescription = "Background",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop // Ajusta para cobrir toda a tela
                )
                Column(
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Button(
                        onClick = {
                            // Navegar para a tela de login
                            navController.navigate("login")
                        },
                        modifier = Modifier.fillMaxWidth(0.7f)
                    ) {
                        Text("Entrar")
                    }
                    Button(
                        onClick = {
                            // Navegar para a tela de registro (implemente no futuro)
                            navController.navigate("register") // Substitua pela navegação correta
                        },
                        modifier = Modifier.fillMaxWidth(0.7f)
                    ) {
                        Text("Registrar")
                    }
                }
            }
        }
    )
}
