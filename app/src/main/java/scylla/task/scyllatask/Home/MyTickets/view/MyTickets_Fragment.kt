package scylla.task.scyllatask.Home.MyTickets.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_my_tikets.*
import kotlinx.android.synthetic.main.fragment_my_tikets.view.*
import scylla.task.scyllatask.Helper.isOnline
import scylla.task.scyllatask.Home.MyTickets.MyTicketsViewModel.MyTicketsViewModel
import scylla.task.scyllatask.Home.MyTickets.MyTicketsViewModel.MyTicketsViewModelFactory
import scylla.task.scyllatask.Home.MyTickets.GetTiketsBottomSheet.view.GetTicket
import scylla.task.scyllatask.Home.MyTickets.adapter.MyTickets_Adapter
import scylla.task.scyllatask.Home.MyTickets.adapter.MyTickets_Adapter.Companion.nextPage
import scylla.task.scyllatask.Home.MyTickets.clickListener.TicketClick
import scylla.task.scyllatask.Home.MyTickets.model.Events
import scylla.task.scyllatask.Home.MyTickets.model.MyTicketsModel
import scylla.task.scyllatask.Home.MyTickets.model.MyTickets_Request_Model
import scylla.task.scyllatask.R

class MyTickets_Fragment : Fragment(), TicketClick, View.OnClickListener {

    lateinit var myTickets_Adapter: MyTickets_Adapter
    lateinit var mainViewModel: MyTicketsViewModel

    lateinit var myTickets: MyTicketsModel
    var myTicketsList: ArrayList<Events> = ArrayList()

    var page = 1
    var nextPageCount = 0

    lateinit var myView: View


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        myView = inflater.inflate(R.layout.fragment_my_tikets, container, false)
        mainViewModel = ViewModelProvider(
            this,
            MyTicketsViewModelFactory(requireActivity())
        )[MyTicketsViewModel::class.java]

        init()

        showTicket()
        return myView
    }


    private fun observeLiveData() {
        mainViewModel.ticketsFirstPageResponse.observe(requireActivity(), {
            myTickets = it
            nextPageCount = myTickets.events.size
            addData(myTicketsList.size)
            if (myTickets.events.isNotEmpty() && myTickets.events.size == 10) {
                page += 1
            }

        })
    }


    fun init() {
        nextPage()
        requestData(page)
        observeLiveData()
        initializeList()
        errorMessage()
        myView.try_again.setOnClickListener(this)
    }

    fun addData(position: Int) {

        myTicketsList += myTickets.events
        nextPageCount = myTickets.events.size
        myView.my_tickets_listView.post(Runnable {
            myTickets_Adapter.notifyItemRangeInserted(
                myTicketsList.size,
                position
            )
        })

        requireActivity().runOnUiThread {
            myTickets_Adapter.notifyDataSetChanged()
        }
        myView.tickets_progress.visibility = View.GONE

    }


    fun requestData(page: Int) {

        if (requireActivity().isOnline()) {
            myView.tickets_progress.visibility = View.VISIBLE
            myView.no_inet_txt.visibility = View.GONE
            myView.try_again.visibility = View.GONE
            mainViewModel.MyTicketsRequest(MyTickets_Request_Model(page, 10, 1))
        } else {
            myView.no_inet_txt.visibility = View.VISIBLE
            myView.try_again.visibility = View.VISIBLE
        }

    }

    private fun initializeList() {
        myView.my_tickets_listView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        myTickets_Adapter = MyTickets_Adapter(requireContext(), myTicketsList, nextPageCount, this)
        myView.my_tickets_listView.adapter = myTickets_Adapter
    }

    private fun showTicket() {
        GetTicket().show(
            requireActivity().supportFragmentManager,
            "Ticket_BottomSheetFragment"
        )
    }

    fun nextPage() {
        nextPage.observe(requireActivity(), {
            requestData(it)
        })
    }

    fun errorMessage(){
        mainViewModel.error.observe(requireActivity(),{
            myView.tickets_progress.visibility = View.GONE
        })
    }

    override fun TicketItemClick(position: Int) {
        showTicket()
    }

    override fun onClick(v: View?) {
        when (v) {
            myView.try_again -> requestData(page)
        }
    }


}