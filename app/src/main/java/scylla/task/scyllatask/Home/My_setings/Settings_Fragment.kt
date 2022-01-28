package scylla.task.scyllatask.Home.My_setings

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_settings_.*
import kotlinx.android.synthetic.main.fragment_settings_.view.*
import scylla.task.scyllatask.Helper.openActivity
import scylla.task.scyllatask.Helper.setShared_String
import scylla.task.scyllatask.Login.view.Login
import scylla.task.scyllatask.R


class Settings_Fragment : Fragment() {

    lateinit var myView:View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        myView =  inflater.inflate(R.layout.fragment_settings_, container, false)

        myView.sign_out.setOnClickListener{
            "".setShared_String(requireActivity(),"UserToken")
            requireActivity().openActivity(Login())
            myView.sign_out_progress.visibility = View.VISIBLE
        }

        return myView
    }


}