package com.example.navigation_compose.ui.pantallas

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.navigation_compose.ui.controladores.navegacion.Canal
import com.example.navigation_compose.ui.controladores.navegacion.Configuracion

@Composable
fun PantallaVideo(modificador: Modifier = Modifier, controlador_de_navegacion: NavHostController){
    Column(modifier = modificador
        .fillMaxSize()
        .background(color = Color.Yellow)) {
        Text("En pantalla de video")

        Text("Ir a Inicio",
            modifier = Modifier
                .padding(15.dp)
                .clickable { controlador_de_navegacion.popBackStack() })

        Text("Ir a Configuracion",
            modifier = Modifier
                .padding(15.dp)
                .clickable { controlador_de_navegacion.navigate(Configuracion) })

        Text("Ir a Canal",
            modifier = Modifier
                .padding(15.dp)
                .clickable { controlador_de_navegacion.navigate(Canal) })
    }
}