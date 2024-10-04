package mx.uacj.juegodedetective.modelo

import java.io.Serializable

data class InformacionPista(
    var informacion: String,
    var imagen: Int,
    var ubicacion: UbicacionPista
) : Serializable {}
