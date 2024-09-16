import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import com.example.myapp.ui.theme.MyAppTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material.icons.rounded.Search
import androidx.navigation.compose.rememberNavController
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyAppTheme {
                // Configuración del NavController
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "home") {
                    composable("home") { MainScreen(navController) }
                    composable("build") { BuildScreen() }
                    composable("menu") { MenuScreen() }
                    composable("favorite") { FavoriteScreen() }
                    composable("delete") { DeleteScreen() }
                }
            }
        }
    }
}

@Composable
fun MainScreen(navController: NavController) {
    // Estado para almacenar el conteo de clics
    var clickCount by remember { mutableStateOf(0) }

    // Scaffold con el botón flotante
    Scaffold(
        topBar = { CustomTopBar(navController) },
        bottomBar = { CustomBottomBar(navController) },
        floatingActionButton = { CustomFAB(onFabClick = { clickCount++ }) },
        content = { padding ->
            CustomContent(padding = padding, clickCount = clickCount)
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopBar(navController: NavController) {
    TopAppBar(
        navigationIcon = {
            IconButton(onClick = { navController.navigate("menu") }) {
                Icon(imageVector = Icons.Filled.Menu, contentDescription = null)
            }
        },
        title = { Text(text = "Home") },
    )
}

@Composable
fun CustomBottomBar(navController: NavController) {
    BottomAppBar {
        // Botón 1 - Build
        IconButton(
            onClick = { navController.navigate("build") },
            modifier = Modifier.weight(1f)
        ) {
            Icon(Icons.Filled.Build, contentDescription = "Build")
        }

        // Botón 2 - Menu
        IconButton(
            onClick = { navController.navigate("menu") },
            modifier = Modifier.weight(1f)
        ) {
            Icon(Icons.Filled.List, contentDescription = "Menu")
        }

        // Botón 3 - Favorite
        IconButton(
            onClick = { navController.navigate("favorite") },
            modifier = Modifier.weight(1f)
        ) {
            Icon(Icons.Filled.Favorite, contentDescription = "Favorite")
        }

        // Botón 4 - Delete
        IconButton(
            onClick = { navController.navigate("delete") },
            modifier = Modifier.weight(1f)
        ) {
            Icon(Icons.Filled.Delete, contentDescription = "Delete")
        }
    }
}

@Composable
fun CustomFAB(onFabClick: () -> Unit) {
    FloatingActionButton(
        onClick = { onFabClick() }
    ) {
        Text(
            fontSize = 24.sp, // Tamaño de fuente del texto del botón
            text = "+" // Texto del botón
        )
    }
}

@Composable
fun CustomContent(padding: PaddingValues, clickCount: Int) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Has presionado el botón $clickCount veces", fontSize = 24.sp)
    }
}

@Composable
fun BuildScreen() {
    ScreenTemplate(title = "Build Screen", description = "Esta es la pantalla de Build.")
}

@Composable
fun MenuScreen() {
    ScreenTemplate(title = "Menu Screen", description = "Esta es la pantalla de Menu.")
}

@Composable
fun FavoriteScreen() {
    ScreenTemplate(title = "Favorite Screen", description = "Esta es la pantalla de Favorite.")
}

@Composable
fun DeleteScreen() {
    ScreenTemplate(title = "Delete Screen", description = "Esta es la pantalla de Delete.")
}

@Composable
fun ScreenTemplate(title: String, description: String) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = title, style = MaterialTheme.typography.headlineLarge)
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = description)
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyAppTheme {
        val navController = rememberNavController()
        MainScreen(navController)
    }
}