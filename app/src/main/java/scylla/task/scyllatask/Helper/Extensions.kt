package scylla.task.scyllatask.Helper

import android.app.Activity
import android.app.Fragment
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.text.TextUtils
import android.util.Patterns
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.textfield.TextInputEditText
import kotlinx.android.synthetic.main.activity_home_page.*
import kotlinx.android.synthetic.main.activity_home_page.view.*
import kotlinx.android.synthetic.main.activity_login.*
import scylla.task.scyllatask.R

inline fun FragmentManager.inTransaction(func: FragmentTransaction.()-> Unit) {
    val fragmentTransaction = beginTransaction()
    fragmentTransaction.func()
    fragmentTransaction.commit()
}


fun Context.openActivity(activity: Activity) {
    val intent = Intent(this, activity::class.java)
    startActivity(intent)
}

fun ImageView.navBarChangeColorBTN(context: Context,list:ArrayList<ImageView>){

    list.forEach { it
        if (this == it){
            it.setColorFilter(ContextCompat.getColor(context, R.color.blue), android.graphics.PorterDuff.Mode.SRC_IN)
        }else{
            it.setColorFilter(ContextCompat.getColor(context, R.color.light_white), android.graphics.PorterDuff.Mode.SRC_IN)
        }
    }
}

fun String.setShared_String(activity: Activity, key: String) {
    val editor = activity.getSharedPreferences(key, Context.MODE_PRIVATE)?.edit()
    editor?.putString(key, this)
    editor?.apply()
}

fun String.getShared_String(activity: Activity): String {
    val sharedPreferences = activity.getSharedPreferences(this, Activity.MODE_PRIVATE)
    val result = sharedPreferences.getString(this, "")
    return result.toString()
}

fun Activity.toastMessage( message:String){
    Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
}

fun Context.isOnline(): Boolean {
    val cm =
        this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val netInfo = cm.activeNetworkInfo
    return netInfo != null && netInfo.isConnectedOrConnecting
}


fun Activity.Longvibrate() {
    val vibrator = this.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
    if (Build.VERSION.SDK_INT >= 26) {
        vibrator.vibrate(VibrationEffect.createOneShot(300, VibrationEffect.DEFAULT_AMPLITUDE))
    } else {
        vibrator.vibrate(300)
    }
}

fun TextInputEditText.isValidEmail(): Boolean {
    return !TextUtils.isEmpty(
        ( this as EditText).text?.trim()
    ) && Patterns.EMAIL_ADDRESS.matcher(( this as EditText).text.trim())
        .matches()
}
fun String.isValidEmail(): Boolean {
    return !TextUtils.isEmpty(
        this.trim()
    ) && Patterns.EMAIL_ADDRESS.matcher(this.trim())
        .matches()
}
fun TextInputEditText.textTrim() = (this as EditText).text.trim().toString()
