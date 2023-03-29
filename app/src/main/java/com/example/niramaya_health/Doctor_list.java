package com.example.niramaya_health;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Toast;

import com.example.niramaya_health.adapters.DoctorListAdapter;
import com.example.niramaya_health.models.Doctor_full_info;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class Doctor_list extends AppCompatActivity {

    ArrayList<Doctor_full_info> doctor_full_infoArrayList;
    RecyclerView recyclerView;

    ProgressDialog progressDialog;

    String formatteddate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_list);


        progressDialog=new ProgressDialog(this);
        progressDialog.setTitle("Fetching Doctor details...");
        progressDialog.show();


        doctor_full_infoArrayList=new ArrayList<>();
        recyclerView=findViewById(R.id.recycle_doctor_list);

        formatteddate=getIntent().getStringExtra("formatteddate");

        setdoctorlist();

    }

    public void setdoctorlist()
    {
        FirebaseFirestore db=FirebaseFirestore.getInstance();

        db.collection("images").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful())
                {
                    progressDialog.dismiss();
                    for(QueryDocumentSnapshot queryDocumentSnapshot:task.getResult())
                    {
                        Doctor_full_info doctor_full_info=queryDocumentSnapshot.toObject(Doctor_full_info.class);
                        doctor_full_infoArrayList.add(doctor_full_info);
                    }

                    DoctorListAdapter doctorListAdapter=new DoctorListAdapter(Doctor_list.this,doctor_full_infoArrayList,formatteddate);
                    recyclerView.setAdapter(doctorListAdapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(Doctor_list.this));

                }

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Doctor_list.this,"Unable to retrive information",Toast.LENGTH_LONG).show();

            }
        });
    }

}