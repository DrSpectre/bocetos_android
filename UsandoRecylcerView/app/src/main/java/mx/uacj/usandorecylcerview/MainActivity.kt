package mx.uacj.usandorecylcerview

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import mx.uacj.usandorecylcerview.api.ApiService
import mx.uacj.usandorecylcerview.api.RetrofitCliente
import mx.uacj.usandorecylcerview.controladores_de_vistas.controlador_recycle_view.AdaptadorDeRecycleViewer
import mx.uacj.usandorecylcerview.api.api_cliente
import mx.uacj.usandorecylcerview.modelos.Publicacion
//TEmas de retorfit
import retrofit2.Callback
import retrofit2.Call
import retrofit2.Response


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        inicializar_reccladores_de_vistas()

        agregar_interaccion_botones()

    }

    fun inicializar_reccladores_de_vistas(){
        val set_de_datos = arrayOf("item numero 1", "item numero 2", "item numero 3", "item_numero 4",
            "item numero 1", "item numero 2", "item numero 3", "item_numero 4",
            "item numero 1", "item numero 2", "item numero 3", "item_numero 4",
        )

        val adapatador_modificado = AdaptadorDeRecycleViewer(set_de_datos)

        val recilador_de_vistas: RecyclerView = findViewById(R.id.vista_reciclador)

        recilador_de_vistas.layoutManager = LinearLayoutManager(this)
        recilador_de_vistas.adapter = adapatador_modificado
    }

    fun agregar_interaccion_botones(){
        val boton_para_descargar_informacion: Button = findViewById(R.id.descargar_publicaciones)
        
        boton_para_descargar_informacion.setOnClickListener {
            val post_id = 1 // El id del post a descargar para hacer prueba de que funciona
            val llamada_al_server = api_cliente.servicio_api.obtener_publicacion(post_id)

            llamada_al_server.enqueue(object: Callback<Publicacion> {
                override fun onResponse(call: Call<Publicacion>, response: Response<Publicacion>){
                    if (response.isSuccessful) {
                        val publicacion = response.body()
                        if (publicacion != null) {
                            Log.v("EUREKA", "${publicacion}")
                        }
                    }
                    else {
                        Log.v("REtrofit2", "Error para obtener una respuesta")
                    }
                }

                override fun onFailure(call: Call<Publicacion>, t: Throwable) {
                    //Aqui manejamos en caso de que exista un fallo de algun tipo
                }
            })
        }
    }
}