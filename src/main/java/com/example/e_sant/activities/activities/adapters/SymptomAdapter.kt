package com.example.e_sant.activities.activities.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.e_sant.R
import com.example.e_sant.activities.activities.models.Symptom
import com.example.e_sant.activities.activities.viewHolders.SymptomViewHolder

class SymptomAdapter (
    val symptomList: ArrayList<Symptom>,
    val itemClickListener: OnSymptomClickListener
) : RecyclerView.Adapter<SymptomViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SymptomViewHolder {
        var v = LayoutInflater.from(parent.context).inflate(R.layout.item_symptom, parent, false)
        return SymptomViewHolder(v)
    }

    override fun getItemCount(): Int {
        return symptomList.size
    }

    override fun onBindViewHolder(holder: SymptomViewHolder, position: Int) {
        val symptom: Symptom = symptomList[position]
        holder.bind(symptom, itemClickListener)
    }

}

interface OnSymptomClickListener{
    fun onSymptomClicked(symptom: Symptom)
}