package scylla.task.scyllatask.Home.MyTickets.MyTicketsViewModel

import android.app.Activity
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.android.synthetic.main.fragment_my_tikets.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import scylla.task.scyllatask.Helper.Longvibrate
import scylla.task.scyllatask.Helper.isOnline
import scylla.task.scyllatask.Helper.toastMessage
import scylla.task.scyllatask.Home.MyTickets.api.MyTickets_Request
import scylla.task.scyllatask.Home.MyTickets.model.MyTicketsModel
import scylla.task.scyllatask.Home.MyTickets.model.MyTickets_Request_Model
import scylla.task.scyllatask.Network.RetrofitClient

class MyTicketsViewModel(val activity: Activity): ViewModel() {

    var ticketsFirstPageResponse = MutableLiveData<MyTicketsModel>()
    var error = MutableLiveData<String>()


    fun MyTicketsRequest(requestData : MyTickets_Request_Model) {
        val service = RetrofitClient.getClient().create(MyTickets_Request::class.java)
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = service.MyTickets(requestData.page,requestData.limit,requestData.q)
                val data = response.body()
                withContext(Dispatchers.Main) {

                    if (data?.statusCode == 200) {

                            ticketsFirstPageResponse.value = data!!

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