package com.example.confetti_timer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import com.example.confetti_timer.ui.theme.ConfettitimerTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import nl.dionsegijn.konfetti.compose.KonfettiView
import nl.dionsegijn.konfetti.core.Party
import nl.dionsegijn.konfetti.core.Position
import nl.dionsegijn.konfetti.core.emitter.Emitter
import java.util.Timer
import java.util.concurrent.TimeUnit
import kotlin.concurrent.timerTask

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge();

        val viewModel: TimerViewModel by viewModels()

        runBlocking {
            delay(2000)
            viewModel.textToChange = "efter ændring"
        }

        lifecycleScope.launch {
            println("before delay")
            delay(2000)
            viewModel.textToChange = "efter ændring"
            println("After delay")
        }

        setContent {
            ConfettitimerTheme {
                Text(viewModel.textToChange)

                ConfettiApp()
            }
        }
    }
}

@Composable
fun ConfettiApp() {
    var confettiKey by remember { mutableStateOf(0) }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        // Our Button to trigger confetti
        Button(
            onClick = {
                // Trigger the confetti by incrementing the key
                confettiKey++
            }
        ) {
            Text("Spray Confetti!")
        }

        // Force re-initialization of KonfettiView whenever confettiKey changes
        key(confettiKey) {
            if(confettiKey!=0) {
                KonfettiView(
                    modifier = Modifier.fillMaxSize(),
                    parties = listOf(
                        Party(
                            speed = 0f,
                            maxSpeed = 30f,
                            damping = 0.9f,
                            spread = 360,
                            colors = listOf(0xfce18a, 0xff726d, 0xf4306d, 0xb48def),
                            position = Position.Relative(0.5, 0.3),
                            emitter = Emitter(duration = 100, TimeUnit.MILLISECONDS).max(100),
                        )
                    )
                )
            }
        }
    }
}