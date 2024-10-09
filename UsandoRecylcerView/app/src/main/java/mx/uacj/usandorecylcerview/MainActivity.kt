package mx.uacj.usandorecylcerview

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import mx.uacj.usandorecylcerview.controladores_de_vistas.controlador_recycle_view.AdaptadorDeRecycleViewer

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val set_de_datos = arrayOf("item numero 1", "item numero 2", "item numero 3", "item_numero 4",
            "item numero 1", "item numero 2", "item numero 3", "item_numero 4",
            "item numero 1", "item numero 2", "item numero 3", "item_numero 4",
        )

        val adapatador_modificado = AdaptadorDeRecycleViewer(set_de_datos)

        val recilador_de_vistas: RecyclerView = findViewById(R.id.vista_reciclador)

        recilador_de_vistas.layoutManager = LinearLayoutManager(this)
        recilador_de_vistas.adapter = adapatador_modificado
    }
}