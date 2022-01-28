package scylla.task.scyllatask.Home.MyTickets.model

data class MyTicketsModel(val statusName : String, val statusCode : Int, val events : ArrayList<Events>, val _meta : _meta)
data class Events (

    val id : String,
    val name : String,
    val startDate : String,
    val endDate : String,
    val hasTicket : Boolean,
    val location : Location
)

data class _meta (

    val total : Int,
    val currentPage : Int,
    val pageCount : Int
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

