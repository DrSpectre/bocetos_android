package mx.uacj.usandorecylcerview.controladores_de_vistas.controlador_recycle_view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import mx.uacj.usandorecylcerview.R

class AdaptadorDeRecycleViewer(private val set_de_datos: Array<String>):
                RecyclerView.Adapter<AdaptadorDeRecycleViewer.VistaDibujada> () {
    class VistaDibujada(vista: View): RecyclerView.ViewHolder(vista){
        val cajon_de_texto: TextView
        init {
            cajon_de_texto = vista.findViewById(R.id.cajon_texto)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VistaDibujada {
        val vista_pariente = LayoutInflater.from(parent.context)
            .inflate(R.layout.renglon_a_dibujar, parent, false)

        return vista_pariente
    }

    override fun onBindViewHolder(vista_siendo_dibujada: VistaDibujada, indice: Int) {
        vista_siendo_dibujada.cajon_de_texto.text = set_de_datos[indice]
    }

    override fun getItemCount(): Int {
        return set_de_datos.count()
    }


}