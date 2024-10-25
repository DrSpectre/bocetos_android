package mx.uacj.usandorecylcerview.controladores_de_vistas

import android.app.usage.UsageStats
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import mx.uacj.usandorecylcerview.R
import mx.uacj.usandorecylcerview.api.api_cliente
import mx.uacj.usandorecylcerview.controladores_de_vistas.controlador_recycle_view.AdaptadorComentarios
import mx.uacj.usandorecylcerview.controladores_de_vistas.controlador_recycle_view.AdaptadorDeRecycleViewer
import mx.uacj.usandorecylcerview.modelos.Publicacion
import mx.uacj.usandorecylcerview.modelos.Usuario
import org.w3c.dom.Text
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ControladorVistaPublicacion : AppCompatActivity() {
    private lateinit var donde_poner_titulo: TextView
    private lateinit var donde_poner_cuerpo_publicacion: TextView
    private lateinit var donde_poner_nombre_usuario: TextView

    private lateinit var ubicacion_comentarios: RecyclerView
    private lateinit var adaptador_comentarios: AdaptadorComentarios

    private var publicacion_a_desplegar: Publicacion? = null
    private var usuario_de_la_publicacion: Usuario? = null
    private var id_de_publicaicon_a_descargar: Int = -1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_controlador_vista_publicacion)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        inicializar_interfaz()
        descargar_informacion_publicacion()
        iniclizar_contenedor_cometnarios()

    }

    fun inicializar_interfaz(){
        donde_poner_titulo = findViewById(R.id.cajon_texto_titulo)
        donde_poner_cuerpo_publicacion = findViewById(R.id.cajon_texto_cuerpo)
        donde_poner_nombre_usuario = findViewById(R.id.cajon_texto_publicado_por)

        // Aqui hago una referncia a nuestro recylce view
        ubicacion_comentarios = findViewById(R.id.recycle_view_comentarios)
    }

    fun descargar_informacion_publicacion(){
        id_de_publicaicon_a_descargar = intent.getIntExtra(Publicacion.id_publicacion, -1)

        val llamada_al_server = api_cliente.servicio_api.obtener_publicacion(id_de_publicaicon_a_descargar)

        llamada_al_server.enqueue(object: Callback<Publicacion> {
            override fun onResponse(call: Call<Publicacion>, response: Response<Publicacion>) {
                if(response.isSuccessful){
                    val publicacion = response.body()!!
                    publicacion_a_desplegar = publicacion

                    colocar_publicacion()
                    descargar_informacion_usuario()

                    Log.v("EUREKA", "${publicacion.userId}")
                }
                else{
                    Log.v("Error en la peticion", response.message())
                }
            }

            override fun onFailure(call: Call<Publicacion>, t: Throwable) {
                Log.v("Error general", "Algo hiciste mal")
            }
        })
    }

    fun descargar_informacion_usuario(){
        var id_de_usuario_a_descargar = publicacion_a_desplegar!!.userId

        val llamada_al_server = api_cliente.servicio_api.obtener_usuario(id_de_usuario_a_descargar)

        llamada_al_server.enqueue(object: Callback<Usuario> {
            override fun onResponse(call: Call<Usuario>, response: Response<Usuario>) {
                if(response.isSuccessful){
                    val usuario = response.body()!!
                    usuario_de_la_publicacion = usuario

                    colocar_usuario()

                }
                else{
                    Log.v("Error en la peticion[Usuario]", response.message())
                }
            }

            override fun onFailure(call: Call<Usuario>, t: Throwable) {
                Log.v("Error general", "Algo hiciste mal")
            }
        })
    }

    fun iniclizar_contenedor_cometnarios(){
        adaptador_comentarios = AdaptadorComentarios(id_de_publicaicon_a_descargar)
        adaptador_comentarios.actualizar_datos()

        ubicacion_comentarios.layoutManager = LinearLayoutManager(this)
        ubicacion_comentarios.adapter = adaptador_comentarios

    }

    fun colocar_publicacion(){
        donde_poner_titulo.text = publicacion_a_desplegar?.title

        donde_poner_cuerpo_publicacion.text = publicacion_a_desplegar?.body


    }

    fun colocar_usuario(){
        donde_poner_nombre_usuario.text = usuario_de_la_publicacion!!.username
    }

}