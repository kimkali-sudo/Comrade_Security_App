package dev.korryr.comradealert

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import dev.korryr.comradealert.ui.feutures.auth.loggin.LoginScreen
import dev.korryr.comradealert.ui.feutures.auth.singup.SignUpScreen
import dev.korryr.comradealert.ui.feutures.navigation.NaveGraph
import dev.korryr.comradealert.ui.theme.ComradeAlertTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComradeAlertTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NaveGraph(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}
