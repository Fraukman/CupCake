package com.example.cupcake

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(navController: NavController, userViewModel: UserViewModel) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Registrar") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Voltar")
                    }
                }
            )
        },
        content = { innerPadding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                contentAlignment = Alignment.TopCenter
            ) {
                var email by remember { mutableStateOf("") }
                var name by remember { mutableStateOf("") }
                var password by remember { mutableStateOf("") }
                var address by remember { mutableStateOf("") }
                var addressError by remember { mutableStateOf(false) } // Estado para controlar erro no endereço
                var nameError by remember { mutableStateOf(false) } // Estado para controlar erro no endereço
                var emailError by remember { mutableStateOf(false) } // Estado para controlar erro no endereço
                var passwordError by remember { mutableStateOf(false) } // Estado para controlar erro no endereço

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Campo de E-mail
                    OutlinedTextField(
                        value = email,
                        onValueChange = { email = it },
                        label = { Text("Email") },
                        isError = emailError,
                        modifier = Modifier.fillMaxWidth()
                    )

                    // Campo de Nome
                    OutlinedTextField(
                        value = name,
                        onValueChange = { name = it },
                        label = { Text("Nome") },
                        isError = nameError,
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth()
                    )

                    // Campo de Senha
                    OutlinedTextField(
                        value = password,
                        onValueChange = { password = it },
                        label = { Text("Senha") },
                        isError = passwordError,
                        singleLine = true,
                        visualTransformation = PasswordVisualTransformation(),
                        modifier = Modifier.fillMaxWidth()
                    )

                    // Campo de Endereço
                    OutlinedTextField(
                        value = address,
                        onValueChange = { address = it },
                        label = { Text("Endereço") },
                        singleLine = false,
                        isError = addressError, // Exibe erro se o campo estiver vazio
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(100.dp)
                    )

                    // Exibir mensagem de erro se o endereço estiver vazio
                    if (addressError) {
                        Text(
                            text = "Endereço é obrigatório!",
                            color = Color.Red,
                            style = MaterialTheme.typography.bodySmall,
                            modifier = Modifier.padding(top = 4.dp)
                        )
                    }
                    if (nameError) {
                        Text(
                            text = "Nome é obrigatório!",
                            color = Color.Red,
                            style = MaterialTheme.typography.bodySmall,
                            modifier = Modifier.padding(top = 4.dp)
                        )
                    }
                    if (passwordError) {
                        Text(
                            text = "Senha é obrigatório!",
                            color = Color.Red,
                            style = MaterialTheme.typography.bodySmall,
                            modifier = Modifier.padding(top = 4.dp)
                        )
                    }
                    if (emailError) {
                        Text(
                            text = "emails é obrigatório!",
                            color = Color.Red,
                            style = MaterialTheme.typography.bodySmall,
                            modifier = Modifier.padding(top = 4.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Botões na parte inferior
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Button(
                            onClick = {
                                if (address.isBlank()){
                                    addressError = true // Marca o erro caso o campo esteja vazio
                                }
                                if (email.isBlank()){
                                    emailError = true // Marca o erro caso o campo esteja vazio
                                }
                                if (password.isBlank()){
                                    passwordError = true // Marca o erro caso o campo esteja vazio
                                }
                                if (name.isBlank()){
                                    nameError = true // Marca o erro caso o campo esteja vazio
                                }else{
                                    userViewModel.name.value = name
                                    userViewModel.email.value = email
                                    userViewModel.address.value = address
                                    navController.navigate("home")
                                }

                            },
                            modifier = Modifier.weight(1f)
                        ) {
                            Text("Registrar")
                        }

                        Spacer(modifier = Modifier.width(16.dp))

                        Button(
                            onClick = {
                                // Lógica para suporte (substituir no futuro)
                                navController.navigate("support") // Substitua conforme necessário
                            },
                            modifier = Modifier.weight(1f)
                        ) {
                            Text("Suporte")
                        }
                    }
                }
            }
        }
    )
}
