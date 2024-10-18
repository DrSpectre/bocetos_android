package mx.uacj.usandorecylcerview.controladores_de_vistas.controlador_recycle_view

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import mx.uacj.usandorecylcerview.R
import mx.uacj.usandorecylcerview.modelos.Publicacion

class VistaDibujada(vista: View): RecyclerView.ViewHolder(vista){
    val cajon_de_texto: TextView
    init {
        cajon_de_texto = vista.findViewById(R.id.cajon_texto)
    }

    public fun dibujar(publicacion: Publicacion){
        cajon_de_texto.text = publicacion.title
    }
}