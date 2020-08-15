package com.example.e_sant.activities.activities.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.e_sant.R
import com.example.e_sant.activities.activities.models.Demande
import com.example.e_sant.activities.activities.viewHolders.DemandeViewHolder

class DemandeAdapter (
    val demandeList: ArrayList<Demande>,
    val itemClickListener: OnDemandeClickListener
) : RecyclerView.Adapter<DemandeViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DemandeViewHolder {
        var v = LayoutInflater.from(parent.context).inflate(R.layout.item_demande, parent, false)
        return DemandeViewHolder(v)
    }

    override fun getItemCount(): Int {
        return demandeList.size
    }

    override fun onBindViewHolder(holder: DemandeViewHolder, position: Int) {
        val demande: Demande = demandeList[position]
        holder.bind(demande, itemClickListener)
    }

}

interface OnDemandeClickListener{
    fun onDemandeClicked(demande: Demande)
}