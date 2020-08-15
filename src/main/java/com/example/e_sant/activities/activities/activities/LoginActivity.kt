package com.example.e_sant.activities.activities.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.e_sant.R
import com.example.e_sant.activities.activities.models.LoginResponse
import com.example.e_sant.activities.activities.services.RetrofitService
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        var phoneNumber=findViewById<EditText>(R.id.phone_number).toString()
        var pwd = findViewById<EditText>(R.id.password).toString()

       // Check if UserResponse is Already Logged In


        text_signUp.setOnClickListener {
            val i = Intent(this, SignupAccountActivity::class.java)
            startActivity(i)
        }

        btn_login.setOnClickListener {


            RetrofitService.instance.auth(phoneNumber,pwd)
                .enqueue(object :
                    Callback<LoginResponse> {

                    override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                        if(response.isSuccessful){

                            Toast.makeText(this@LoginActivity,"Success", Toast.LENGTH_LONG).show()


                        }else{
                            Toast.makeText(this@LoginActivity,"Failure1", Toast.LENGTH_LONG).show()

                        }
                    }

                    override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                        Toast.makeText(this@LoginActivity,"Failure2", Toast.LENGTH_LONG).show()
                    }
                })


            val i = Intent(this, HomeDoctorActivity::class.java)
            startActivity(i)
        }
    }

    fun getMe(){
        RetrofitService.instance.getMe().enqueue(object : Callback <List<Any>> {

                    override fun onResponse(call: Call<List<Any>>, response: Response<List<Any>>) {
                        if(response.isSuccessful){

                            Toast.makeText(this@LoginActivity,"Success", Toast.LENGTH_LONG).show()


                        }else{
                            Toast.makeText(this@LoginActivity,"Failure1", Toast.LENGTH_LONG).show()

                        }
                    }

                    override fun onFailure(call: Call<List<Any>>, t: Throwable) {
                        Toast.makeText(this@LoginActivity,"Failure2", Toast.LENGTH_LONG).show()
                    }
                })
    }
}



