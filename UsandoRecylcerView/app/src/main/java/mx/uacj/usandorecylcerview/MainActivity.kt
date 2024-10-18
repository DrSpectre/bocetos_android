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
    //private var publicaciones_a_desplegar: Array<Publicacion> = arrayOf()

    lateinit private var adaptador_recycle_view: AdaptadorDeRecycleViewer
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
        adaptador_recycle_view = AdaptadorDeRecycleViewer(this)
        adaptador_recycle_view.actualizar_datos()

        val recilador_de_vistas: RecyclerView = findViewById(R.id.vista_reciclador)

        recilador_de_vistas.layoutManager = LinearLayoutManager(this)
        recilador_de_vistas.adapter = adaptador_recycle_view
    }

    fun agregar_interaccion_botones(){
        val boton_para_descargar_informacion: Button = findViewById(R.id.descargar_publicaciones)
        
        boton_para_descargar_informacion.setOnClickListener {
            // val post_id = 1 // El id del post a descargar para hacer prueba de que funciona


        }
    }
}