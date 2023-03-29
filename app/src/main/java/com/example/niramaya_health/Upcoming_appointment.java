package com.example.niramaya_health;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.niramaya_health.adapters.appointment_adapter;
import com.example.niramaya_health.models.Symptom;
import com.example.niramaya_health.models.medical_appoint_data;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Upcoming_appointment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Upcoming_appointment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private RecyclerView recyclerView;

    private FirebaseAuth auth;

    private FirebaseDatabase firebaseDatabase;

    private ArrayList<Symptom> medicalappointlist;

    public Upcoming_appointment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Upcoming_appointment.
     */
    // TODO: Rename and change types and number of parameters
    public static Upcoming_appointment newInstance(String param1, String param2) {
        Upcoming_appointment fragment = new Upcoming_appointment();
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
        View rootview=inflater.inflate(R.layout.fragment_upcoming_appointment, container, false);

        recyclerView=rootview.findViewById(R.id.recycler_upcoming_appointment);

        auth=FirebaseAuth.getInstance();

        medicalappointlist=new ArrayList<>();

        setappointment();






        return rootview;
    }

    void setappointment()
    {
        firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference ref= firebaseDatabase.getReference("Professional/"+FirebaseAuth.getInstance().getCurrentUser().getUid()+"/UpcomingAppointment");

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for(DataSnapshot childSnapshot:snapshot.getChildren())
                {
                    Log.d("The appointment data",childSnapshot.toString());
                    Symptom data=childSnapshot.getValue(Symptom.class);

                    medicalappointlist.add(data);

                    appointment_adapter adapter=new appointment_adapter(Upcoming_appointment.this.getContext(),medicalappointlist);
                    recyclerView.setAdapter(adapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(Upcoming_appointment.this.getContext()));


                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

}