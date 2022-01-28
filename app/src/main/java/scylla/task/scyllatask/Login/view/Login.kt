package scylla.task.scyllatask.Login.view

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_login.*
import scylla.task.scyllatask.Helper.getShared_String
import scylla.task.scyllatask.Helper.openActivity
import scylla.task.scyllatask.Helper.textTrim
import scylla.task.scyllatask.Helper.toastMessage
import scylla.task.scyllatask.Home.HomePage
import scylla.task.scyllatask.Login.LocalEmailValid
import scylla.task.scyllatask.Login.LocalPasswordValid
import scylla.task.scyllatask.Login.SignInViewModel.SignInViewModel
import scylla.task.scyllatask.Login.SignInViewModel.SignInViewModelFactory
import scylla.task.scyllatask.Login.model.SignIn_Model
import scylla.task.scyllatask.Login.model.SignIn_Request_Model
import scylla.task.scyllatask.R


class Login : AppCompatActivity(), View.OnClickListener {

    private  lateinit var vm:SignInViewModel
    private lateinit var loginData:SignIn_Request_Model
    var email =false
    var pass =false

    lateinit var userInfo: SignIn_Model


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        init()



    }



    fun init (){

        Login_and_Pass_LocalValid()
        login_btn.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v){
            login_btn -> this.openActivity(HomePage())
        }
    }

    fun SignIn(){
        login_progress.visibility = View.VISIBLE
        vm = ViewModelProvider(this,SignInViewModelFactory(this))[SignInViewModel::class.java]
        loginData = SignIn_Request_Model(email = login.textTrim(),password = password.textTrim())
        vm.SignIn_Request(loginData)
        signInResponse()
    }

    fun signInResponse(){
        vm.user_info.observe(this,Observer{
            login_progress.visibility = View.GONE
            userInfo = it
            vm.user_info.removeObservers(this)
            this.openActivity(HomePage())

        })

    }





    fun Login_and_Pass_LocalValid(){
        login.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                email =   LocalEmailValid(s.toString(),loginLayout)
                if (email && pass){
                    login_btn.isEnabled = true
                    login_btn.setBackgroundDrawable(resources.getDrawable(R.drawable.sign_in_button_style))
                }else{
                    login_btn.isEnabled = false
                    login_btn.setBackgroundDrawable(resources.getDrawable(R.drawable.style_disable_button))
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })


        password.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
              pass =  LocalPasswordValid(s.toString(),passwordLayout)
                if (email && pass){
                    login_btn.isEnabled = true
                    login_btn.setBackgroundDrawable(resources.getDrawable(R.drawable.sign_in_button_style))
                }else{
                    login_btn.isEnabled = false
                    login_btn.setBackgroundDrawable(resources.getDrawable(R.drawable.style_disable_button))
                }

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })


    }

    override fun onBackPressed() {
        finishAffinity()
    }



}