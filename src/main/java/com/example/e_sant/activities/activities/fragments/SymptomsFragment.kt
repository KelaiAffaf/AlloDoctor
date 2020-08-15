package com.example.e_sant.activities.activities.fragments

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.e_sant.R
import com.example.e_sant.activities.activities.activities.LoginActivity
import com.example.e_sant.activities.activities.adapters.OnSymptomClickListener
import com.example.e_sant.activities.activities.adapters.SymptomAdapter
import com.example.e_sant.activities.activities.enumerations.SymptomType
import com.example.e_sant.activities.activities.models.Symptom
import com.google.android.flexbox.AlignItems
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager
import kotlinx.android.synthetic.main.activity_symptoms.*
import kotlinx.android.synthetic.main.fragment_symptoms.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SymptomsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SymptomsFragment : Fragment(), OnSymptomClickListener {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var listener: OnFragmentInteractionListener? = null
    private var descriptionFragment = DescriptionFragment()

    var symptoms =  ArrayList<Symptom>()
    var selectedSymptoms = ArrayList<Symptom>()

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
        var v = inflater.inflate(R.layout.fragment_symptoms, container, false)

        val symptomRecyclerView = v.findViewById<RecyclerView>(R.id.symptoms_recycler_view) as RecyclerView

        symptoms.add(
            Symptom(0, SymptomType.Headache)
        )
        symptoms.add(
            Symptom(0, SymptomType.Stomacache)
        )
        symptoms.add(
            Symptom(0, SymptomType.Fever)
        )
        symptoms.add(
            Symptom(0, SymptomType.Flue)
        )

        val flexboxLayoutManager = FlexboxLayoutManager(this.context).apply {
            flexWrap = FlexWrap.WRAP
            flexDirection = FlexDirection.ROW
            alignItems = AlignItems.STRETCH
        }

        symptomRecyclerView.apply {
            layoutManager = flexboxLayoutManager
            adapter = SymptomAdapter(symptoms, this@SymptomsFragment)
        }

        var btnSymptomsNext = v.findViewById<Button>(R.id.btn_symptoms_next) as Button

        btnSymptomsNext.setOnClickListener {
            var fragmentTransaction = fragmentManager!!.beginTransaction()
            fragmentTransaction.replace(R.id.demande_fragment_container, descriptionFragment)
            fragmentTransaction.commit()
        }


        return v
    }

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
         * @return A new instance of fragment SymptomsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SymptomsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onSymptomClicked(symptom: Symptom) {
        when(symptom.isChecked){
            true -> selectedSymptoms.add(symptom)
            false ->{
                for(sympto in selectedSymptoms){
                    if (symptom.name == sympto.name){
                        selectedSymptoms.remove(symptom)
                    }
                }
            }
        }
    }
}
