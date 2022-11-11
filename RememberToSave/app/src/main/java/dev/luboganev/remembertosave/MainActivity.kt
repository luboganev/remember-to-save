package dev.luboganev.remembertosave

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dev.luboganev.remembertosave.ui.theme.RememberToSaveTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RememberToSaveTheme {
                val navController = rememberNavController()
                NavHost(
                    modifier = Modifier, navController = navController, startDestination = "main"
                ) {
                    composable("main") {
                        MainScreen {
                            navController.navigate("detail")
                        }
                    }
                    composable("detail") {
                        DetailScreen {
                            navController.popBackStack()
                        }
                    }
                }
            }
        }
    }
}
