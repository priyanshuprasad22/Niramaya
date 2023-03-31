package com.example.niramaya_health;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.niramaya_health.adapters.TimelineAdapter;
import com.example.niramaya_health.models.UserUpcomingAppoint;
import com.example.niramaya_health.models.patient_medical_data;

import java.util.List;

public class UserPastDetail extends AppCompatActivity {

    List<patient_medical_data> symptomList;
    List<TimelineItem> mData;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_past_detail);

        symptomList = (List<patient_medical_data>) getIntent().getSerializableExtra("symptomList");
        mData = (List<TimelineItem>) getIntent().getSerializableExtra("mData");

        recyclerView=findViewById(R.id.timeline_recyclerView_doctor);
        TimelineAdapter adapter = new TimelineAdapter(UserPastDetail.this, mData,symptomList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(UserPastDetail.this));



    }
}