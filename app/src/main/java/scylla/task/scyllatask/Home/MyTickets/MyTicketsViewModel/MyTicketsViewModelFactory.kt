package scylla.task.scyllatask.Home.MyTickets.MyTicketsViewModel

import android.app.Activity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import scylla.task.scyllatask.Login.SignInViewModel.SignInViewModel

class MyTicketsViewModelFactory  constructor(private  val activity: Activity): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MyTicketsViewModel(activity) as T
    }


}