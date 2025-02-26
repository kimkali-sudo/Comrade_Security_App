package dev.korryr.comradealert.ui.feutures.auth.singup

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import dev.korryr.comradealert.ui.feutures.navigation.Route

@Composable
fun SignUpScreen(
    modifier: Modifier = Modifier,
    navController: NavController
){
    Scaffold (
    ){ innerPadding ->

        var email by rememberSaveable { mutableStateOf("") }
        var password by rememberSaveable { mutableStateOf("") }

        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ){
            Text(
                text = "Sign Up",
                fontSize = 30.sp,
                fontWeight = FontWeight.ExtraBold,
                modifier = Modifier
                    .padding(20.dp)
            )

            ComradeTextField(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth(),
                value = email,
                placeholder = "Email",
                onValueChange = {
                    email = it
                }
            )

            Spacer(androidx.compose.ui.Modifier.height(16.dp))

            ComradeTextField(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth(),
                value = password,
                placeholder = "Enter Password",
                onValueChange = {
                    password = it
                }
            )

            Spacer(androidx.compose.ui.Modifier.height(16.dp))

            Button(
                onClick = {
                    navController.navigate(Route.Home.SIGN_IN)
                },
            ) {
                Text(text = "Register")
            }

            Spacer(androidx.compose.ui.Modifier.weight(1f))

            LoginLink(
                onClick = {
                    navController.navigate(Route.Home.SIGN_IN)
                }
            )
        }

    }
}

@Composable
fun ComradeTextField(
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    value : String = "",
    placeholder : String = ""
){
    Row (
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
    ){
        OutlinedTextField(
            value = value,
            shape = androidx.compose.material3.MaterialTheme.shapes.medium,
            onValueChange = {
                onValueChange(it)
            },
            modifier = modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth(),
            placeholder = {
                Text(text = placeholder)
            }



            )
    }
}