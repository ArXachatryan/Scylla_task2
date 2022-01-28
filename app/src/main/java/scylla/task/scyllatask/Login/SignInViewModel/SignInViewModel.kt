package scylla.task.scyllatask.Login.SignInViewModel

import android.app.Activity
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import scylla.task.scyllatask.Helper.Longvibrate
import scylla.task.scyllatask.Helper.isOnline
import scylla.task.scyllatask.Helper.setShared_String
import scylla.task.scyllatask.Helper.toastMessage
import scylla.task.scyllatask.Login.api.SignIn_Request
import scylla.task.scyllatask.Login.model.SignIn_Request_Model
import scylla.task.scyllatask.Login.model.SignIn_Model
import scylla.task.scyllatask.Network.RetrofitClient

class SignInViewModel(val activity: Activity) : ViewModel() {

    var user_info = MutableLiveData<SignIn_Model>()


    fun SignIn_Request(repository: SignIn_Request_Model) {
        val service = RetrofitClient.getClient().create(SignIn_Request::class.java)
        viewModelScope.launch(Dispatchers.IO) {

            try {
                val response = service.SignIn(repository)
                val data = response.body()
                withContext(Dispatchers.Main) {

                    if (data?.statusCode == 200) {
                        data.token.access.setShared_String(activity,"UserToken")
                       user_info.value = data!!
                    }else{
                        activity.runOnUiThread {
                            activity.Longvibrate()
                            activity.toastMessage("wrong email or password")
                            activity.login_progress.visibility = View.GONE
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
                    activity.login_progress.visibility = View.GONE
                }

            }

        }

    }
}