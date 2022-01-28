package scylla.task.scyllatask.Login.model

    data class SignIn_Model(val statusName : String, val statusCode : Int, val user : User, val token : Token){
    data class Token (val type : String, val access : String)
    data class User (

    val id : String,
    val email : String,
    val fullName : String,
    val avatar : String,
    val status : String,
    val createdAt : String,
    val updatedAt : String
)

}
