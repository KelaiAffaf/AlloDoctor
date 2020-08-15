package com.example.e_sant.activities.activities.viewHolders

import android.view.View
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.e_sant.R
import com.example.e_sant.activities.activities.adapters.OnSymptomClickListener
import com.example.e_sant.activities.activities.models.Symptom

class SymptomViewHolder (itemView: View): RecyclerView.ViewHolder(itemView) {
    val symptomIcon = itemView.findViewById<ImageView>(R.id.symptom_icon) as ImageView
    val symptomName = itemView.findViewById<TextView>(R.id.symptom_name) as TextView

    fun bind(symptom: Symptom, clickListener: OnSymptomClickListener) {
        symptomIcon.setImageResource(symptom.icon)
        symptomName.text = symptom.name.toString()

        itemView.setOnClickListener {
            var checkBox = itemView.findViewById<CheckBox>(R.id.symptom_checkBox) as CheckBox
            when(checkBox.isChecked){
                true -> {
                    checkBox.isChecked = false
                    symptom.isChecked = false
                }
                false -> {
                    checkBox.isChecked = true
                    symptom.isChecked = true
                }
            }
            clickListener.onSymptomClicked(symptom)
        }
    }
}