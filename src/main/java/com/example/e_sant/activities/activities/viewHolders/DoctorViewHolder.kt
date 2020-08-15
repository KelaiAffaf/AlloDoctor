package com.example.e_sant.activities.activities.viewHolders

import android.graphics.BitmapFactory
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.e_sant.R
import com.example.e_sant.activities.activities.adapters.OnDoctorClickListener
import com.example.e_sant.activities.activities.models.Doctor
import java.lang.Exception
import java.net.URL

class DoctorViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    val doctorFullName = itemView.findViewById<TextView>(R.id.doctor_name) as TextView
    val doctorSpeciality = itemView.findViewById<TextView>(R.id.doctor_speciality) as TextView
    val doctorPhoneNumber = itemView.findViewById<TextView> (R.id.doctor_phone_number) as TextView
    val doctorPicture = itemView.findViewById<TextView> (R.id.doctor_picture) as ImageView

    fun bind(doctor: Doctor, clickListener: OnDoctorClickListener){
        doctorFullName.text = "Dr. " + doctor.firstName + " " + doctor.lastName
        doctorSpeciality.text = doctor.field
        doctorPhoneNumber.text = doctor.phoneNumber
        try{
            var url = URL(doctor.picture)
            var image = BitmapFactory.decodeStream(url.openConnection().getInputStream())
            doctorPicture.setImageBitmap(image)
        }catch (e: Exception){

        }

        itemView.setOnClickListener {
            clickListener.onDoctorClicked(doctor)
        }
    }
}