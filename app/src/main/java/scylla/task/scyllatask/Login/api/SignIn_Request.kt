package scylla.task.scyllatask.Login.api

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import scylla.task.scyllatask.Login.model.SignIn_Request_Model
import scylla.task.scyllatask.Login.model.SignIn_Model

interface SignIn_Request {


    @POST("v1/users/login")
    suspend fun SignIn(@Body requestBody: SignIn_Request_Model): Response<SignIn_Model>


}