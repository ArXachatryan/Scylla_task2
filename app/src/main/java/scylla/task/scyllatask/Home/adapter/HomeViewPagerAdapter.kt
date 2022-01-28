package scylla.task.scyllatask.Home.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import scylla.task.scyllatask.Home.MyTickets.view.MyTickets_Fragment
import scylla.task.scyllatask.Home.My_notif.Notification_Fragment
import scylla.task.scyllatask.Home.My_setings.Settings_Fragment

class HomeViewPagerAdapter (manager: FragmentManager,
) :
    FragmentPagerAdapter(manager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getCount(): Int {
        return NUM_ITEMS
    }

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> MyTickets_Fragment()
            1 -> Notification_Fragment()
            2 -> Settings_Fragment()
            else -> null
        }!!
    }


    override fun getPageTitle(position: Int): CharSequence {
        return "Page $position"
    }

    companion object {
        private const val NUM_ITEMS = 3
    }

}