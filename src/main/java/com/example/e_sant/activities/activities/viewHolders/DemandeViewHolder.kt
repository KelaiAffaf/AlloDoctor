package com.example.e_sant.activities.activities.viewHolders


import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.e_sant.R
import com.example.e_sant.activities.activities.adapters.OnDemandeClickListener
import com.example.e_sant.activities.activities.models.Demande

class DemandeViewHolder (itemView: View): RecyclerView.ViewHolder(itemView) {
    val demandeTitle = itemView.findViewById<TextView>(R.id.demande_title) as TextView
    val demandeDate = itemView.findViewById<TextView>(R.id.demande_date) as TextView

    fun bind(demande: Demande, clickListener: OnDemandeClickListener) {
        demandeTitle.text = demande.title
        demandeDate.text = demande.date.toString()

        itemView.setOnClickListener {
            clickListener.onDemandeClicked(demande)
        }
    }
}