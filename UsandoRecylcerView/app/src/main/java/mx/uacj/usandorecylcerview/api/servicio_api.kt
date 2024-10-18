package mx.uacj.usandorecylcerview.api

import mx.uacj.usandorecylcerview.modelos.Comentario
import mx.uacj.usandorecylcerview.modelos.Publicacion
import mx.uacj.usandorecylcerview.modelos.Usuario
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("posts/")
    fun obtener_todas_las_publicaciones(): Call<Array<Publicacion>>

    @GET("posts/{id}")
    fun obtener_publicacion(@Path("id") id_publicacion: Int): Call<Publicacion>

    @GET("posts/{id}/comments")
    fun obtener_todos_los_comentarios_de_la_publicaicon(@Path("id") id_publicacion: Int): Call<Array<Comentario>>

    @GET("users/{id}")
    fun obtener_usuario(@Path("id") id_usuario: Int): Call<Usuario>
}