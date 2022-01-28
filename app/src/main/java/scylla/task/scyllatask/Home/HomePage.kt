package scylla.task.scyllatask.Home

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

import kotlinx.android.synthetic.main.activity_home_page.*
import scylla.task.scyllatask.Helper.SwipeDisabledViewPager
import scylla.task.scyllatask.Helper.getShared_String
import scylla.task.scyllatask.Helper.navBarChangeColorBTN
import scylla.task.scyllatask.Home.MyTickets.view.MyTickets_Fragment
import scylla.task.scyllatask.Home.My_notif.Notification_Fragment
import scylla.task.scyllatask.Home.My_setings.Settings_Fragment
import scylla.task.scyllatask.Home.adapter.HomeViewPagerAdapter
import scylla.task.scyllatask.R

class HomePage : AppCompatActivity(), View.OnClickListener {


    var navBtn =  ArrayList<ImageView>()
    val firstFragment=MyTickets_Fragment()
    val secondFragment=Notification_Fragment()
    val thirdFragment=Settings_Fragment()
    lateinit var homeviewPager: SwipeDisabledViewPager

    companion object{
        var token = ""
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page)
        init()



    }

    fun init(){
        token = "UserToken".getShared_String(this)
        Log.e("hgghghghgh",token)
        navBtn.add(ticket_btn)
        navBtn.add(notif_btn)
        navBtn.add(setings_btn)

        homeviewPager = findViewById(R.id.homeViewPager)
        ticket_btn.setOnClickListener(this)
        notif_btn.setOnClickListener(this)
        setings_btn.setOnClickListener(this)

        viewPagerCreate()
    }

    override fun onClick(v: View?) {
        when(v){
            ticket_btn->{
                ticket_btn.navBarChangeColorBTN(this,navBtn)
                homeviewPager.setCurrentItem(0, true)
            }
            notif_btn->{

                homeviewPager.setCurrentItem(1, true)
                notif_btn.navBarChangeColorBTN(this,navBtn)
            }

           setings_btn->{

               homeviewPager.setCurrentItem(2, true)
                setings_btn.navBarChangeColorBTN(this,navBtn)
           }
        }
    }


    fun viewPagerCreate(){
        homeviewPager.adapter = HomeViewPagerAdapter(supportFragmentManager)
        homeviewPager.offscreenPageLimit = 3
//        homeviewPager.setPageTransformer(true,CubeOutTransformer())
    }

//    private fun setCurrentFragment(fragment: Fragment)=
//        supportFragmentManager.beginTransaction().apply {
//            replace(R.id.home_fragment_container,fragment)
//            commit()
//        }


    override fun onBackPressed() {
        finishAffinity()
    }
}