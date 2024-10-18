package mx.uacj.usandorecylcerview.modelos


data class Publicacion(
    var userId: Int,
    var id: Int,
    var title: String,
    var body: String
){
    companion object{
        val id_publicacion = "identificador_de_la_publicacion"
    }
}

