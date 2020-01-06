package com.loma.home

import android.support.design.widget.TabLayout
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import base.BlankFragment
import com.loma.ListTestActivity

import com.loma.R
import com.loma.TestVmLiActivity
import com.loma.project.ProjectListFragment
import kotlinx.android.synthetic.main.activity_home.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class HomeActivity : AppCompatActivity() {

    private var mSectionsPagerAdapter: SectionsPagerAdapter? = null
    var mTitle= arrayListOf<CharSequence>("任务","项目","我的")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setSupportActionBar(toolbar)
        container.adapter =SectionsPagerAdapter(supportFragmentManager)
        container.offscreenPageLimit=2

//        container.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabs) )

//        container.addOnPageChangeListener(object : ViewPager.SimpleOnPageChangeListener() {
//            override fun onPageSelected(position: Int) {
//                toast("ddddddddddddddd$position")
//            }
//        })
        tabs.addOnTabSelectedListener(TabLayout.ViewPagerOnTabSelectedListener(container))
        tabs.setupWithViewPager(container)
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", {startActivity<TestVmLiActivity>()}).show()
        }

    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_home, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == R.id.action_settings) {
            return true
        }

        return super.onOptionsItemSelected(item)
    }


    inner class SectionsPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

        override fun getItem(position: Int): Fragment {
            when(position){
                0,1-> return BlankFragment()

                else -> return  ProjectListFragment()
            }
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return mTitle.get(position)
        }


        override fun getCount(): Int {
            return 3
        }
    }

 }
