package com.example.niramaya_health

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.niramaya_health.models.patient_medical_data
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class Patient_detail : AppCompatActivity() {

   lateinit var databaseReference: DatabaseReference
   lateinit var auth:FirebaseAuth
   lateinit var button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_patient_detail)

        auth=FirebaseAuth.getInstance()

        button=findViewById(R.id.update_btn)

        button.setOnClickListener {
            patientdata()

        }




    }
    private fun patientdata()
    {
        databaseReference=FirebaseDatabase.getInstance().getReference()
        databaseReference.child("Patient_medical_data").child(auth.currentUser?.uid!!).child("Patients").push().setValue(patient_medical_data("David","2mqqk8fUtpZNUrB1LnZKYTC1F4Q2",
            listOf("Fever","Chills"),"20-5-22",
            listOf("No"),"No","Done","Normal","Common Cold","Antibiotics",
            listOf("Cough Syrup","Antibiotics"),"23-05-22"
        ))

        databaseReference.child("Patient_medical_data").child(auth.currentUser?.uid!!).child("Patients").push().setValue(patient_medical_data("David","QLRLuJJVdbMj1xfMA1fttXzsxyh1",
            listOf("Fever","Chills"),"20-5-22",
            listOf("No"),"No","Done","Normal","Common Cold","Antibiotics",
            listOf("Cough Syrup","Antibiotics"),"23-05-22"
        ))

        databaseReference.child("Patient_medical_data").child(auth.currentUser?.uid!!).child("Patients").push().setValue(patient_medical_data("David","QLRLuJJVdbMj1xfMA1fttXzsxyh1",
            listOf("Fever","Chills"),"20-5-22",
            listOf("No"),"No","Done","Normal","Common Cold","Antibiotics",
            listOf("Cough Syrup","Antibiotics"),"23-05-22"
        ))


        Toast.makeText(this,"Update Sucessful",Toast.LENGTH_SHORT).show()


    }
}