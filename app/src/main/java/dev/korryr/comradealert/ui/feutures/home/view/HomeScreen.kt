package dev.korryr.comradealert.ui.feutures.home.view

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import dev.korryr.comradealert.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController
){
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Home")
                },
                navigationIcon = {
                    IconButton(onClick = {
                    }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Menu,
                            contentDescription = "Back",
                            tint = Color.Blue
                        )
                    }
                },

            )
        }
    ){ innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            var isRecording by remember { mutableStateOf(false) }
            var recordingTime by remember { mutableStateOf(0) }
            val timer = remember { mutableStateOf<Job?>(null) }

            LaunchedEffect (isRecording){
                if (isRecording){
                    timer.value = CoroutineScope(Dispatchers.Main).launch {
                        while (isRecording){
                            delay(1000)
                            recordingTime++
                        }

                    }
                } else {
                    timer.value?.cancel()
                    recordingTime = 0
                }
            }

            AnimatedVisibility(visible = isRecording) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Recording... ${recordingTime}s",
                        style = MaterialTheme.typography.headlineLarge,
                        color = Color.Red,
                        modifier = Modifier.padding(8.dp)
                    )
                    LinearProgressIndicator(
                        modifier = Modifier
                            .fillMaxWidth(0.7f)
                            .height(6.dp),
                        color = Color.Red
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Button(
                        enabled = false,
                        onClick = { isRecording = false },
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
                    ) {
                        Text("Stop Recording", color = Color.White)
                    }
                }
            }

            if (!isRecording) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .clip(CircleShape)
                        .padding(8.dp)

                ) {
                    Image(
                        painter = painterResource(id = R.drawable.voice_button),
                        contentDescription = "Alert",
                        modifier = Modifier
                            .clip(CircleShape)
                            .clickable {
                                isRecording = !isRecording
                                Toast
                                    .makeText(
                                        navController.context,
                                        "Recording Started",
                                        Toast.LENGTH_SHORT
                                    )
                                    .show()
                            }
                            .background(
                                shape = CircleShape,
                                color = Color.Red
                            )
                            .padding(16.dp),
                        contentScale = ContentScale.Fit,
                        alignment = Alignment.Center,
                    )

                }
            }
        }
    }
}