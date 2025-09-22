package com.example.lista_de_interacciones

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.lista_de_interacciones.ui.theme.Lista_de_interaccionesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Lista_de_interaccionesTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modificador = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }

    override fun onPause() {
        Log.v("Aplicacion", "Aplicacion pausada")
        super.onPause()
    }

    override fun onResume() {
        Log.v("Aplicacion", "Aplicacion resumida")
        super.onResume()
    }
}

@Composable
fun Greeting(name: String, modificador: Modifier = Modifier) {
    var clicks_realizados by remember { mutableStateOf(1) }

    Column(modifier = Modifier
        .background(Color.Cyan)
        .clickable {
        Log.v("Aplicacion", "MEsnaje desde la columna")
        clicks_realizados = clicks_realizados * 10
    }) {
        Text(
            text = "Hello $name! ${clicks_realizados}",
            modifier = modificador.background(Color.Red)
        )
        Text(
            text = "Hello $name! ${clicks_realizados}",
            modifier = modificador.background(Color.Red)
        )
        Text(
            text = "Hello $name!",
            modifier = modificador.background(Color.Red)
        )
        Text(
            text = "Hello $name!",
            modifier = modificador.background(Color.Red)
        )

    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Lista_de_interaccionesTheme {
        Greeting("Android")
    }
}