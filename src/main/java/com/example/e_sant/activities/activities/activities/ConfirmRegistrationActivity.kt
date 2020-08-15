package com.example.e_sant.activities.activities.activities


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast

import com.example.e_sant.R
import com.example.e_sant.activities.activities.models.Patient
import com.example.e_sant.activities.activities.services.RetrofitService
import kotlinx.android.synthetic.main.activity_confirm_registration.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ConfirmRegistrationActivity : AppCompatActivity() {
    lateinit var patient: Patient
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirm_registration)
        patient = intent.getSerializableExtra("patient") as Patient

        field_one.addTextChangedListener(
            object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {
                    if (field_one.text.length > 0) {
                        field_two.requestFocus()
                    }
                }

                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {

                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            })

        field_two.addTextChangedListener(
            object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {
                    if (field_two.text.length > 0) {
                        field_three.requestFocus()
                    }
                }

                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {

                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            })

        field_three.addTextChangedListener(
            object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {
                    if (field_three.text.length > 0) {
                        field_four.requestFocus()
                    }
                }

                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {

                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            }
        )

        btn_confirm_signup.setOnClickListener {
            var code = field_one.text.toString().toInt() * 1000 + field_two.text.toString()
                .toInt() * 100 + field_three.text.toString()
                .toInt() * 10 + field_four.text.toString().toInt()

            RetrofitService.instance.RegisterPatient(
                patient.firstName,
                patient.lastName,
                patient.email,
                patient.pwd,
                patient.phoneNumber,
                patient.address,
                code
            )
                .enqueue(object :
                    Callback<Patient> {

                    override fun onResponse(call: Call<Patient>, response: Response<Patient>) {
                        if (response.isSuccessful) {

                            Toast.makeText(
                                this@ConfirmRegistrationActivity,
                                "Success",
                                Toast.LENGTH_LONG
                            ).show()


                        } else {
                            Toast.makeText(
                                this@ConfirmRegistrationActivity,
                                "Failure1",
                                Toast.LENGTH_LONG
                            ).show()

                        }
                    }

                    override fun onFailure(call: Call<Patient>, t: Throwable) {
                        //Toast.makeText(this@ConfirmRegistrationActivity,"Failure2", Toast.LENGTH_LONG).show()
                    }
                })
            val i = Intent(this@ConfirmRegistrationActivity, HomePatientActivity::class.java)
            startActivity(i)
        }

    }
}
