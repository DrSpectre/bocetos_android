package mx.uacj.juegodedetective.modelo

import java.io.Serializable

data class InformacionPista(
    var informacion: String,
    var imagen: String,
    var ubicacion: UbicacionPista
) : Serializable {}
