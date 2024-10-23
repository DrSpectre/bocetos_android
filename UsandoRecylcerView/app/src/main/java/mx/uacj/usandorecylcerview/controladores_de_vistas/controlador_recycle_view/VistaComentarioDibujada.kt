package mx.uacj.usandorecylcerview.controladores_de_vistas.controlador_recycle_view

import android.view.View
import android.widget.TextView
import androidx.lifecycle.findViewTreeViewModelStoreOwner
import androidx.recyclerview.widget.RecyclerView
import mx.uacj.usandorecylcerview.R
import mx.uacj.usandorecylcerview.modelos.Comentario
import mx.uacj.usandorecylcerview.modelos.Publicacion

class VistaComentarioDibujada(vista: View): RecyclerView.ViewHolder(vista){
    val cajon_de_usuario: TextView
    val cajon_de_cuerpo: TextView
    init {
        cajon_de_usuario = vista.findViewById(R.id.cajon_texto_nombre_usuario)
        cajon_de_cuerpo = vista.findViewById(R.id.cajon_texto_cuerpo_comentario)
    }

    public fun dibujar(comentario: Comentario){
        cajon_de_usuario.text = comentario.name
        cajon_de_cuerpo.text = comentario.body
    }
}