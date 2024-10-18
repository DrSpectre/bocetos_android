package mx.uacj.usandorecylcerview.modelos

data class Comentario(
    var postId: Int,
    var id: Int,
    var name: String,
    var email: String,
    var body: String
)