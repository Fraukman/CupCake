package com.example.cupcake

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun ChatScreen() {
    var userMessage by remember { mutableStateOf("") }
    var messages by remember { mutableStateOf(listOf("Olá! Como posso te ajudar?")) }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Exibe as mensagens do chat
        Column(modifier = Modifier.weight(1f).fillMaxWidth()) {
            messages.forEach { message ->
                // Verifica se a mensagem é do usuário ou do sistema
                val isUserMessage = message.startsWith("Você:")

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    horizontalArrangement = if (isUserMessage) Arrangement.End else Arrangement.Start
                ) {
                    // Cria a bolha de mensagem
                    Box(
                        modifier = Modifier
                            .background(
                                color = if (isUserMessage) Color.Blue else Color.Gray,
                                shape = androidx.compose.foundation.shape.RoundedCornerShape(16.dp)
                            )
                            .padding(16.dp)
                    ) {
                        Text(
                            text = message,
                            color = Color.White,
                            style = TextStyle(fontSize = 16.sp)
                        )
                    }
                }
            }
        }

        // Campo de texto para o usuário digitar
        BasicTextField(
            value = userMessage,
            onValueChange = { userMessage = it },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Send
            ),
            keyboardActions = KeyboardActions(
                onSend = {
                    if (userMessage.isNotEmpty()) {
                        // Adiciona a mensagem do usuário à lista
                        messages = messages + "Você: $userMessage"

                        // Adiciona uma resposta automática
                        messages = messages + "Suporte: Estamos verificando o seu pedido."

                        // Limpa o campo de entrada
                        userMessage = ""
                    }
                }
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .background(Color.Gray.copy(alpha = 0.1f))
                .padding(16.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Botão de enviar mensagem
        Button(
            onClick = {
                if (userMessage.isNotEmpty()) {
                    // Adiciona a mensagem do usuário à lista
                    messages = messages + "Você: $userMessage"

                    // Adiciona uma resposta automática
                    messages = messages + "Suporte: Estamos verificando o seu pedido."

                    // Limpa o campo de entrada
                    userMessage = ""
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Enviar", fontSize = 18.sp)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ChatScreenPreview() {
    ChatScreen()
}