package mx.uacj.usandorecylcerview.controladores_de_vistas.controlador_recycle_view

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import mx.uacj.usandorecylcerview.R
import mx.uacj.usandorecylcerview.api.api_cliente
import mx.uacj.usandorecylcerview.modelos.Publicacion
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AdaptadorDeRecycleViewer():
                RecyclerView.Adapter<VistaDibujada> () {
    private var publicaciones_a_desplegar: Array<Publicacion> = arrayOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VistaDibujada {
        val vista_pariente = LayoutInflater.from(parent.context)
            .inflate(R.layout.renglon_a_dibujar, parent, false)

        return VistaDibujada(vista_pariente)
    }

    override fun onBindViewHolder(vista_siendo_dibujada: VistaDibujada, indice: Int) {
        vista_siendo_dibujada.cajon_de_texto.text = publicaciones_a_desplegar[indice].title
    }

    override fun getItemCount(): Int {
        return publicaciones_a_desplegar.count()
    }

    fun actualizar_datos(){
        val llamada_al_server = api_cliente.servicio_api.obtener_todas_las_publicaciones()

        llamada_al_server.enqueue(object: Callback<Array<Publicacion>> {
            override fun onResponse(call: Call<Array<Publicacion>>, response: Response<Array<Publicacion>>) {
                if(response.isSuccessful){
                    val publicaciones_recibidas = response.body()!!
                    publicaciones_a_desplegar = publicaciones_recibidas
                    notifyDataSetChanged()

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