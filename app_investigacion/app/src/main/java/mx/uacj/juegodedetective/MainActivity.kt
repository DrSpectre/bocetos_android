package mx.uacj.juegodedetective

import android.content.Intent
import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.widget.Button
import android.widget.TextView

import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.content.PackageManagerCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.Granularity
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import mx.uacj.juegodedetective.controladores.ProveedorDePistas
import mx.uacj.juegodedetective.modelo.InformacionPista
import mx.uacj.juegodedetective.pantallas.PantallaPistas
import java.io.Serializable
import java.util.concurrent.PriorityBlockingQueue


//TODO: https://www.youtube.com/watch?v=oqrbggPlASs

class ObjetoCompacto(var cajon_texto: String,
                     var contraseña: String,
                     var correo_electronico: String
): Serializable {}

class MainActivity : AppCompatActivity() {
    // PARA calcular la distancia tomando en cuenta la esfericidad: https://www.deformasymapas.com/blog/post/2/
    lateinit var boton_para_cambiar_de_pantalla: Button
    lateinit var cajon_de_texto: TextInputEditText

    lateinit var cajon_texto_latitud: TextView
    lateinit var cajon_texto_longitud: TextView

    var pistas_para_jugar: ProveedorDePistas = ProveedorDePistas()
    var pista_mas_cercana: InformacionPista? = null


    // Variables para controlar el GPS
    private var tenemos_permisos_para_el_GPS = false

    lateinit var fusedLocationClient: FusedLocationProviderClient
    lateinit var cuando_obtengamos_coordenadas_que_hacer: LocationCallback

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        inicializar_proveedores()

        inicializar_sensores()

        inicializar_relaciones_con_interfaz()
        inicializar_botones()

    }

    private fun inicializar_proveedores() {
        pistas_para_jugar.inicializar_juego()
    }

    fun inicializar_relaciones_con_interfaz(){
        boton_para_cambiar_de_pantalla = findViewById(R.id.mover_a_pantalla)
        cajon_de_texto = findViewById(R.id.cajon_de_texto)

        cajon_texto_latitud = findViewById(R.id.latitud_texto)
        cajon_texto_longitud = findViewById(R.id.longitud_texto)
    }

    fun inicializar_botones(){
        boton_para_cambiar_de_pantalla.setOnClickListener {
            var intento_para_cambiar_pantalla = Intent(this, PantallaPistas::class.java)

            if(pista_mas_cercana != null){
                intento_para_cambiar_pantalla.putExtra("pista_a_pintar", pista_mas_cercana)

                this.startActivity(intento_para_cambiar_pantalla)
            }
        }

    }

    fun inicializar_sensores(){
        verificar_permisos_gps()
    }

    fun verificar_permisos_gps(){
        var arreglo_permisos = arrayListOf(
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION
        )

        val arreglo_de_permisos = arreglo_permisos.toTypedArray()
        if(tenemos_permisos_actualmente(arreglo_de_permisos)){
            tenemos_permisos_para_el_GPS = true
            cuando_tengamos_permisos_para_GPS()

        }
        else {
            cuando_no_tengamos_permisos_para_GPS(arreglo_de_permisos)
        }
    }

    fun tenemos_permisos_actualmente(permisos_actuales: Array<String>): Boolean {
        /*return permisos_actuales.all {
            return ContextCompat.checkSelfPermission(this, it) == PackageManager.PERMISSION_GRANTED
        }*/
        var cantidad_permisos = 0

        val permiso_garantizado = PackageManager.PERMISSION_GRANTED

        for (permiso in permisos_actuales){
            if( ContextCompat.checkSelfPermission(this, permiso) == permiso_garantizado){
                cantidad_permisos = cantidad_permisos + 1
            }
        }
        return (cantidad_permisos >= 2)
    }

    fun cuando_no_tengamos_permisos_para_GPS(solicitud_permisos: Array<String>) {
        // Documentacion: https://developer.android.com/training/permissions/requesting?hl=es-419
        //Preguntar y solicitar permisos
        requestPermissions(
            solicitud_permisos,
            100 // Coidgo para permisos de segundo plano
        )

        // Para las versiones anteriores https://stackoverflow.com/questions/36056721/ask-for-permissions-with-api-22-android#36056944
    }

    fun cuando_tengamos_permisos_para_GPS(){
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        try{
            fusedLocationClient.lastLocation.addOnSuccessListener {
                if(it == null){
                    Toast.makeText(
                            this,
                            "No se puede obtener la ubicacion",
                            Toast.LENGTH_LONG
                        )
                        .show()
                }
                else {
                    // TODO: implementar la funcion de abajo
                    identifica_la_pista_mas_cercana(it)
                }
            }

            val peticion_de_ubicacion = LocationRequest.Builder(
                Priority.PRIORITY_HIGH_ACCURACY,
                3000
            ).apply {
                setGranularity(Granularity.GRANULARITY_PERMISSION_LEVEL)
                setWaitForAccurateLocation(true)
            }.build()

            cuando_obtengamos_coordenadas_que_hacer = object: LocationCallback(){
                override fun onLocationResult(ubicaciones_obtenidas: LocationResult) {
                    super.onLocationResult(ubicaciones_obtenidas)

                    for(ubicacion in ubicaciones_obtenidas.locations){
                        identifica_la_pista_mas_cercana(ubicacion)
                    }
                }
            }

            fusedLocationClient.requestLocationUpdates(
                peticion_de_ubicacion,
                cuando_obtengamos_coordenadas_que_hacer,
                Looper.getMainLooper()
            )

        }
        catch (error_interno: SecurityException){
            Log.v("ERROR_DE_SEGURIDAD", error_interno.message.toString())
            // Haz nada
        }
    }

    fun identifica_la_pista_mas_cercana(ubicacion: Location){
        cajon_texto_latitud.text = "${ubicacion.latitude}"
        cajon_texto_longitud.text = "${ubicacion.longitude}"


        pista_mas_cercana = pistas_para_jugar.ubicar_pista_mas_cercana(ubicacion)
        Log.v("PISTA_CERCANA", "${pista_mas_cercana}")

        Log.v("COORDENADAS", "lat: ${ubicacion.latitude} long: ${ubicacion.longitude}")


    }

}