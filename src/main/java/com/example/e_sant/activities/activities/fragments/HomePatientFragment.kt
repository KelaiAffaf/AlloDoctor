package com.example.e_sant.activities.activities.fragments


import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.e_sant.R
import com.example.e_sant.activities.activities.activities.SymptomsActivity
import com.example.e_sant.activities.activities.adapters.DoctorAdapter
import com.example.e_sant.activities.activities.adapters.OnDoctorClickListener
import com.example.e_sant.activities.activities.models.Doctor
import kotlin.collections.ArrayList

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */

class HomePatientFragment : Fragment(), OnDoctorClickListener {
    // TODO: Rename and change types of parameters
    private var listener: OnFragmentInteractionListener? = null

    var doctors = ArrayList<Doctor>()
    lateinit var recyclerView: RecyclerView
    lateinit var field: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            doctors = it.getSerializable("doctors") as ArrayList<Doctor>
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var v = inflater.inflate(R.layout.fragment_home, container, false)

        recyclerView = v.findViewById<RecyclerView>(R.id.home_patient_recycler_view)

        recyclerView.apply {
            layoutManager = LinearLayoutManager(this.context)
            adapter = DoctorAdapter(doctors, this@HomePatientFragment)
        }

        return v
    }


    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments]
     * (http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(doctors: ArrayList<Doctor>) =
            HomePatientFragment().apply {
                arguments = Bundle().apply {
                    putSerializable("doctors", doctors)
                }
            }
    }

    override fun onDoctorClicked(doctor: Doctor) {
        val i = Intent(this.context, SymptomsActivity::class.java)
        startActivity(i)
    }



    /*fun getDoctors() {

        val call = RetrofitService.instance.getDoctor()
        var list=ArrayList<Doctor>()
        call.enqueue(object : Callback<List<Doctor>> {
            override fun onFailure(call: Call<List<Doctor>>, t: Throwable) {
                Toast.makeText(context, "erreur", Toast.LENGTH_LONG).show()

            }

            override fun onResponse(call: Call<List<Doctor>>, response: Response<List<Doctor>>) {
                if (response.isSuccessful) {
                    list = response.body()!! as ArrayList<Doctor>
                    recyclerView.apply {
                        adapter = DoctorAdapter(list, this@HomePatientFragment)
                    }


                } else {
                    Toast.makeText(context, "erreur2", Toast.LENGTH_LONG).show()
                    println("Error message " + response.message())

                }

            }
        })
    }*/

}
