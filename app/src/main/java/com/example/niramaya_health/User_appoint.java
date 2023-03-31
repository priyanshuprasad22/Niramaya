package com.example.niramaya_health;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.niramaya_health.adapters.Upcoming;
import com.example.niramaya_health.models.UserUpcomingAppoint;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link User_appoint#newInstance} factory method to
 * create an instance of this fragment.
 */
public class User_appoint extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    String doctorname="";
    String doctorlocation="";
    String doctortime="";
    String doctordate="";

    String specialization="";
    RecyclerView recyclerView;

    ImageView imageView;

    ArrayList<UserUpcomingAppoint> userUpcomingAppoints;

    public User_appoint() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment User_appoint.
     */
    // TODO: Rename and change types and number of parameters
    public static User_appoint newInstance(String param1, String param2) {
        User_appoint fragment = new User_appoint();
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
        View rootview=inflater.inflate(R.layout.fragment_user_appoint, container, false);

        recyclerView=rootview.findViewById(R.id.recycle_view_appointment);
        imageView=rootview.findViewById(R.id.img_nodata);
        userUpcomingAppoints=new ArrayList<>();

        DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference("User/"+ FirebaseAuth.getInstance().getCurrentUser().getUid()+"/Appointment");


        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for( DataSnapshot dataSnapshot:snapshot.getChildren())
                {
                    doctorname=dataSnapshot.child("doctorname").getValue(String.class);
                    doctordate=dataSnapshot.child("dateofvist").getValue(String.class);
                    doctorlocation=dataSnapshot.child("location").getValue(String.class);
                    doctortime=dataSnapshot.child("timeofvisit").getValue(String.class);
                    specialization=dataSnapshot.child("specialization").getValue(String.class);

                    UserUpcomingAppoint upcomingAppoint=new UserUpcomingAppoint(doctorname,doctorlocation,doctordate,doctortime,specialization);

                    userUpcomingAppoints.add(upcomingAppoint);




                }

                if(userUpcomingAppoints.size()==0)
                {
                    imageView.setVisibility(View.VISIBLE);
                }

                Upcoming upcoming=new Upcoming(userUpcomingAppoints,User_appoint.this.getContext());
                recyclerView.setAdapter(upcoming);
                recyclerView.setLayoutManager(new LinearLayoutManager(User_appoint.this.getContext()));


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });









        return rootview;
    }
}