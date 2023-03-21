package com.example.niramaya_health;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

public class Upcoming_appoint_checkup extends AppCompatActivity {

    CheckBox followUpCheckbox;
    EditText followupdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upcoming_appoint_checkup);

        followUpCheckbox = findViewById(R.id.follow_up_checkbox);
        followupdate=findViewById(R.id.follow_up_date_time);

        followUpCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b)
                {
                    followupdate.setVisibility(View.VISIBLE);
                }
                else
                {
                    followupdate.setVisibility(View.GONE);
                }
            }
        });


    }
}