package com.example.e_sant.activities.activities.activities

import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.e_sant.R
import com.example.e_sant.activities.activities.fragments.Signup1Fragment
import com.example.e_sant.activities.activities.fragments.Signup2Fragment

class SignupActivity : AppCompatActivity(), Signup1Fragment.OnFragmentInteractionListener,
    Signup2Fragment.OnFragmentInteractionListener{

    private val fragmentManager = supportFragmentManager
    private val signUp1Fragment = Signup1Fragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        val bundle = intent.extras
        val from = bundle!!.getString("from")

        System.out.println("from: " + from)

        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container, signUp1Fragment)
        fragmentTransaction.commit()
    }

    override fun onFragmentInteraction(uri: Uri) {
        TODO("Not yet implemented")
    }
}
