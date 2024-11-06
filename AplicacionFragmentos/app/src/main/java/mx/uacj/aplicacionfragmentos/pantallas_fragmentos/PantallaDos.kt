package mx.uacj.aplicacionfragmentos.pantallas_fragmentos

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.launch
import mx.uacj.aplicacionfragmentos.R

/// https://developer.android.com/topic/libraries/architecture/viewmodel/viewmodel-cheatsheet <- PAra el view model compartido
/// https://developer.android.com/kotlin/flow/stateflow-and-sharedflow <- PAra la docuemtnacion del state flow 
public const val datos_a_recibir = "datos_a_recibir_1"
private const val ARG_PARAM2 = ""

class PantallaDos : Fragment() {
    private val modelo_de_datos by viewModels<InformacionCompartida>(ownerProducer = {requireActivity()})

    private lateinit var cajon_de_textp: TextView
    private lateinit var datos_recibidos: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            datos_recibidos = it.getString(datos_a_recibir).toString()
            Log.v("EUREKA", "Recibimos de bundle sandwich el valor ${datos_recibidos}")
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                modelo_de_datos.mutante_numero_clicks.collect {evento ->
                    when(evento){
                        is EstadosData.NumeroDeClicks -> mmostrarNumero(evento.clicks)
                        is EstadosData.Error -> cajon_de_textp.text = "Error :("
                    }
                }
            }
        }


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        Log.v("ACTUALIZACION", "El valor de variable es ${modelo_de_datos} en PantallaDos")
        // Inflate the layout for this fragment
        var vista_pantalla_dos = inflater.inflate(R.layout.fragment_pantalla_dos, container, false)
        cajon_de_textp = vista_pantalla_dos.findViewById(R.id.cajon_de_texto_pantalla_2)
        cajon_de_textp.text = "Se ha pulsado el boton ${modelo_de_datos.variable}"

        //modelo_de_datos.numero_de_clicks.observe(lifecycle.)
        return vista_pantalla_dos
    }


    fun mmostrarNumero(numero: Int){
        cajon_de_textp.text = "Se ha pulsado el boton ${numero}"
    }

}