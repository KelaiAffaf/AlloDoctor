package com.example.e_sant.activities.activities.fragments

import android.Manifest
import android.app.Activity
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast

import com.example.e_sant.R
import com.example.e_sant.activities.activities.activities.ConfirmRegistrationActivity

import kotlinx.android.synthetic.main.fragment_signup2.*
import com.example.e_sant.activities.activities.models.Patient
import com.example.e_sant.activities.activities.services.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

private const val PERMISSION_CODE = 1000
private const val IMAGE_CAPTURE_CODE = 1001

/**
 * A simple [Fragment] subclass.
 * Use the [Signup2Fragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class Signup2Fragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var listener: OnFragmentInteractionListener? = null

    private lateinit var firstname:String
    private lateinit var lastname:String
    private lateinit var emailPatient:String
    private lateinit var passwordPatient:String

    var imageUri:  Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            firstname = it.getString("firstname").toString()
            lastname = it.getString("lastname").toString()
            emailPatient=it.getString("emailPatient").toString()
            passwordPatient=it.getString("pwd").toString()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var v= inflater.inflate(R.layout.fragment_signup2, container, false)

        var btnSignUp = v.findViewById<Button>(R.id.btn_signup) as Button

        var btnTakePicture = v.findViewById<View>(R.id.btn_take_pic) as Button

        btnTakePicture.setOnClickListener {
            takePicture()
        }

        btnSignUp.setOnClickListener {
            val addressPatient =address.text.toString()
            val phoneNumber = phone_number.text.toString(

            )

            if(addressPatient.isEmpty()){
                address.error="first_name is required"
                address.requestFocus()
                return@setOnClickListener
            }

            if(phoneNumber.isEmpty()){
                phone_number.error="Last_name is required"
                phone_number.requestFocus()
                return@setOnClickListener
            }

            //println("test test"+patientInfo.get("firstname")+patientInfo.get("lastname"))
            var patient = Patient(firstname,lastname,emailPatient,passwordPatient,addressPatient,phoneNumber)
            RetrofitService.instance.sendSMS(patient.firstName,patient.lastName,patient.email,patient.pwd,patient.phoneNumber,patient.address)
                .enqueue(object :
                    Callback<Patient> {

                    override fun onResponse(call: Call<Patient>, response: Response<Patient>) {
                        if(response.isSuccessful){

                            Toast.makeText(this@Signup2Fragment.context,"Success", Toast.LENGTH_LONG).show()


                        }else{
                            Toast.makeText(this@Signup2Fragment.context,"Failure1", Toast.LENGTH_LONG).show()

                        }
                    }

                    override fun onFailure(call: Call<Patient>, t: Throwable) {
                        Toast.makeText(this@Signup2Fragment.context,"Failure2", Toast.LENGTH_LONG).show()
                    }
                })
            val i = Intent(this.context, ConfirmRegistrationActivity::class.java)
              i.putExtra("patient",patient)
            startActivity(i)
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(resultCode == Activity.RESULT_OK){

            var bitmap = MediaStore.Images.Media.getBitmap(context?.contentResolver, imageUri)
            picture.rotation = 90F
            picture.setImageBitmap(bitmap)
        }
        super.onActivityResult(requestCode, resultCode, data)
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
         * @return A new instance of fragment Signup2Fragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic

        fun newInstance(firstname: String, lastname: String, emailPatient:String,passwordPatient:String) =
            Signup2Fragment().apply {
                arguments = Bundle().apply {
                    putString("firstname", firstname)
                    putString("lastname", lastname)
                    putString("emailPatient",emailPatient)
                    putString("pwd",passwordPatient)
                }
            }
    }

    fun takePicture() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (context?.checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED
                || context?.checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED
            ) {
                //permission was not enabled
                val permission = arrayOf(
                    Manifest.permission.CAMERA,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                )
                //show pop up to request permission
                requestPermissions(permission, PERMISSION_CODE)
            } else {
                //permission already granted
                openCamera()
            }
        } else {
            //system os is < marshmallow
            openCamera()
        }
    }

    fun openCamera(){
        val values = ContentValues()
        values.put(MediaStore.Images.Media.TITLE, "profile_picture")
        values.put(MediaStore.Images.Media.DESCRIPTION, "From The Camera")
        imageUri = context?.contentResolver?.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri)

        startActivityForResult(cameraIntent, IMAGE_CAPTURE_CODE)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when(requestCode){
            PERMISSION_CODE -> {
                if(grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    //permission from pop up was granted
                    openCamera()
                }else{
                    //permission from pop up was denied
                    Toast.makeText(context, "Permission Denied", Toast.LENGTH_SHORT).show()
                }
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }


    fun getRealPathFromURI(uri: Uri) : String{
        var path = ""
        if (context?.contentResolver != null) {
            var cursor = context?.contentResolver?.query(uri, null, null, null, null);
            if (cursor != null) {
                cursor.moveToFirst()
                var idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA)
                path = cursor.getString(idx)
                cursor.close()
            }
        }
        return path
    }
}
