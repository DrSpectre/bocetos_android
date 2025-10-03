package com.example.navigation_compose.ui.controladores.navegacion


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
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.navigation_compose.ui.pantallas.PantallaInicio
import com.example.navigation_compose.ui.pantallas.PantallaVideo
import java.util.concurrent.locks.Condition

@Composable
fun NavegacionInicio(modificador: Modifier = Modifier, controlador_de_navegacion: NavHostController){
    // val controlador_de_navegacion = rememberNavController()

    NavHost(navController = controlador_de_navegacion, startDestination = Inicio){
        composable<Inicio> {
           PantallaInicio(modificador, controlador_de_navegacion)
        }

        composable<Video> {
            PantallaVideo(modificador, controlador_de_navegacion)

        }



        composable<Configuracion> {
            Column(modifier = modificador
                        .fillMaxSize()
                        .background(color = Color.Cyan)) {
                Text("En pantalla de configuracio")

                Text("Ir a Video",
                    modifier = Modifier
                        .padding(15.dp)
                        .clickable { controlador_de_navegacion.navigate(Video) })

                Text("Ir a Inicio",
                    modifier = Modifier
                        .padding(15.dp)
                        .clickable { controlador_de_navegacion.popBackStack() })

                Text("Ir a Canal",
                    modifier = Modifier
                        .padding(15.dp)
                        .clickable { controlador_de_navegacion.navigate(Canal) })
            }

        }

        composable<Canal>{
            Text("En pantalla de canal", modifier = modificador
                .fillMaxSize()
                .background(color = Color.Green)
            )
        }
    }
}