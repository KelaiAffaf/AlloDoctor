package com.example.e_sant.activities.activities.models

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class Patient (
    @SerializedName("firstname") val firstName:String ,
    @SerializedName("lastname") val lastName:String,
    @SerializedName("email")val email:String,
    @SerializedName("address")val address:String,
    @SerializedName("phone")val phoneNumber:String,
    @SerializedName("pwd")val pwd:String
): Serializable{

     lateinit var context: Context;
     lateinit var  sharedPreferences: SharedPreferences;
     //lateinit var Username:String
    fun removeUser(){
        sharedPreferences.edit().clear().apply();
    }

   fun getUsername(): String? {
        var Username = sharedPreferences.getString("userdata", "");
        return Username
    }

   fun setUsername( username:String) {
        var Username = username;
        sharedPreferences.edit().putString("userdata", username).apply();
    }

    
    public User(Context context){
        this.context = context;
        sharedPreferences = context.getSharedPreferences("userinfo", Context.MODE_PRIVATE);
    }
}
