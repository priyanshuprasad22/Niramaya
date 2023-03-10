package com.example.niramaya_health

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class professional_login_activity : AppCompatActivity() {

    private lateinit var edt_email: EditText
    private lateinit var edt_password: EditText
    private lateinit var btn_submit:Button
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_professional_login)

        edt_email=findViewById(R.id.edt_email_login)
        edt_password=findViewById(R.id.edt_password_login)

        btn_submit=findViewById(R.id.submit_login)

        auth= FirebaseAuth.getInstance()

        btn_submit.setOnClickListener {

            var email=edt_email.text
            var password=edt_password.text

            signIn(email.toString().trim(),password.toString().trim())
        }





    }

    private fun signIn(email:String,password:String)
    {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    var intent = Intent(this,Activity_professional::class.java)
                    startActivity(intent)

                } else {
                    Toast.makeText(this,"Invalid Input",Toast.LENGTH_SHORT).show()

                }
            }
    }

}