package com.example.e_sant.activities.activities.activities

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuItemCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.e_sant.R
import com.example.e_sant.activities.activities.adapters.OnDoctorClickListener
import com.example.e_sant.activities.activities.adapters.OnSearchItemClickListener
import com.example.e_sant.activities.activities.adapters.SearchAdapter
import com.example.e_sant.activities.activities.fragments.HomePatientFragment
import com.example.e_sant.activities.activities.fragments.ProfileFragment
import com.example.e_sant.activities.activities.models.Doctor
import com.example.e_sant.activities.activities.services.RetrofitService
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_home_patient.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomePatientActivity : AppCompatActivity(), HomePatientFragment.OnFragmentInteractionListener,
    ProfileFragment.OnFragmentInteractionListener, OnSearchItemClickListener {

    private var mSectionsPagerAdapter: SectionsPagerAdapter? = null
    private var mSearchAdapter: SearchAdapter? = null
    private var doctorItemSearch = ArrayList<Doctor>()
    lateinit var searchRecyclerView : RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_patient)

        // set the toolbar
        setSupportActionBar(toolbar)

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = SectionsPagerAdapter(supportFragmentManager)

        // Set up the ViewPager with the sections adapter.
        home_patient_container.adapter = mSectionsPagerAdapter
        home_patient_container.currentItem = 0

        home_patient_container.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabs))
        tabs.addOnTabSelectedListener(TabLayout.ViewPagerOnTabSelectedListener(home_patient_container))

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        searchRecyclerView = findViewById<RecyclerView>(R.id.search_recycler_view) as RecyclerView
        searchRecyclerView.apply {
            layoutManager = LinearLayoutManager(this.context)
        }

        val inflater = menuInflater
        inflater.inflate(R.menu.home_menu, menu)

        val searchItem = menu?.findItem(R.id.search)
        val searchView = searchItem?.actionView as SearchView

        searchView.setOnCloseListener(object : SearchView.OnCloseListener {
            override fun onClose(): Boolean {
                return true
            }
        })

        val searchPlate = searchView.findViewById(androidx.appcompat.R.id.search_src_text) as EditText
        searchPlate.hint = "Search"

        MenuItemCompat.setOnActionExpandListener(
            searchItem,
            object : MenuItemCompat.OnActionExpandListener {
                override fun onMenuItemActionExpand(item: MenuItem): Boolean {
                    searchRecyclerView.visibility = View.VISIBLE
                    return true
                }

                override fun onMenuItemActionCollapse(item: MenuItem): Boolean {
                    searchRecyclerView.visibility = View.GONE
                    return true
                }
            })

        searchRecyclerView.addItemDecoration(
            DividerItemDecoration(
                applicationContext,
                LinearLayoutManager.VERTICAL
            )
        )
        searchRecyclerView.setHasFixedSize(true)

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                mSearchAdapter?.filter?.filter(newText)
                return false
            }

        })

        getDoctors()
        return super.onCreateOptionsMenu(menu)
    }

    inner class SectionsPagerAdapter(fm: FragmentManager) :
        FragmentPagerAdapter(fm, FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

        override fun getItem(position: Int): Fragment {
            return when (position) {
                0 -> HomePatientFragment.newInstance(doctorItemSearch)
                1 -> ProfileFragment()
                else -> Fragment()
            }
        }

        override fun getCount(): Int {
            // Show 5 total pages.
            return 2
        }

    }

    override fun onFragmentInteraction(uri: Uri) {

    }

    override fun onSearchItemClicked(doctor: Doctor) {
    }

    fun getDoctors(){
        val call = RetrofitService.instance.getDoctor()
        call.enqueue(object : Callback<List<Doctor>> {
            override fun onFailure(call: Call<List<Doctor>>, t: Throwable) {
                Toast.makeText(this@HomePatientActivity, "erreur", Toast.LENGTH_LONG).show()

            }

            override fun onResponse(call: Call<List<Doctor>>, response: Response<List<Doctor>>) {
                if (response.isSuccessful) {
                    doctorItemSearch = response.body()!! as ArrayList<Doctor>
                    mSearchAdapter = SearchAdapter(doctorItemSearch, this@HomePatientActivity)
                    searchRecyclerView.apply {
                        adapter = mSearchAdapter
                    }

                } else {
                    Toast.makeText(this@HomePatientActivity, "erreur2", Toast.LENGTH_LONG).show()
                    println("Error message " + response.message())

                }

            }
        })
    }

}
