package com.example.e_sant.activities.activities.viewHolders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.e_sant.R
import com.example.e_sant.activities.activities.adapters.OnSearchItemClickListener
import com.example.e_sant.activities.activities.models.Doctor

class SearchViewHolder (itemView: View): RecyclerView.ViewHolder(itemView) {

    val doctorFullName = itemView.findViewById<TextView>(R.id.doctor_name) as TextView
    val doctorSpeciality = itemView.findViewById<TextView>(R.id.doctor_speciality) as TextView
    val doctorPhoneNumber = itemView.findViewById<TextView> (R.id.doctor_phone_number) as TextView
    val doctorPicture = itemView.findViewById<TextView> (R.id.doctor_picture) as ImageView

    fun bind(doctor: Doctor, clickListener: OnSearchItemClickListener){
        doctorFullName.text = "Dr. " + doctor.firstName + " " + doctor.lastName
        doctorSpeciality.text = doctor.field
        doctorPhoneNumber.text = doctor.phoneNumber

        itemView.setOnClickListener {
            clickListener.onSearchItemClicked(doctor)
        }
    }

}