package mx.uacj.aplicacionfragmentos.pantallas_fragmentos

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.viewModels
import mx.uacj.aplicacionfragmentos.R


class PantallaUno : Fragment() {
    public val parametro_1: String = "Parametro_1"

    private var param1: String? = null
    private var param2: String? = null

    private lateinit var cajon_texto: TextView

    private val modelo_de_datos by viewModels<InformacionCompartida>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(parametro_1)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val vista_de_pantalla_uno = inflater.inflate(R.layout.fragment_pantalla_uno, container, false)

        cajon_texto = vista_de_pantalla_uno.findViewById(R.id.cajon_de_texto_de_pantalla_1)
        cajon_texto.text = "Has pulsado el boton de cambiar vistas: ${modelo_de_datos.variable}"

        return vista_de_pantalla_uno
    }
}