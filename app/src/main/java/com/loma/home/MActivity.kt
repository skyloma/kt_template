package com.loma.home

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.widget.TextView
import base.BlankFragment

import com.loma.R
import com.loma.project.ProjectListFragment
import kotlinx.android.synthetic.main.activity_m.*

class MActivity : AppCompatActivity() {


    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                viewPager.setCurrentItem(0)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                viewPager.setCurrentItem(1)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                viewPager.setCurrentItem(2)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_m)
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        viewPager.adapter = SectionsPagerAdapter(supportFragmentManager)
        viewPager.offscreenPageLimit=2
        viewPager.addOnPageChangeListener(object : ViewPager.SimpleOnPageChangeListener() {
            override fun onPageSelected(position: Int) {
                when (position) {
                    0 -> {
                        navigation.setSelectedItemId(R.id.navigation_home)
                    }
                    1 -> {
                        navigation.setSelectedItemId(R.id.navigation_dashboard)
                    }
                    2 -> {
                        navigation.setSelectedItemId(R.id.navigation_notifications)
                    }
                }

            }
        })
    }

    inner class SectionsPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

        override fun getItem(position: Int): Fragment {
            when (position) {
                0, 1 -> return BlankFragment()

                else -> return ProjectListFragment()
            }
        }




        override fun getCount(): Int {
            return 3
        }
    }

}
