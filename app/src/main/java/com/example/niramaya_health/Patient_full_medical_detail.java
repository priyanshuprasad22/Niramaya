package com.example.niramaya_health;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Patient_full_medical_detail extends AppCompatActivity {

    boolean isEditTextEnabled;
    Button button_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_full_medical_detail);

        isEditTextEnabled=false;
        button_list=findViewById(R.id.btn_update_list);

    }
//    public void onEnableButtonClick(View view) {
//
//        isEditTextEnabled=!isEditTextEnabled;
//
//
//
//        EditText editText1 = findViewById(R.id.edt_patientlist_advice);
//        EditText editText2 = findViewById(R.id.edt_patientlist_bloodpressure);
//        EditText editText3 = findViewById(R.id.edt_patientlist_follow_up);
//        EditText editText4 = findViewById(R.id.edt_patientlist_height);
//        EditText editText5 = findViewById(R.id.edt_patientlist_level);
//        EditText editText6 = findViewById(R.id.edt_patientlist_symptom);
//        EditText editText7 = findViewById(R.id.edt_patientlist_weight);
//
//        editText1.setEnabled(isEditTextEnabled);
//        editText2.setEnabled(isEditTextEnabled);
//        editText3.setEnabled(isEditTextEnabled);
//        editText4.setEnabled(isEditTextEnabled);
//        editText5.setEnabled(isEditTextEnabled);
//        editText6.setEnabled(isEditTextEnabled);
//        editText7.setEnabled(isEditTextEnabled);
//
//        if (isEditTextEnabled) {
//            button_list.setText("Save");
//        } else {
//            button_list.setText("Update");
//        }
//
//
//    }
}