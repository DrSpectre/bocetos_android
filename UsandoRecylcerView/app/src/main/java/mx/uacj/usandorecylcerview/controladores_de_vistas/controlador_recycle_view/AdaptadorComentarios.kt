package mx.uacj.usandorecylcerview.controladores_de_vistas.controlador_recycle_view

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import mx.uacj.usandorecylcerview.R
import mx.uacj.usandorecylcerview.api.api_cliente
import mx.uacj.usandorecylcerview.controladores_de_vistas.ControladorVistaPublicacion
import mx.uacj.usandorecylcerview.modelos.Comentario
import mx.uacj.usandorecylcerview.modelos.Publicacion
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.math.log

class AdaptadorComentarios(private val id_de_publicacion: Int):
    RecyclerView.Adapter<VistaComentarioDibujada> () {

    private var comentarios_a_desplegar: Array<Comentario> = arrayOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VistaComentarioDibujada {
        val vista_pariente = LayoutInflater.from(parent.context)
            .inflate(R.layout.renglon_comentario, parent, false)

        val vista_dibujada_sosten = VistaComentarioDibujada(vista_pariente)

        return vista_dibujada_sosten
    }

    override fun onBindViewHolder(vista_siendo_dibujada: VistaComentarioDibujada, indice: Int) {
        vista_siendo_dibujada.dibujar(comentarios_a_desplegar[indice])

        vista_siendo_dibujada.itemView.setOnClickListener{
            Log.v("ESTAMOS EN EL VIND VIEW", "${comentarios_a_desplegar[indice].id}")
            /*
            var intento_para_cambiar_de_pantalla = Intent(it.context, ControladorVistaPublicacion::class.java)


            intento_para_cambiar_de_pantalla
                .putExtra(
                    Publicacion.id_publicacion,
                    comentarios_a_desplegar[indice].id
                )

            // Aqui buscaremos como resolver la llamada al start activity
            padre.startActivity(intento_para_cambiar_de_pantalla)
            */
        }
    }

    override fun getItemCount(): Int {
        return comentarios_a_desplegar.count()
    }

    fun actualizar_datos(){
        val llamada_al_server = api_cliente.servicio_api.obtener_todos_los_comentarios_de_la_publicaicon(id_de_publicacion)

        llamada_al_server.enqueue(object: Callback<Array<Comentario>> {
            override fun onResponse(call: Call<Array<Comentario>>, response: Response<Array<Comentario>>) {
                if(response.isSuccessful){
                    val publicaciones_recibidas = response.body()!!
                    comentarios_a_desplegar = publicaciones_recibidas
                    notifyDataSetChanged()

                    Log.v("EUREKA", "${comentarios_a_desplegar.size}")
                }
                else{
                    Log.v("Error en la peticion", response.message())
                }
            }

            override fun onFailure(call: Call<Array<Comentario>>, t: Throwable) {
                Log.v("Error general", "Algo hiciste mal")
            }
        })
    }


}