package com.example.niramaya_health;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.niramaya_health.adapters.PostTextItem;
import com.example.niramaya_health.models.Symptom;
import com.example.niramaya_health.models.patient_medical_data;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class UserData {

    private static PostTextItem itemHeader;

    public UserData(PostTextItem itemHeader) {
        this.itemHeader = itemHeader;
    }


    public interface DataCallback {
        void onDataReceived(List<TimelineItem> data,List<patient_medical_data> diagnosislist);
    }

    public static void getTimelineData(DataCallback callback) {


        List<TimelineItem> mdata = new ArrayList<>();
        List<patient_medical_data> clickeddiagnosis=new ArrayList<>();

        DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference("User/"+ FirebaseAuth.getInstance().getCurrentUser().getUid()+"/Diagnosis");


        //HeaderTextItem item = new HeaderTextItem("Yesterday");
        // TimelineItem headerTimelineItem  = new TimelineItem(itemHeader);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {


                for(DataSnapshot dataSnapshot:snapshot.getChildren()) {

                    Log.d("UpdateTime",dataSnapshot.toString());

                    patient_medical_data symptom=dataSnapshot.getValue(patient_medical_data.class);

                    String doctorname=dataSnapshot.child("doctorname").getValue(String.class);
                    String dateofvisit=dataSnapshot.child("dateofvist").getValue(String.class);
                    String time="20:00 P.M";
                    String followup=dataSnapshot.child("followup").getValue(String.class);
                    String medicine=dataSnapshot.child("medication").getValue(String.class);
                    String diagnosis=dataSnapshot.child("diagonosis").getValue(String.class);

                    PostTextItem postTextItem = new PostTextItem("Doctor name: "+doctorname,
                            R.drawable.baseline_person_24, time, "Diagnosed for: "+diagnosis, "Date of visit: "+dateofvisit, "Followupdate: "+followup, "Medication: "+medicine);

                    TimelineItem posttextTimelineItem = new TimelineItem(postTextItem);

                    mdata.add(posttextTimelineItem);
                    clickeddiagnosis.add(symptom);

                }
                callback.onDataReceived(mdata,clickeddiagnosis);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });





    }
}
