package com.example.hilt_y_retrofit.ui.pantallas

import android.util.Log
import androidx.compose.foundation.clickable
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
        controlodar_publicaciones: ControladorPublicaciones = hiltViewModel(),
        navegar_a_publiacion: () -> Unit = {}
) {
    Log.v("PantallaPublicacion", "Valor del cotnrolador: ${controlodar_publicaciones}")

    controlodar_publicaciones.obtener_publicaciones()

    if(controlodar_publicaciones.publicaciones.value.size > 0){
        Column(modifier = Modifier.verticalScroll(rememberScrollState())){
            for(publicacion in controlodar_publicaciones.publicaciones.value){
                Column(modifier = Modifier.clickable {
                        controlodar_publicaciones.seleccionar_publicacion(id = publicacion.id)
                        navegar_a_publiacion()
                    }) {
                    Text("Publicacion: ${publicacion.title}")
                    Text("${publicacion.body}")
                }

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