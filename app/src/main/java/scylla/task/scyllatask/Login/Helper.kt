package scylla.task.scyllatask.Login

import com.google.android.material.textfield.TextInputLayout
import scylla.task.scyllatask.Helper.isValidEmail

fun LocalPasswordValid(pass:String,layout:TextInputLayout):Boolean{
    var t = false
    if (pass.length in 1..5){
        layout.error = "Password length must be >= 6"
        layout.isErrorEnabled = true

    }else{
        layout.isErrorEnabled = false
        t = pass.isNotEmpty()

    }

    return  t
}
fun LocalEmailValid(email:String,layout:TextInputLayout):Boolean{
    var t = false
    if (!email.isValidEmail() && email.isNotEmpty() ){
        layout.error = "Incorrect email"
        layout.isErrorEnabled = true

    }else{

            layout.isErrorEnabled = false
            t = email.isNotEmpty()
    }
    return  t
}