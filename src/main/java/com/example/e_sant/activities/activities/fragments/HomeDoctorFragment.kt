package com.example.e_sant.activities.activities.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.e_sant.R
import com.example.e_sant.activities.activities.adapters.DemandeAdapter
import com.example.e_sant.activities.activities.adapters.OnDemandeClickListener
import com.example.e_sant.activities.activities.models.Demande
import java.util.*
import kotlin.collections.ArrayList

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeDoctorFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeDoctorFragment : Fragment(), OnDemandeClickListener {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    var demandes =  ArrayList<Demande>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var v = inflater.inflate(R.layout.fragment_home_doctor, container, false)

        val homeRecyclerView = v.findViewById<RecyclerView>(R.id.home_doctor_recycler_view)

        demandes.add(
            Demande(
                "first Demande",
                Date()
            )
        )
        demandes.add(
            Demande(
                "second Demande",
                Date()
            )
        )
        demandes.add(
            Demande(
                "third Demande",
                Date()
            )
        )
        demandes.add(
            Demande(
                "fourth Demande",
                Date()
            )
        )
        demandes.add(
            Demande(
                "fifth Demande",
                Date()
            )
        )


        homeRecyclerView.apply {
            layoutManager = LinearLayoutManager(this.context)
            adapter = DemandeAdapter(demandes, this@HomeDoctorFragment)
        }

        return v
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomeDoctorFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeDoctorFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onDemandeClicked(demande: Demande) {
        TODO("Not yet implemented")
    }
}
