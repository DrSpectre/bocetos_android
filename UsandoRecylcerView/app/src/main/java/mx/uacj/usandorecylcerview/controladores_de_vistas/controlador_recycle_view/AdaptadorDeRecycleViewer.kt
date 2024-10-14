package mx.uacj.usandorecylcerview.controladores_de_vistas.controlador_recycle_view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import mx.uacj.usandorecylcerview.R
import mx.uacj.usandorecylcerview.modelos.Publicacion

class AdaptadorDeRecycleViewer(private val set_de_datos: Array<Publicacion>):
                RecyclerView.Adapter<VistaDibujada> () {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VistaDibujada {
        val vista_pariente = LayoutInflater.from(parent.context)
            .inflate(R.layout.renglon_a_dibujar, parent, false)

        return VistaDibujada(vista_pariente)
    }

    override fun onBindViewHolder(vista_siendo_dibujada: VistaDibujada, indice: Int) {
        vista_siendo_dibujada.cajon_de_texto.text = set_de_datos[indice].title
    }

    override fun getItemCount(): Int {
        return set_de_datos.count()
    }


}