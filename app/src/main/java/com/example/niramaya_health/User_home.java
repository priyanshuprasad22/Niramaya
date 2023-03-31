package com.example.niramaya_health;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.niramaya_health.adapters.TrendsAdapter;
import com.example.niramaya_health.adapters.Upcoming;
import com.example.niramaya_health.models.Trends;
import com.example.niramaya_health.models.User;
import com.example.niramaya_health.models.UserUpcomingAppoint;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link User_home#newInstance} factory method to
 * create an instance of this fragment.
 */
public class User_home extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private String url;

    private ArrayList<Trends>topiclist;

    private RecyclerView recyclerView;

    ProgressDialog progressDialog;

    private Button button;

    private LinearLayout linearLayout;
    private LinearLayout linearLayout2,linearLayout3,linearLayout4;

    private RecyclerView recyclerView1;


    public User_home() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment User_home.
     */
    // TODO: Rename and change types and number of parameters
    public static User_home newInstance(String param1, String param2) {
        User_home fragment = new User_home();
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

        View rootview=inflater.inflate(R.layout.fragment_user_home, container, false);

        topiclist=new ArrayList<>();
        recyclerView=rootview.findViewById(R.id.userfeed_recycler_view);
        linearLayout=rootview.findViewById(R.id.linear_insurance);
        linearLayout2=rootview.findViewById(R.id.linear_doctors);
        linearLayout3=rootview.findViewById(R.id.linear_test);
        linearLayout4=rootview.findViewById(R.id.linear_medicine);

        recyclerView1=rootview.findViewById(R.id.userappoint_recycler_view);




        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("users")
                .whereEqualTo("userId", "knXSTQu8e9dekN1ag9YYIgTE8vJ2")
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        if (!queryDocumentSnapshots.isEmpty()) {
                            DocumentSnapshot documentSnapshot = queryDocumentSnapshots.getDocuments().get(0);
                            Object data = documentSnapshot.getData();



                        } else {
                            // No document matches the query
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Handle unsuccessful document read
                    }
                });


        progressDialog=new ProgressDialog(User_home.this.getContext());
        progressDialog.setTitle("Fetching Details..");
        progressDialog.show();
        settrends();
        setappoint();

        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(User_home.this.getContext(),Insurance_user.class);
                startActivity(intent);
            }
        });


        linearLayout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(User_home.this.getContext(),Doctor_list.class);
                startActivity(intent);
            }
        });

        linearLayout3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(User_home.this.getContext(),LabTest.class);
                startActivity(intent);

            }
        });

        linearLayout4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(User_home.this.getContext(),MedList.class);
                startActivity(intent);
            }
        });

        return rootview;

    }

    public void settrends() {
        RequestQueue queue = Volley.newRequestQueue(User_home.this.getContext());
        url = "https://health.gov/myhealthfinder/api/v3/topicsearch.json?lang=en&categoryId=21";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        progressDialog.dismiss();
                        try {
                            JSONObject resourcesObj = response.getJSONObject("Result").getJSONObject("Resources");
                            JSONArray resourceArr = resourcesObj.getJSONArray("Resource");
                            for (int i = 0; i < resourceArr.length(); i++) {
                                Trends trend = new Trends();
                                JSONObject resourceobj = resourceArr.getJSONObject(i);
                                String title = resourceobj.getString("Title");

                                String imageUrl = resourceobj.getString("ImageUrl");
                                String url = resourceobj.getString("AccessibleVersion");
                                String discription = resourceobj.getString("Categories");

                                JSONObject sectionsObj = resourceobj.getJSONObject("Sections");
                                JSONArray sectionArr = sectionsObj.getJSONArray("section");

                                for (int j = 0; j < 1; j++) {
                                    JSONObject sectionObj = sectionArr.getJSONObject(j);
                                    String sectionContent = sectionObj.getString("Content");
                                    trend.setContent(sectionContent);
                                }
                                trend.setUrl(url);
                                trend.setDiscription(discription);
                                trend.setImage_url(imageUrl);
                                trend.setTitle(title);

                                topiclist.add(trend);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        Log.d("Size of topiclist", Integer.toString(topiclist.size()));
                        TrendsAdapter adapter = new TrendsAdapter(User_home.this.getContext(), topiclist);
                        recyclerView.setAdapter(adapter);
                        recyclerView.setLayoutManager(new LinearLayoutManager(User_home.this.getContext()));
                        ViewCompat.setNestedScrollingEnabled(recyclerView, true);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle error.
                    }
                });
        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(
                30000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
        ));
        queue.add(jsonObjectRequest);
    }

    public void setappoint()
    {
        DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference("User/"+ FirebaseAuth.getInstance().getCurrentUser().getUid()+"/Appointment");


        ArrayList<UserUpcomingAppoint> userUpcomingAppoints=new ArrayList<>();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for( DataSnapshot dataSnapshot:snapshot.getChildren())
                {
                    String doctorname=dataSnapshot.child("doctorname").getValue(String.class);
                    String doctordate=dataSnapshot.child("dateofvist").getValue(String.class);
                    String doctorlocation=dataSnapshot.child("location").getValue(String.class);
                    String doctortime=dataSnapshot.child("timeofvisit").getValue(String.class);
                    String specialization=dataSnapshot.child("specialization").getValue(String.class);

                    UserUpcomingAppoint upcomingAppoint=new UserUpcomingAppoint(doctorname,doctorlocation,doctordate,doctortime,specialization);

                    userUpcomingAppoints.add(upcomingAppoint);




                }

                Upcoming upcoming=new Upcoming(userUpcomingAppoints,User_home.this.getContext());
                recyclerView1.setAdapter(upcoming);
                recyclerView1.setLayoutManager(new LinearLayoutManager(User_home.this.getContext()));


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


}