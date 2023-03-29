package com.example.niramaya_health;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.niramaya_health.models.Question_Answer;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.Date;

public class question_view extends AppCompatActivity {

    EditText editText;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_view);

        editText=findViewById(R.id.about_answer);
        button=findViewById(R.id.btn_save);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FirebaseAuth auth=FirebaseAuth.getInstance();

                Date currentDate = new Date();

// Create a calendar instance and set it to the current date and time
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(currentDate);

// Get the year, month, and day from the calendar instance
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH) + 1; // Note: month is 0-indexed
                int day = calendar.get(Calendar.DAY_OF_MONTH);

// Print the date in YYYY-MM-DD format
                String formattedDate = String.format("%d-%02d-%02d", year, month, day);

                DatabaseReference databaseReference=FirebaseDatabase.getInstance().getReference();
                databaseReference.child("User").child(auth.getCurrentUser().getUid()).child("Symptoms").child(formattedDate).push().setValue(new Question_Answer("about",editText.getText().toString(),formattedDate));


                Toast.makeText(question_view.this,"Your Symptoms are saved",Toast.LENGTH_LONG).show();
                Intent intent=new Intent(question_view.this,Doctor_list.class);
                intent.putExtra("formatteddate",formattedDate);
                startActivity(intent);
            }
        });






    }
}