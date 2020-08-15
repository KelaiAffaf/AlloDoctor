package com.example.e_sant.activities.activities.activities

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.e_sant.R
import com.example.e_sant.activities.activities.fragments.DashboardFragment
import com.example.e_sant.activities.activities.fragments.HomeDoctorFragment
import com.example.e_sant.activities.activities.fragments.ProfileFragment
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_home_doctor.*
import kotlinx.android.synthetic.main.activity_home_patient.*
import kotlinx.android.synthetic.main.activity_home_patient.tabs
import kotlinx.android.synthetic.main.activity_home_patient.toolbar

class HomeDoctorActivity : AppCompatActivity(), DashboardFragment.OnFragmentInteractionListener,
    ProfileFragment.OnFragmentInteractionListener{

    private var mSectionsPagerAdapter: SectionsPagerAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_doctor)

        // set the toolbar
        setSupportActionBar(toolbar)

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = SectionsPagerAdapter(supportFragmentManager)

        // Set up the ViewPager with the sections adapter.
        home_doctor_container.adapter = mSectionsPagerAdapter
        home_doctor_container.currentItem = 0

        home_doctor_container.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabs))
        tabs.addOnTabSelectedListener(TabLayout.ViewPagerOnTabSelectedListener(home_doctor_container))
    }

    class SectionsPagerAdapter(fm: FragmentManager) :
        FragmentPagerAdapter(fm, FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

        override fun getItem(position: Int): Fragment {
            return when (position) {
                0 -> HomeDoctorFragment()
                1 -> DashboardFragment()
                2 -> ProfileFragment()
                else -> Fragment()
            }
        }

        override fun getCount(): Int {
            // Show 5 total pages.
            return 3
        }

    }

    override fun onFragmentInteraction(uri: Uri) {
        TODO("Not yet implemented")
    }
}
