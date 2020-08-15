package com.example.e_sant.activities.activities.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.example.e_sant.R
import com.example.e_sant.activities.activities.models.Doctor
import com.example.e_sant.activities.activities.viewHolders.SearchViewHolder
import java.util.*
import kotlin.collections.ArrayList

class SearchAdapter (
    var doctorList: ArrayList<Doctor>,
    val itemClickListener: OnSearchItemClickListener
) : RecyclerView.Adapter<SearchViewHolder>(), Filterable {

    var filterList = ArrayList<Doctor>()

    init {
        filterList = doctorList
    }

    override fun getItemCount(): Int {
        return filterList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        var v = LayoutInflater.from(parent.context).inflate(R.layout.item_doctor, parent, false)
        return SearchViewHolder(v)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val commonItemSearch: Doctor = filterList[position]
        holder.bind(commonItemSearch, itemClickListener)
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                if (charSearch.isEmpty()) {
                    filterList = doctorList
                } else {
                    val resultList = ArrayList<Doctor>()
                    for (row in doctorList) {
                        if (row.field?.toLowerCase(Locale.ROOT)?.contains(charSearch.toLowerCase(
                                Locale.ROOT))!!) {
                            resultList.add(row)
                        }
                    }
                    filterList = resultList
                }
                val filterResults = FilterResults()
                filterResults.values = filterList
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                filterList = results?.values as ArrayList<Doctor>
                notifyDataSetChanged()
            }

        }
    }

}

interface OnSearchItemClickListener {
    fun onSearchItemClicked(doctor: Doctor)
}
