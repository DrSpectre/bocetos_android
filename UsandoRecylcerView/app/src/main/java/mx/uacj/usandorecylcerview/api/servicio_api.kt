package mx.uacj.usandorecylcerview.api

import mx.uacj.usandorecylcerview.modelos.Publicacion
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("posts/")
    fun obtener_todas_las_publicaciones(): Call<Publicacion>

    @GET("posts/{id}")
    fun obtener_publicacion(@Path("id") id_publicacion: Int): Call<Publicacion>
}