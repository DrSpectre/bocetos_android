package mx.uacj.aplicacionfragmentos

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.add
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import mx.uacj.aplicacionfragmentos.pantallas_fragmentos.InformacionCompartida
import mx.uacj.aplicacionfragmentos.pantallas_fragmentos.PantallaDos
import mx.uacj.aplicacionfragmentos.pantallas_fragmentos.PantallaUno
import mx.uacj.aplicacionfragmentos.pantallas_fragmentos.datos_a_recibir

class MainActivity : AppCompatActivity() {
    // private val modelo_datos by viewModels<InformacionCompartida>()

    private val modelo_de_datos by viewModels<InformacionCompartida>()

    private var estoy_en_pantalla_dos: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        if(savedInstanceState == null){
            val infromacion_a_pasar = bundleOf(PantallaUno().parametro_1 to "Informacion que cura")
            supportFragmentManager.commit {
                add<PantallaUno>(R.id.contenedor_vista_sencilla_de_fragment, args = infromacion_a_pasar)
                setReorderingAllowed(true)
            }
        }

        crear_interaccion()
    }

    fun crear_interaccion(){
        val boton = findViewById<Button>(R.id.boton_para_hacer_cosas)

        boton.setOnClickListener {
            modelo_de_datos.agregar_un_click()

            var sandwich_de_datos = bundleOf(datos_a_recibir to "Esto de aqui es inforamcion que recibo")

            Log.v("ACTUALIZACION", "El valor de variable es ${modelo_de_datos} en MAinActivity")

            if(!estoy_en_pantalla_dos){
                supportFragmentManager.commit {
                    replace<PantallaDos>(R.id.contenedor_vista_sencilla_de_fragment, args = sandwich_de_datos)
                    setReorderingAllowed(true)
                    addToBackStack(null)
                }

                Log.v("ACTUALIZACION", "Intentamos cambiar a pantalla dos")

                estoy_en_pantalla_dos = true
            }
        }
    }
}