package com.example.e_sant.activities.activities.models

import com.google.gson.annotations.SerializedName

class LoginResponse(
    @SerializedName("msg")val message :String,
    @SerializedName("phone")val phoneNumber:String,
    @SerializedName("pwd")val pwd:String
)
