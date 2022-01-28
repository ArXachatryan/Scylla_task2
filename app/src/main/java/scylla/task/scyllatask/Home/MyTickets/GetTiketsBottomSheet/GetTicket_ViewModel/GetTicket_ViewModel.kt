package scylla.task.scyllatask.Home.MyTickets.GetTiketsBottomSheet.GetTicket_ViewModel

import android.app.Activity
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.android.synthetic.main.fragment_my_tikets.*
import kotlinx.android.synthetic.main.fragment_ticket__bottom_sheet.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import scylla.task.scyllatask.Helper.Longvibrate
import scylla.task.scyllatask.Helper.isOnline
import scylla.task.scyllatask.Helper.toastMessage
import scylla.task.scyllatask.Home.MyTickets.GetTiketsBottomSheet.api.GetTickets_Request
import scylla.task.scyllatask.Home.MyTickets.GetTiketsBottomSheet.model.GetTicketModel
import scylla.task.scyllatask.Network.RetrofitClient

class GetTicket_ViewModel (val activity: Activity): ViewModel() {

    var getTicketLive = MutableLiveData<GetTicketModel>()
    var error = MutableLiveData<String>()

    fun GetTicketsRequest() {
        val service = RetrofitClient.getClient().create(GetTickets_Request::class.java)
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = service.GetTicket()
                val data = response.body()
                withContext(Dispatchers.Main) {

                    if (data?.statusCode == 200) {
                        getTicketLive.value = data!!

                    }else{
                        activity.runOnUiThread {
                            activity.Longvibrate()
                            activity. toastMessage("no access to the server")
                            error.value ="error"

                        }
                    }
                }
            } catch (e: Exception) {
                activity.runOnUiThread{
                    if (activity.isOnline()){
                        activity. toastMessage("no access to the server")
                    }else{
                        activity. toastMessage("no internet Connection")
                    }
                    activity.Longvibrate()
                    error.value ="error"

                }
            }

        }

    }



}