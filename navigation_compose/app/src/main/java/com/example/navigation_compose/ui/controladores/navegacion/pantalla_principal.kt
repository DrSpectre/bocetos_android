package com.example.navigation_compose.ui.controladores.navegacion

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.navigation_compose.ui.atomos.OpcionNavegacion

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaPrincipal(){
    val controlador_de_navegacion  = rememberNavController()
    var pantalla_seleccionada by remember { mutableStateOf(1) }

    Scaffold(
        topBar = {
            TopAppBar(title = {
                Row {
                    Column {
                        Text("hola mundo")
                        Text("ADios mundo")
                    }

                    Column {
                        Text("hola mundo")
                        Text("ADios mundo")
                    }
                }

            },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Blue,
                    titleContentColor = Color.White
                ))
        },
        bottomBar = {
            BottomAppBar(containerColor = Color.Blue) {
                Row(modifier = Modifier.fillMaxSize(), horizontalArrangement = Arrangement.SpaceAround) {
                    OpcionNavegacion(texto = "Ir a video",
                        selecionado = pantalla_seleccionada == 0,
                        al_pulsar = {
                            pantalla_seleccionada = 0
                            controlador_de_navegacion.navigate(Video)
                        })

                    OpcionNavegacion(texto = "Ir a Inicio",
                                selecionado = pantalla_seleccionada == 1,
                                al_pulsar = {
                                    pantalla_seleccionada = 1
                                    controlador_de_navegacion.navigate(Inicio)
                                })

                    Text("Ir a Canal",
                            color = if(pantalla_seleccionada == 2) Color.Red else Color.Green,
                            modifier = Modifier.clickable {
                                pantalla_seleccionada = 2
                                controlador_de_navegacion.navigate(Canal)
                        })
                }

            }
        },
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        NavegacionInicio(modificador = Modifier.padding(innerPadding), controlador_de_navegacion)
    }
}

@Composable
@Preview(showBackground = true)
fun PrevistaPAntallaPrincipal(){
    PantallaPrincipal()
}