package mx.uacj.usandorecylcerview.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitCliente {
    private const val url_base = "https://jsonplaceholder.typicode.com/"

    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(url_base)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}

object api_cliente {
    val servicio_api: ApiService by lazy {
        RetrofitCliente.retrofit.create(ApiService::class.java)
    }
}






