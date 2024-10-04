package mx.uacj.juegodedetective.pantallas

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import mx.uacj.juegodedetective.ObjetoCompacto
import mx.uacj.juegodedetective.R
import mx.uacj.juegodedetective.modelo.InformacionPista
import java.io.Serializable

class PantallaPistas : AppCompatActivity() {
    lateinit var cajon_de_texto_pantalla_anterior: TextView
    lateinit var informacion_de_la_pista_cercana: InformacionPista

    lateinit var marco_imagen: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_pantalla_pistas)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        recibir_informacion_pantalla_anterior()

        inicializar_interfaz()


    }

    fun recibir_informacion_pantalla_anterior(){
        var datos_recibidos: InformacionPista
        datos_recibidos = intent.getSerializableExtra("pista_a_pintar") as InformacionPista
        informacion_de_la_pista_cercana = datos_recibidos
    }

    fun inicializar_interfaz(){
        cajon_de_texto_pantalla_anterior = findViewById(R.id.caja_texto)

        cajon_de_texto_pantalla_anterior.text = informacion_de_la_pista_cercana.informacion

        marco_imagen = findViewById(R.id.lugar_para_la_imagen)

        marco_imagen.setImageResource(informacion_de_la_pista_cercana.imagen)
    }

}