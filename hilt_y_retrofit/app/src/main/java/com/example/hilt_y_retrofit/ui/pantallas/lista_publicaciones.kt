package com.example.hilt_y_retrofit.ui.pantallas

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.example.hilt_y_retrofit.controladores.ControladorPublicaciones

@Composable
fun ListaPublicaciones(
        modificador: Modifier = Modifier,
        controlodar_publicaciones: ControladorPublicaciones = hiltViewModel()
) {
    val controlador_de_navegacion = rememberNavController()

    controlodar_publicaciones.obtener_publicaciones()

    if(controlodar_publicaciones.publicaciones.value.size > 0){
        Column(modifier = Modifier.verticalScroll(rememberScrollState())){
            for(publicacion in controlodar_publicaciones.publicaciones.value){
                Text("Publicacion: ${publicacion.title}")
                Text("${publicacion.body}")
            }
        }
    }
    else {
        Text("Disculpa las molestias, pero estamos obteniendo las ultimas publicaicones. Favor de esperar...")
    }
}

@Preview(showBackground = true)
@Composable
fun Previsualizacion(){
    ListaPublicaciones()
}