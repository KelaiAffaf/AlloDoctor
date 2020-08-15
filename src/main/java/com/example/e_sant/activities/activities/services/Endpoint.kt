package com.example.e_sant.activities.activities.services

import com.example.e_sant.activities.activities.models.Doctor

import com.example.e_sant.activities.activities.models.Patient
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

import com.example.e_sant.activities.activities.models.LoginResponse
import retrofit2.http.*

interface Endpoint {


    @GET("/getPatient")
    fun getMovie(): Call<List<Patient>>


    @GET("/GetMe")
    fun getMe(): Call<List<Any>>


    @GET("/getDoctors")
    fun getDoctor(): Call<List<Doctor>>


    @FormUrlEncoded
    @POST("/addPatient")

    fun RegisterPatient(
        @Field("firstname") firstname:String,
        @Field("lastname") lastname:String,
        @Field("email") email:String,
        @Field("pwd")pwd:String,
        @Field("phoneNumber")phoneNumber:String,
        @Field("address")address:String,
        @Query("code")code:Int
    ): Call<Patient>


    @FormUrlEncoded
    @POST("/sendSMS")
    fun sendSMS(
        @Field("firstname") firstname:String,
        @Field("lastname") lastname:String,
        @Field("email") email:String,
        @Field("pwd")pwd:String,
        @Field("phoneNumber")phoneNumber:String,
        @Field("address")address:String
    ): Call<Patient>

    @FormUrlEncoded
    @POST("/auth")
    fun auth(
        @Field("PhoneNumber") phoneNumber:String,
        @Field("pwd") password:String
    ): Call<LoginResponse>
}