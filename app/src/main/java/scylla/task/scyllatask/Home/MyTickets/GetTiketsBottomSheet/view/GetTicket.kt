package scylla.task.scyllatask.Home.MyTickets.GetTiketsBottomSheet.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.fragment_ticket__bottom_sheet.view.*
import scylla.task.scyllatask.Helper.openActivity
import scylla.task.scyllatask.Home.HomePage
import scylla.task.scyllatask.Home.MyTickets.GetTiketsBottomSheet.GetTicket_ViewModel.GetTicket_ViewModel
import scylla.task.scyllatask.Home.MyTickets.GetTiketsBottomSheet.GetTicket_ViewModel.GetTicketsViewModelFactory
import scylla.task.scyllatask.Home.MyTickets.GetTiketsBottomSheet.helper.RoundetBottomSheet
import scylla.task.scyllatask.Home.MyTickets.GetTiketsBottomSheet.model.GetTicketModel
import scylla.task.scyllatask.Login.view.Login
import scylla.task.scyllatask.R

class GetTicket : RoundetBottomSheet() ,View.OnClickListener{


    lateinit var myView:View
    lateinit var getTicketViewModel: GetTicket_ViewModel
    lateinit var getTicketInfo: GetTicketModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        myView= inflater.inflate(R.layout.fragment_ticket__bottom_sheet, container, false)
        init()


        return myView
    }


    fun init(){
        myView.event_progress.visibility = View.VISIBLE
        getTicketViewModel= ViewModelProvider(this, GetTicketsViewModelFactory(requireActivity()))[GetTicket_ViewModel::class.java]
        myView.ticket_sheet_dissmis.setOnClickListener(this)
        myView.add_to_wallet.setOnClickListener(this)
        errorMessage()
        requestData()

    }

    @SuppressLint("SetTextI18n")
    fun setInfo(){
        val data = getTicketInfo.ticket.event

       myView.eventName.text = data.name
       myView.eventLocation.text = "${data.location.country} , ${data.location.state} , ${data.location.city}"
       myView.eventDate.text = "${data.startDate.substring(0,10) } - ${data.endDate.substring(0,10)}"
        myView.event_progress.visibility = View.GONE
    }


    override fun onClick(v: View?) {
        when(v){
            myView.ticket_sheet_dissmis->dismiss()
            myView.add_to_wallet->dismiss()
        }
    }


    fun requestData(){

        getTicketViewModel.GetTicketsRequest()
        getTicketViewModel.getTicketLive.observe(requireActivity(), Observer {
            getTicketInfo=it
            setInfo()
        })
    }

    fun errorMessage(){
        getTicketViewModel.error.observe(requireActivity(),{
            Handler(Looper.getMainLooper()).postDelayed({
                dismiss()

            }, 1000)



        })
    }




}