package com.example.e_sant.activities.activities.activities

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.example.e_sant.R
import com.example.e_sant.activities.activities.fragments.DescriptionFragment
import com.example.e_sant.activities.activities.fragments.DrugsFragment
import com.example.e_sant.activities.activities.fragments.SymptoPicFragment
import com.example.e_sant.activities.activities.fragments.SymptomsFragment
import kotlinx.android.synthetic.main.activity_symptoms.*

class SymptomsActivity : AppCompatActivity(), SymptomsFragment.OnFragmentInteractionListener,
    DescriptionFragment.OnFragmentInteractionListener, DrugsFragment.OnFragmentInteractionListener,
    SymptoPicFragment.OnFragmentInteractionListener{

    private val fragmentManager = supportFragmentManager
    private val symptomsFragment = SymptomsFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_symptoms)

        symptom_toolbar.title = "Desease"
        setSupportActionBar(symptom_toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.demande_fragment_container, symptomsFragment)
        fragmentTransaction.commit()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onFragmentInteraction(uri: Uri) {
        TODO("Not yet implemented")
    }

}
