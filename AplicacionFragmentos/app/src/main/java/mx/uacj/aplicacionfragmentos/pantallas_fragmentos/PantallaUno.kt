package mx.uacj.aplicacionfragmentos.pantallas_fragmentos

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.viewModels
import mx.uacj.aplicacionfragmentos.R


class PantallaUno : Fragment() {
    public val parametro_1: String = "Parametro_1"

    private var param1: String? = null
    private var param2: String? = null
    private val modelo by viewModels<InformacionCompartida>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(parametro_1)
            // param2 = it.getString(ARG_PARAM2)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val vista = inflater.inflate(R.layout.fragment_pantalla_uno, container, false)
        return vista
    }
}