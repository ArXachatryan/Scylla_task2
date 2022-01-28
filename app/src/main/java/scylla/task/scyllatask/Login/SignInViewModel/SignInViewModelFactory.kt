package scylla.task.scyllatask.Login.SignInViewModel

import android.app.Activity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import scylla.task.scyllatask.Login.model.SignIn_Request_Model

class SignInViewModelFactory constructor(private  val activity:Activity): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SignInViewModel(activity) as T
    }


}