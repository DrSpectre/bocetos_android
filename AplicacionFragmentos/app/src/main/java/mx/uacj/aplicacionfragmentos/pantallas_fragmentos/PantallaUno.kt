package mx.uacj.aplicacionfragmentos.pantallas_fragmentos

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.launch
import mx.uacj.aplicacionfragmentos.R


class PantallaUno : Fragment() {
    public val parametro_1: String = "Parametro_1"

    private var param1: String? = null
    private var param2: String? = null

    private lateinit var cajon_texto: TextView

    private val modelo_de_datos by viewModels<InformacionCompartida>(ownerProducer = { requireActivity() })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(parametro_1)
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                modelo_de_datos.mutante_numero_clicks.collect { evento ->
                    when(evento){
                        is EstadosData.Error -> cajon_texto.text = "TEnemos un error en ${evento.error}. Por favor contactar administración"
                        is EstadosData.NumeroDeClicks -> cajon_texto.text = "Has pulsado el boton de cambiar vistas: ${evento.clicks}"
                    }
                }
            }
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val vista_de_pantalla_uno = inflater.inflate(R.layout.fragment_pantalla_uno, container, false)

        cajon_texto = vista_de_pantalla_uno.findViewById(R.id.cajon_de_texto_de_pantalla_1)
        // cajon_texto.text = "Has pulsado el boton de cambiar vistas: ${modelo_de_datos.variable}"

        val boton_para_cambiar_pantalla: Button = vista_de_pantalla_uno.findViewById(R.id.boton_mover_a_pantalla_4)
        boton_para_cambiar_pantalla.setOnClickListener {
            findNavController().navigate(R.id.action_pantallaUno_to_cuartaPantalla2)
        }

        return vista_de_pantalla_uno
    }
}