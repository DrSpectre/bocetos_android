package com.example.hilt_y_retrofit.api

import com.example.hilt_y_retrofit.modelos.Publicacion
import retrofit2.http.GET

interface JSONPlaceholder{
    @GET("/posts")
    suspend fun obtener_publicaciones(): List<Publicacion>
}

