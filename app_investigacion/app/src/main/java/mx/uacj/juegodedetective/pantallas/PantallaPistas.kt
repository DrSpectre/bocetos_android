package mx.uacj.juegodedetective.pantallas

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import mx.uacj.juegodedetective.ObjetoCompacto
import mx.uacj.juegodedetective.R
import java.io.Serializable

class PantallaPistas : AppCompatActivity() {
    lateinit var cajon_de_texto_pantalla_anterior: TextView
    lateinit var informacion_recibida: ObjetoCompacto

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_pantalla_pistas)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        Log.v("MensajeSegundaPantalla", intent.getStringExtra("texto_que_te_envio") ?: "Parece que no llego nada")

        //Log.v("Mensaje", intent.getStringExtra("cajon_anterior_de_texto") ?: "")

        //val serializado = intent.getSerializableExtra("paquete") as prueba

        //Log.v("Mensaje",  serializado.nombre)

        recibir_informacion_pantalla_anterior()

        inicializar_interfaz()


    }

    fun recibir_informacion_pantalla_anterior(){
        var datos_recibidos: ObjetoCompacto
        datos_recibidos = intent.getSerializableExtra("paquete_que_te_envio_de_datos") as ObjetoCompacto
        informacion_recibida = datos_recibidos
    }

    fun inicializar_interfaz(){
        cajon_de_texto_pantalla_anterior = findViewById(R.id.caja_texto)

        cajon_de_texto_pantalla_anterior.text = informacion_recibida.cajon_texto
    }

}