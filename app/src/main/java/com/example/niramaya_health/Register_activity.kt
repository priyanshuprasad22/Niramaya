package com.example.niramaya_health

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.niramaya_health.models.Professional
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase

class Register_activity : AppCompatActivity() {

    private lateinit var btn_submit: Button
    private lateinit var auth: FirebaseAuth
    private lateinit var edt_name: EditText
    private lateinit var edt_email:EditText
    private lateinit var edt_password:EditText
    private lateinit var edt_mobile:EditText
    private lateinit var edt_registry:EditText
    private lateinit var databaseRef:DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        btn_submit=findViewById(R.id.btn_submit)
        edt_name=findViewById(R.id.edt_professional_name)
        edt_password=findViewById(R.id.edt_password)
        edt_email=findViewById(R.id.edt_email)
        edt_mobile=findViewById(R.id.edt_mobile)
        edt_registry=findViewById(R.id.edt_registry_number)

        auth = FirebaseAuth.getInstance()

        btn_submit.setOnClickListener {

            var name=edt_name.text
            var password=edt_password.text
            var number=edt_mobile.text
            var registrynumber=edt_registry.text
            var email=edt_email.text

            signup(email.toString().trim(),password.toString().trim(),registrynumber.toString().trim(), number.toString().trim(),name.toString().trim())

        }





    }

    private fun signup(email:String, password:String,registry_number:String,mobile_number:String,name:String)
    {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    updatedatabase(email,registry_number,mobile_number,name,auth.currentUser?.uid!!)

                } else {
                    Toast.makeText(this,"Invalid Input",Toast.LENGTH_SHORT).show()
                }
            }

    }

    private fun updatedatabase(email:String,registry_number:String,mobile_number:String,name:String,uid:String)
    {
        databaseRef=FirebaseDatabase.getInstance().getReference()
        databaseRef.child("Professional").child(uid).setValue(Professional(name,email,registry_number,mobile_number))

        Toast.makeText(this,"Successful SignUp",Toast.LENGTH_SHORT).show()

        var intent=Intent(this,Activity_professional::class.java)
        startActivity(intent)


    }
}