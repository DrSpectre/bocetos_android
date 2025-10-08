package com.example.hilt_y_retrofit.controladores

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hilt_y_retrofit.api.JSONPlaceholder
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