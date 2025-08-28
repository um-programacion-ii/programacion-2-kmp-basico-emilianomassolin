package org.basic.project

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

import kmpbasico.composeapp.generated.resources.Res
import kmpbasico.composeapp.generated.resources.compose_multiplatform

@Composable
fun App() {
    MaterialTheme {
        Navigator(
            screen = MainScreen()
        )
    }
}

class MainScreen : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        var name by remember { mutableStateOf("") }

        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.primaryContainer)
                .safeContentPadding()
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextField(
                value = name,
                onValueChange = { name = it },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )

            Spacer(modifier = Modifier.height(40.dp))

            AnimatedVisibility(name.isNotEmpty()) {
                Text(
                    text = "Versión animada $name",
                    fontSize = 24.sp
                )
            }

            Spacer(modifier = Modifier.height(30.dp))

            // Ir a Segunda Pantalla
            Button(onClick = { navigator.push(SecondScreen()) }) {
                Text("Ir a Segunda Pantalla")
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Ir a Tercera Pantalla
            Button(onClick = { navigator.push(ThirdScreen()) }) {
                Text("Ir a Tercera Pantalla")
            }
        }
    }
}

class SecondScreen : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Yellow),
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Segunda pantalla",
                fontSize = 20.sp,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(20.dp))

            Button(onClick = { navigator.pop() }) {
                Text("Volver atrás")
            }
        }
    }
}

class ThirdScreen : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Cyan),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Tercera pantalla",
                fontSize = 20.sp,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Vuelve directamente a MainScreen
            Button(onClick = {
                // Limpiamos el stack y vamos a MainScreen
                navigator.popUntilRoot()
            }) {
                Text("Volver a la Primera Pantalla")
            }
        }
    }
}