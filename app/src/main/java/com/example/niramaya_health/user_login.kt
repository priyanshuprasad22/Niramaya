package com.example.niramaya_health

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class user_login : AppCompatActivity() {

    private lateinit var email:EditText
    private lateinit var password:EditText
    private lateinit var btn_sigin:Button

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_login)

        email=findViewById(R.id.edt_user_email_login)
        password=findViewById(R.id.edt_user_password_login)

        auth=FirebaseAuth.getInstance();

        btn_sigin=findViewById(R.id.btn_user_sign_in)

        btn_sigin.setOnClickListener {
            var useremail=email.text
            var userpassword=password.text

            signIn(useremail.toString().trim(),userpassword.toString().trim())



        }






    }
    private fun signIn(email:String,password:String)
    {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    var intent = Intent(this,User_Activity::class.java)
                    startActivity(intent)

                } else {
                    Toast.makeText(this,"Invalid Input", Toast.LENGTH_SHORT).show()

                }
            }
    }
}