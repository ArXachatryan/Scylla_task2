package scylla.task.scyllatask

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import scylla.task.scyllatask.Helper.getShared_String
import scylla.task.scyllatask.Helper.openActivity
import scylla.task.scyllatask.Home.HomePage
import scylla.task.scyllatask.Home.MyTickets.adapter.MyTickets_Adapter
import scylla.task.scyllatask.Login.view.Login

class Splash : AppCompatActivity() {

    var token = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

       token = "UserToken".getShared_String(this)

        validUser()
    }

    fun validUser(){
        Handler(Looper.getMainLooper()).postDelayed({
            if (token.isNotEmpty()){
                this.openActivity(HomePage())
            }else{
                this.openActivity(Login())
            }

        }, 1000)

    }
}