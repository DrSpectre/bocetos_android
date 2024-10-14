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
//Temas de retorfit
import retrofit2.Callback
import retrofit2.Call
import retrofit2.Response


class MainActivity : AppCompatActivity() {
    private var publicaciones_a_desplegar: Array<Publicacion> = arrayOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        inicializar_recicladores_de_vistas()

        agregar_interaccion_botones()

    }

    fun inicializar_recicladores_de_vistas(){
        val adapatador_modificado = AdaptadorDeRecycleViewer(publicaciones_a_desplegar)

        val recilador_de_vistas: RecyclerView = findViewById(R.id.vista_reciclador)

        recilador_de_vistas.layoutManager = LinearLayoutManager(this)
        recilador_de_vistas.adapter = adapatador_modificado
    }

    fun agregar_interaccion_botones(){
        val boton_para_descargar_informacion: Button = findViewById(R.id.descargar_publicaciones)
        
        boton_para_descargar_informacion.setOnClickListener {
            // val post_id = 1 // El id del post a descargar para hacer prueba de que funciona
            val llamada_al_server = api_cliente.servicio_api.obtener_todas_las_publicaciones()

            llamada_al_server.enqueue(object: Callback<Array<Publicacion>> {
                override fun onResponse(call: Call<Array<Publicacion>>, response: Response<Array<Publicacion>>) {
                    if(response.isSuccessful){
                        val publicaciones_recibidas = response.body()!!
                        publicaciones_a_desplegar = publicaciones_recibidas
                        Log.v("EUREKA", "${publicaciones_a_desplegar.size}")
                    }
                    else{
                        Log.v("Error en la peticion", response.message())
                    }
                }

                override fun onFailure(call: Call<Array<Publicacion>>, t: Throwable) {
                    Log.v("Error general", "Algo hiciste mal")
                }
            })

        }
    }
}