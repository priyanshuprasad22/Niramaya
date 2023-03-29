package com.example.niramaya_health;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.niramaya_health.adapters.patient_data;
import com.example.niramaya_health.models.patient_medical_data;
import com.example.niramaya_health.models.medical_appoint_data;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Patient_List#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Patient_List extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private RecyclerView recyclerView;
    private ArrayList<patient_medical_data> patientlist;

    private FirebaseDatabase firebaseDatabase;
    private FirebaseAuth auth;

    private Button button;

    public Patient_List() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Patient_List.
     */
    // TODO: Rename and change types and number of parameters
    public static Patient_List newInstance(String param1, String param2) {
        Patient_List fragment = new Patient_List();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview=inflater.inflate(R.layout.fragment_patient__list, container, false);
        recyclerView=rootview.findViewById(R.id.recycler_patient_list);

//        button = rootview.findViewById(R.id.update_btn);

        patientlist=new ArrayList<>();

        auth=FirebaseAuth.getInstance();

        setpatient();

//        setbtn_listner();

        return rootview;
    }

//    void setbtn_listner()
//    {
//
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                setdatabase();
//            }
//        });
//
//    }

//    void setdatabase()
//    {
//        firebaseDatabase=FirebaseDatabase.getInstance();
//        DatabaseReference ref=firebaseDatabase.getReference();
//
//        ref.child("Upcoming Apointments").child(auth.getCurrentUser().getUid()).child("Appointments")
//                .push().setValue(new medical_appoint_data("QLRLuJJVdbMj1xfMA1fttXzsxyh1",auth.getCurrentUser().getUid(),"Steve","John","20-03-2023","25-03-2023","5:30 P.M","22333","steve@gmail.com"));
//
//        ref.child("Upcoming Apointments").child(auth.getCurrentUser().getUid()).child("Appointments")
//                .push().setValue(new medical_appoint_data("2mqqk8fUtpZNUrB1LnZKYTC1F4Q2",auth.getCurrentUser().getUid(),"Tim","John","20-03-2023","25-03-2023","6:00 P.M","547777","tim@gmail.com"));
//
//
//    }

    void setpatient()
    {
        firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference ref= firebaseDatabase.getReference("Professional/"+FirebaseAuth.getInstance().getCurrentUser().getUid()+"/CompletedDiagnosis");


        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                    Log.d("The database",childSnapshot.toString());

                    patient_medical_data patient = childSnapshot.getValue(patient_medical_data.class);

                    Log.d("The patient value",patient.toString());

                    patientlist.add(patient);

                    patient_data patientadapter=new patient_data(Patient_List.this.getContext(),patientlist);
                    recyclerView.setAdapter(patientadapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(Patient_List.this.getContext()));


                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });



    }

}