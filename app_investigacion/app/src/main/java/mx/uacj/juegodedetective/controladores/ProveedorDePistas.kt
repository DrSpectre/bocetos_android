package mx.uacj.juegodedetective.controladores

import android.location.Location
import mx.uacj.juegodedetective.R
import mx.uacj.juegodedetective.modelo.InformacionPista
import mx.uacj.juegodedetective.modelo.UbicacionPista

class ProveedorDePistas( ) {
    var arreglo_de_pistas: ArrayList<InformacionPista> = arrayListOf()

    fun ubicar_pista_mas_cercana(ubicacion: Location, distancia_maxima: Float): InformacionPista? {
        var pista_mas_cercana: InformacionPista? = null
        var distancia_pista_mas_cercana: Float = distancia_maxima + 1f

        for(pista in arreglo_de_pistas){
            val distancia_calculada = pista.ubicacion.calcular_distancia(ubicacion)
            if (distancia_calculada < distancia_pista_mas_cercana){
                pista_mas_cercana = pista
                distancia_pista_mas_cercana = distancia_calculada
            }

        }
        return pista_mas_cercana
    }

    fun inicializar_juego(){
        _generar_pistas_actuales()
    }

    private fun _generar_pistas_actuales(){
        var nueva_pista: InformacionPista
        //@31.7438316,-106.4353813,18.51
        nueva_pista = InformacionPista(
            "TExto loco de la pista 1",
            R.drawable.lake,
            ubicacion = UbicacionPista(
                longitud = -106.4353813,
                latitud = 31.7438316
            )
        )

        arreglo_de_pistas.add(nueva_pista)

        nueva_pista = InformacionPista(
            "TExto loco de la pista 2",
            R.drawable.nombre_interesante,
            ubicacion = UbicacionPista(
                longitud = -106.43212267978987,
                latitud = 31.742533543833936
            )
        )
        arreglo_de_pistas.add(nueva_pista)

        nueva_pista = InformacionPista(
            "Texto de la pista 3",
            R.drawable.lake,
            ubicacion = UbicacionPista(
                longitud = -106.43200486454715,
                latitud = 31.74365609793031
            )
        )

        arreglo_de_pistas.add(nueva_pista)
    }
}