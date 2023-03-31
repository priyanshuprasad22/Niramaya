package com.example.niramaya_health;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.niramaya_health.adapters.PostTextItem;
import com.example.niramaya_health.adapters.TimelineAdapter;
import com.example.niramaya_health.models.Symptom;
import com.example.niramaya_health.models.UserUpcomingAppoint;
import com.example.niramaya_health.models.patient_medical_data;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Upcoming_appoint_checkup extends AppCompatActivity {


    TextView name,contact,email,patientid;
    EditText symptoms;

    EditText doctor_added_symptom,height,weight,o2level,bloodpressure,medication,advices,treatmentplan,diagnosis,allergies;

    CheckBox followUpCheckbox;
    EditText followupdate;

    Button viewdata,submit;

    RelativeLayout relativeLayout;
    Symptom symptom;

    private RecyclerView timelineRV;
    private TimelineAdapter adapter;
//    ArrayList<patient_medical_data> upcomingAppointArrayList;
//    ArrayList<TimelineItem> mdata;

    List<patient_medical_data> symptomList;
    List<TimelineItem> mData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upcoming_appoint_checkup);

        followUpCheckbox = findViewById(R.id.follow_up_checkbox);
        followupdate=findViewById(R.id.follow_up_date_time);

        symptom= (Symptom) getIntent().getSerializableExtra("symptominfo");

        name=findViewById(R.id.name_patient);
        patientid=findViewById(R.id.patient_id);
        contact=findViewById(R.id.contact_patient);
        email=findViewById(R.id.email_patient);

        symptoms=findViewById(R.id.symptoms_edttext);
        doctor_added_symptom=findViewById(R.id.doctor_symptoms_edttext);

        viewdata=findViewById(R.id.patient_medical_data);
        submit=findViewById(R.id.submit_btn);

        height=findViewById(R.id.patient_height);
        weight=findViewById(R.id.patient_weight);
        o2level=findViewById(R.id.patient_O2_level);
        bloodpressure=findViewById(R.id.patient_blood_pressure);

        medication=findViewById(R.id.patient_medication);
        advices=findViewById(R.id.medical_advices);
        treatmentplan=findViewById(R.id.treatment_plan);
        diagnosis=findViewById(R.id.diagnosis_appoint);
        allergies=findViewById(R.id.allegies);

        relativeLayout=findViewById(R.id.activity_appoint_checkup);



        viewdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String patientid=symptom.getUserId();

                symptomList=new ArrayList<>();
                mData=new ArrayList<>();



                DatabaseReference database=FirebaseDatabase.getInstance().getReference("User/"+patientid+"/Diagnosis");

                database.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        for(DataSnapshot dataSnapshot:snapshot.getChildren())
                        {
                            patient_medical_data userUpcomingAppoint=dataSnapshot.getValue(patient_medical_data.class);

                            String doctorname=dataSnapshot.child("doctorname").getValue(String.class);
                            String dateofvisit=dataSnapshot.child("dateofvist").getValue(String.class);
                            String time="20:00 P.M";
                            String followup=dataSnapshot.child("followup").getValue(String.class);
                            String medicine=dataSnapshot.child("medication").getValue(String.class);
                            String diagnosis=dataSnapshot.child("diagonosis").getValue(String.class);

                            PostTextItem postTextItem = new PostTextItem("Doctor name: "+doctorname,
                                    R.drawable.baseline_person_24, time, "Diagnosed for: "+diagnosis, "Date of visit: "+dateofvisit, "Followupdate: "+followup, "Medication: "+medicine);

                            TimelineItem posttextTimelineItem = new TimelineItem(postTextItem);

                            symptomList.add(userUpcomingAppoint);
                            mData.add(posttextTimelineItem);


                        }
                        Intent intent=new Intent(Upcoming_appoint_checkup.this,UserPastDetail.class);
                        intent.putExtra("symptomList", (Serializable) symptomList);
                        intent.putExtra("mData", (Serializable) mData);
                        startActivity(intent);


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


            }
        });




        name.setText(symptom.getUsername());
        patientid.setText(symptom.getUserId());
        contact.setText(symptom.getUsercontact());
        email.setText(symptom.getUseremail());

        Map<String,String> question_answer=new HashMap<>();

        question_answer=symptom.getSymptoms();
        StringBuilder sb = new StringBuilder();

        for(String Key:question_answer.keySet())
        {
            String value=question_answer.get(Key);
            sb.append(Key+":"+value+"\n");

        }
        Log.d("String upcoming",sb.toString());

        symptoms.setText(sb.toString());



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

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Upcoming_appoint_checkup.this,"Medical Record Updated",Toast.LENGTH_LONG).show();
                updatedatabases();
            }
        });


    }

    public void updatedatabases()
    {
        String patientname=name.getText().toString();
        String patientinfo=patientid.getText().toString();
        String doctor_id=FirebaseAuth.getInstance().getCurrentUser().getUid();
        String doctoradvices=advices.getText().toString();
        String doctormedication=medication.getText().toString();
        String doctorname=symptom.getDoctorname();
        String doctorsymptoms=doctor_added_symptom.getText().toString();
        Map<String,String> patientsymptom=symptom.getSymptoms();
        String dateofvisit=symptom.getDateofvist();
        String patientfollowupdate=followupdate.getText().toString();
        String diagonosis=diagnosis.getText().toString();
        String patientallergies=allergies.getText().toString();
        String patienttreatmentplan=treatmentplan.getText().toString();
        String patienteto2level=o2level.getText().toString();
        String patientheight=height.getText().toString();
        String patientweight=weight.getText().toString();
        String patientbloodpressure=bloodpressure.getText().toString();



        DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference();
        databaseReference.child("Professional").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("CompletedDiagnosis").push().setValue(new patient_medical_data(patientname,patientinfo,doctor_id,doctoradvices,doctorname,patientsymptom,doctorsymptoms,dateofvisit,patientallergies,diagonosis,patienttreatmentplan,doctormedication,patientfollowupdate,patientheight,patienteto2level,patientweight,patientbloodpressure,true));

        DatabaseReference databaseReference1=FirebaseDatabase.getInstance().getReference();
        databaseReference1.child("User").child(patientinfo).child("Diagnosis").push().setValue(new patient_medical_data(patientname,patientinfo,doctor_id,doctoradvices,doctorname,patientsymptom,doctorsymptoms,dateofvisit,patientallergies,diagonosis,patienttreatmentplan,doctormedication,patientfollowupdate,patientheight,patienteto2level,patientweight,patientbloodpressure,true));

        relativeLayout.setEnabled(false);
        Intent intent=new Intent(Upcoming_appoint_checkup.this,Activity_professional.class);
        startActivity(intent);

    }

//    private void setupAdapter() {
//
//
//
//    }

//    private void getListData() {
//
//        UserData.getTimelineData(new UserData.DataCallback() {
//            @Override
//            public void onDataReceived(List<TimelineItem> data, List<patient_medical_data> diagnosis) {
//                mData = data;
//                symptomList=diagnosis;
//
//                for(TimelineItem item:mData)
//                {
//                    PostTextItem postTextItem=item.getPostTextItem();
//                    Log.d("DoctorName",postTextItem.getPostText());
//                }
//                setupAdapter();
//
//
//
//            }
//        });
//
//    }
}