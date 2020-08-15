package com.example.e_sant.activities.activities.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.e_sant.R
import com.example.e_sant.activities.activities.models.Doctor
import com.example.e_sant.activities.activities.viewHolders.DoctorViewHolder

class DoctorAdapter (
    val doctorList: ArrayList<Doctor>,
    val itemClickListener: OnDoctorClickListener
) : RecyclerView.Adapter<DoctorViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DoctorViewHolder {
        var v = LayoutInflater.from(parent.context).inflate(R.layout.item_doctor, parent, false)
        return DoctorViewHolder(v)
    }

    override fun getItemCount(): Int {
        return doctorList.size
    }

    override fun onBindViewHolder(holder: DoctorViewHolder, position: Int) {
        val doctor: Doctor = doctorList[position]
        holder.bind(doctor, itemClickListener)
    }

}

interface OnDoctorClickListener{
    fun onDoctorClicked(doctor: Doctor)
}