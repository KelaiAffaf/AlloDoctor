package com.example.e_sant.activities.activities.models

import com.google.gson.annotations.SerializedName

data class Doctor (
    @SerializedName("firstname") val firstName:String,
    @SerializedName("lastname") val lastName:String,
    @SerializedName("field")val field:String,
    @SerializedName("phone")val phoneNumber:String,
    @SerializedName("picture")val picture:String
)