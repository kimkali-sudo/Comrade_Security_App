package dev.korryr.comradealert.ui.feutures.navigation

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import dev.korryr.comradealert.ui.feutures.auth.loggin.LoginScreen
import dev.korryr.comradealert.ui.feutures.auth.singup.SignUpScreen
import dev.korryr.comradealert.ui.feutures.home.view.HomeScreen

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NaveGraph(
    modifier: Modifier = Modifier
){
     val navController = rememberNavController()
    Scaffold {
        NavHost(
            navController = navController,
            startDestination = Route.Home.SIGN_UP
        ){
            composable(Route.Home.SIGN_UP){
                SignUpScreen(
                    navController = navController
                )
            }

            composable(Route.Home.SIGN_IN){
                LoginScreen(
                    navController = navController
                )
            }

            composable(Route.Home.HOME){
                HomeScreen(
                    navController = navController
                )
            }
        }
    }
}