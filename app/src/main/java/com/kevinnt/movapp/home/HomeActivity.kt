package com.kevinnt.movapp.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.kevinnt.movapp.R
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val homeFragment = HomeFragment()
        val ticketFragment = TicketFragment()
        val settingFragment = SettingFragment()

        setFragment(homeFragment)

        iv_menu1.setOnClickListener {
            setFragment(homeFragment)

            setIcon(iv_menu1, R.drawable.ic_nav_home_active)
            setIcon(iv_menu2, R.drawable.ic_nav_ticket)
            setIcon(iv_menu3, R.drawable.ic_nav_profile)
        }

        iv_menu2.setOnClickListener {
            setFragment(ticketFragment)

            setIcon(iv_menu1, R.drawable.ic_nav_home)
            setIcon(iv_menu2, R.drawable.ic_nav_ticket_active)
            setIcon(iv_menu3, R.drawable.ic_nav_profile)
        }

        iv_menu3.setOnClickListener {
            setFragment(settingFragment)

            setIcon(iv_menu1, R.drawable.ic_nav_home)
            setIcon(iv_menu2, R.drawable.ic_nav_ticket)
            setIcon(iv_menu3, R.drawable.ic_nav_profile_active)
        }


    }

    private fun setFragment(fragment : Fragment){
        supportFragmentManager.beginTransaction()
            .replace(R.id.fl_home, fragment)
            .commit()
    }

    private fun setIcon(iv:ImageView, res:Int){
        iv.setImageResource(res)
    }
}