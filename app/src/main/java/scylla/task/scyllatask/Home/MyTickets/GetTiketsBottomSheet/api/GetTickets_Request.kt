package scylla.task.scyllatask.Home.MyTickets.GetTiketsBottomSheet.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import scylla.task.scyllatask.Home.MyTickets.GetTiketsBottomSheet.model.GetTicketModel


interface GetTickets_Request {

    @Headers( "Content-Type: application/json;charset=UTF-8")
    @GET("v1/events/d7767a36-3fe8-48ef-816b-b6e930ba0699/tickets")
    suspend fun GetTicket():Response<GetTicketModel>
}