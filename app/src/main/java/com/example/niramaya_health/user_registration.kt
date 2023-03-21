package com.example.niramaya_health

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.niramaya_health.models.Professional
import com.example.niramaya_health.models.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class user_registration : AppCompatActivity() {


    private lateinit var name:EditText
    private lateinit var email:EditText
    private lateinit var password:EditText
    private lateinit var number:EditText
    private lateinit var btn_register: Button
    private lateinit var auth:FirebaseAuth
    private lateinit var firebaseDatabase: DatabaseReference



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_registration)

        name=findViewById(R.id.edt_user_name)
        email=findViewById(R.id.edt_user_email)
        password=findViewById(R.id.edt_user_password)
        number=findViewById(R.id.edt_user_number)

        btn_register=findViewById(R.id.btn_user_register)

        auth=FirebaseAuth.getInstance()

        btn_register.setOnClickListener {
            var useremail=email.text
            var userpassword=password.text
            var usernumber=number.text
            var username=name.text

            signup(useremail.toString().trim(),userpassword.toString().trim(),usernumber.toString(),username.toString().trim())


        }




    }
    private fun signup(email:String, password:String,mobile_number:String,name:String)
    {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    updatedatabase(email,mobile_number,name,auth.currentUser?.uid!!)

                } else {
                    Toast.makeText(this,"Invalid Input", Toast.LENGTH_SHORT).show()
                }
            }

    }

    private fun updatedatabase(email:String,mobile_number:String,name:String,uid:String)
    {
        firebaseDatabase= FirebaseDatabase.getInstance().getReference()
        firebaseDatabase.child("User").child(uid).setValue(User(name,email,mobile_number))

        Toast.makeText(this,"Successful SignUp",Toast.LENGTH_SHORT).show()

        var intent= Intent(this,User_Activity::class.java)
        startActivity(intent)


    }
}