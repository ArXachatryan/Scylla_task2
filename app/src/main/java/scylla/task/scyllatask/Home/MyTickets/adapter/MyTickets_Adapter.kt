package scylla.task.scyllatask.Home.MyTickets.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.os.*
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.my_tickets_list_item.view.*
import scylla.task.scyllatask.Home.MyTickets.clickListener.TicketClick
import scylla.task.scyllatask.Home.MyTickets.model.Events
import scylla.task.scyllatask.R
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class MyTickets_Adapter(val context:Context,ticketsList :ArrayList<Events>, val nextPageCount:Int ,private val ticketClick: TicketClick) :RecyclerView.Adapter<MyTickets_Adapter.MyViewHolder>() {


    var tickets: ArrayList<Events> = ticketsList

    companion object {
        var nextPage = MutableLiveData<Int>()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.my_tickets_list_item, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        if (position == tickets.size - 1) {


            if (nextPageCount == 10) {
                val vibrator = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
                if (Build.VERSION.SDK_INT >= 26) {
                    vibrator.vibrate(
                        VibrationEffect.createOneShot(
                            300,
                            VibrationEffect.DEFAULT_AMPLITUDE
                        )
                    )
                } else {
                    vibrator.vibrate(300)
                }
                Handler(Looper.getMainLooper()).postDelayed({

                    nextPage.value = tickets.size
                }, 1000)
            }


        }
        holder.bind(tickets[position])

        holder.itemView.setOnClickListener {
            holder.itemView.isEnabled = false
            Handler(Looper.getMainLooper()).postDelayed({
                holder.itemView.isEnabled = true
            }, 500)
            ticketClick.TicketItemClick(position)
        }


    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name = itemView.ticket_title
        val location = itemView.ticket_location
        val date = itemView.ticket_date

        @SuppressLint("SetTextI18n")
        fun bind(eEvents: Events) {

            name.text = eEvents.name
            location.text = "${eEvents.location.country} , ${eEvents.location.state} , ${eEvents.location.city}"
            date.text = "${eEvents.startDate.substring(0,10) } - ${eEvents.endDate.substring(0,10)}"

        }


    }


    override fun getItemCount() = tickets.size
}