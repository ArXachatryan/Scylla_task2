package scylla.task.scyllatask.Home.MyTickets.api


import retrofit2.Response
import retrofit2.http.*
import scylla.task.scyllatask.Home.MyTickets.model.MyTicketsModel

interface MyTickets_Request {
    @Headers( "Content-Type: application/json;charset=UTF-8")
    @GET("v1/events")
    suspend fun MyTickets(
                          @Query("page") page: Int,
                          @Query("limit") limit: Int =10,
                          @Query("q") uid: Int =1): Response<MyTicketsModel>

}