package com.example.e_sant.activities.activities.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.e_sant.R
import kotlinx.android.synthetic.main.activity_signup_account.*

class SignupAccountActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup_account)

        card_doctor.setOnClickListener {
            val i = Intent(this, SignupActivity::class.java)
            i.putExtra("from", "doctor")
            startActivity(i)
        }

        card_patient.setOnClickListener {
            val i = Intent(this, SignupActivity::class.java)
            i.putExtra("from", "patient")
            startActivity(i)
        }
    }
}
