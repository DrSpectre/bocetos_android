package mx.uacj.juegodedetective.modelo

import android.location.Location
import android.util.Log
import java.io.Serializable

data class UbicacionPista(
    var longitud: Double,
    var latitud: Double
): Serializable {
    fun calcular_distancia(ubicacion: Location): Float{
        // De donde se obtuvo la operacion
        // https://developer.android.com/reference/android/location/Location#distanceBetween(double,%20double,%20double,%20double,%20float[])

        val ubicacion_pista = Location("proveedor_falso")
        ubicacion_pista.longitude = this.longitud
        ubicacion_pista.latitude = this.latitud

        val distancia: Float = ubicacion.distanceTo(ubicacion_pista)
        Log.v("DistanciaCalculada", "Distancia: ${distancia}")

        return distancia
    }
}
