package scylla.task.scyllatask.Home.MyTickets.GetTiketsBottomSheet.model

 data class GetTicketModel(val statusName : String, val statusCode : Int, val ticket : Ticket)
data class Event (

    val id : String,
    val name : String,
    val startDate : String,
    val endDate : String,
    val location : Location
)
data class Location (

    val city : String,
    val state : String,
    val country : String,
    val zipCode : Int,
    val latitude : Double,
    val longitude : Double,
    val firstAddress : String
)
data class Ticket (

    val id : String,
    val createdAt : String,
    val updatedAt : String,
    val event : Event
)