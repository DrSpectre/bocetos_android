package mx.uacj.usandorecylcerview.controladores_de_vistas.controlador_recycle_view

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import mx.uacj.usandorecylcerview.R
import mx.uacj.usandorecylcerview.modelos.Comentario
import mx.uacj.usandorecylcerview.modelos.Publicacion

class VistaComentarioDibujada(vista: View): RecyclerView.ViewHolder(vista){
    val cajon_de_texto: TextView
    init {
        cajon_de_texto = vista.findViewById(R.id.cajon_texto)
    }

    public fun dibujar(comentario: Comentario){
        cajon_de_texto.text = comentario.body
    }
}