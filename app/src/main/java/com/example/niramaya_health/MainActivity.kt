package com.example.niramaya_health

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TableLayout
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var register:TextView
    private lateinit var btn_professional:Button
    private lateinit var user_register:TextView
    private lateinit var btn_user:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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