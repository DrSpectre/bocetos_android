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
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
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

        crear_interaccion()
        agregar_menu_inferior_funcionalidad()
    }

    fun crear_interaccion(){
        val boton = findViewById<Button>(R.id.boton_para_hacer_cosas)

        boton.setOnClickListener {
            modelo_de_datos.agregar_un_click()

            Log.v("ACTUALIZACION", "El valor de variable es ${modelo_de_datos} en MAinActivity")


        }
    }

    fun agregar_menu_inferior_funcionalidad(){
        val host_navegacion: NavHostFragment = supportFragmentManager.findFragmentById(R.id.mi_pantalla_navegacion) as NavHostFragment

        val controlador_de_navegacion: NavController = host_navegacion.navController

        findViewById<BottomNavigationView>(R.id.navegacion_inferior_menu).setupWithNavController(controlador_de_navegacion)
    }
}