package scylla.task.scyllatask.Home.MyTickets.GetTiketsBottomSheet.GetTicket_ViewModel

import android.app.Activity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class GetTicketsViewModelFactory  constructor(private  val activity: Activity): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return GetTicket_ViewModel(activity) as T
    }


}