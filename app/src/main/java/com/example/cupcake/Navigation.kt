package com.example.cupcake

import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.material3.Shapes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

data class Item(val name: String,
                val imageRes: Int,
                val price: Float,
                val flavor: String,
                val size: String,
                val rating: Int,
                val baseFlavor: String = "", // Novo campo: sabor da massa
                val frostingFlavor: String = "", // Novo campo: sabor da cobertura
                val customMessage: String = "" // Novo campo: mensagem personalizada
)

val items = listOf(
    Item("Cupcake Chocolate", R.drawable.choco,15.00f,"Chocolate","Medio",5), // Substitua com suas imagens reais
    Item("Cupcake Prestigio", R.drawable.prestige,25.00f,"Prestigio","Pequeno",3),
    Item("Cupcake Jiló", R.drawable.jilo,10.00f,"Jiló","Grande",2),
    Item("Cupcake Doce de Leite", R.drawable.doce,10.00f,"Doce de Leite","Pequeno",4),
    Item("Cupcake Morango", R.drawable.morango,10.00f,"Morango","Medio",3)
)

class UserViewModel : ViewModel() {
    // Propriedades para armazenar os dados do usuário
    var name = mutableStateOf("")
    var email = mutableStateOf("")
    var address = mutableStateOf("")
}

// Cores definidas no arquivo colors.xml (você pode referenciá-las aqui)
val Purple200 = Color(0xFFBB86FC)
val Purple500 = Color(0xFF6200EE)
val Purple700 = Color(0xFF3700B3)
val Teal200 = Color(0xFF03DAC5)
val Teal700 = Color(0xFF018786)
val Black = Color(0xFF000000)
val White = Color(0xFFFFFFFF)

// Tema Claro
private val LightColors2 = lightColors(
    primary = Purple500,
    primaryVariant = Purple700,
    secondary = Teal200,
    background = Color(0xFFA623C4),
    surface = White,
    onPrimary = White,
    onSecondary = Black,
    onBackground = Black,
    onSurface = Black,
    error = Color(0xFFB00020),
    onError = White
)

// Tema Escuro
private val DarkColors2 = darkColors(
    primary = Purple200,
    primaryVariant = Purple700,
    secondary = Teal200,
    background = Color(0xFF121212),
    surface = Color(0xFF1E1E1E),
    onPrimary = White,
    onSecondary = Black,
    onBackground = White,
    onSurface = White,
    error = Color(0xFFB00020),
    onError = White
)

@Composable
fun CupcakeTheme2(darkTheme: Boolean = true, content: @Composable () -> Unit) {
    // Definir qual tema usar (escuro ou claro)
    val colors = if (darkTheme) DarkColors2 else LightColors2

    MaterialTheme(
        colors = colors,
        content = content
    )
}


@Composable
fun AppNavigation() {

    val navController = rememberNavController()
    val cartList = remember { mutableStateListOf<Item>() } // Estado do carrinho

    val userViewModel: UserViewModel = viewModel() // Cria ou obtém o ViewModel

    NavHost(navController = navController, startDestination = "welcome") {
        composable("welcome") { WelcomeScreen(navController) }
        composable("login") { LoginScreen(navController) }
        composable("register") { RegisterScreen(navController,userViewModel) }
        composable("support") { ChatScreen() }
        composable("home") { HomeScreen(navController,cartList) }
        composable("selectedCupcake/{itemName}") { backStackEntry ->
            val itemName = backStackEntry.arguments?.getString("itemName") ?: "Item Desconhecido"
            SelectedCupCakeScreen(itemName, navController, cartList)
        }
        composable("cart") {
            // Passando o carrinho para a CartScreen
            CartScreen(navController,cart = cartList,userViewModel)
        }
        composable("customizeCupcake") {
            CustomizeCupcakeScreen(navController,cartList)
        }
        composable("orderConfirmation") { OrderConfirmationScreen(navController,userViewModel) }
    }



}