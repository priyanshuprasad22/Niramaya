package com.example.niramaya_health

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TableLayout
import android.widget.TextView
import com.example.niramaya_health.models.Professional
import com.example.niramaya_health.models.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MainActivity : AppCompatActivity() {

    private lateinit var register:TextView
    private lateinit var btn_professional:Button
    private lateinit var user_register:TextView
    private lateinit var btn_user:Button
    private lateinit var auth:FirebaseAuth;

    private lateinit var progressDialog: ProgressDialog;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        progressDialog= ProgressDialog(this)

        progressDialog.setTitle("Please Wait..")
        progressDialog.show()


        auth=FirebaseAuth.getInstance()
        val user= auth.currentUser

        if(user!=null)
        {
            redirectUser(user)

        }
        else
        {
            progressDialog.dismiss()
            setContentView(R.layout.activity_main)

            register=findViewById(R.id.txt_register_from_login)
            btn_professional=findViewById(R.id.btn_professional_login)
            user_register=findViewById(R.id.txt_register_from_login_user)
            btn_user=findViewById(R.id.btn_user_login)




            register.setOnClickListener {
                val intent=Intent(this,Register_activity::class.java)
                startActivity(intent)
            }

            btn_professional.setOnClickListener {
                val intent=Intent(this,professional_login_activity::class.java)
                startActivity(intent)
            }

            user_register.setOnClickListener {
                val intent=Intent(this,user_registration::class.java)
                startActivity(intent)
            }
            btn_user.setOnClickListener {
                val intent=Intent(this,user_login::class.java)
                startActivity(intent)
            }
        }








    }

    private fun redirectUser(user: FirebaseUser) {
        val database = FirebaseDatabase.getInstance()
        val usersRef = database.getReference("Professional")
        val uid = user.uid

        usersRef.child(uid).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val userNode = dataSnapshot.getValue(Professional::class.java)
                if (userNode?.registry_number != null) {
                    // User is a professional, redirect to professional home page
                    progressDialog.dismiss()
                    val professionalIntent = Intent(this@MainActivity, Activity_professional::class.java)
                    startActivity(professionalIntent)
                    finish()
                } else {
                    // User is a regular user, redirect to user home page
                    progressDialog.dismiss()
                    val userIntent = Intent(this@MainActivity, User_Activity::class.java)
                    startActivity(userIntent)
                    finish()
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Log.e("Not Fetched", "onCancelled", databaseError.toException())
            }
        })
    }

}