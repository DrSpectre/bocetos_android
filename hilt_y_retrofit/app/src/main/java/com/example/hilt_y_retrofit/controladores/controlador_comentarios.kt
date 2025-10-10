package com.example.hilt_y_retrofit.controladores

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hilt_y_retrofit.api.JSONPlaceholder
import com.example.hilt_y_retrofit.modelos.Comentario
import com.example.hilt_y_retrofit.modelos.Publicacion
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.Collections.emptyList
import javax.inject.Inject

@HiltViewModel
class ControladorPublicaciones @Inject constructor(
            private val api_placeholder: JSONPlaceholder
): ViewModel(){
    private val _publicaciones = mutableStateOf(emptyList<Publicacion>())
    val publicaciones: State<List<Publicacion>> = _publicaciones

    private val _publicacion_selecionada = mutableStateOf(Publicacion(id = 0, title = "404", body = "No encontrado", userId = 0))
    val publicacion_seleccionada: State<Publicacion> = _publicacion_selecionada

    private val _comentarios = mutableStateOf(emptyList<Comentario>())
    val comentarios: State<List<Comentario>> = _comentarios

    fun obtener_comentarios(){
        viewModelScope.launch {
            try {
                val comentarios_obtenidos = api_placeholder.obtener_comentarios_de_publicacion(publicacion_seleccionada.value.id)
                _comentarios.value = comentarios_obtenidos
            }
            catch(error: Exception){
                Log.wtf("Peticion API", "LA api respondio con un ${error.message}")
            }
        }
    }

    fun seleccionar_publicacion(id: Int){
        Log.wtf("Controlodar De Publicaciones", "Se ha seleccionado la publicacion: ${id}")
        for(publicacion in publicaciones.value){
            if(publicacion.id == id){
                _publicacion_selecionada.value = publicacion
                obtener_comentarios()
                break
            }
        }
    }

    fun obtener_publicaciones(){
        viewModelScope.launch {
            try {
                val publicaciones_obtenidas = api_placeholder.obtener_publicaciones()
                _publicaciones.value = publicaciones_obtenidas
            }
            catch(error: Exception){
                Log.wtf("Peticion API", "LA api respondio con un ${error.message}")
            }
        }
    }

}