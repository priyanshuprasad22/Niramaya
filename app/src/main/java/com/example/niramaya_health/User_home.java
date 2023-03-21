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
import android.widget.LinearLayout;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.niramaya_health.adapters.TrendsAdapter;
import com.example.niramaya_health.models.Trends;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

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

    private LinearLayout linearLayout;

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
        settrends();

        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(User_home.this.getContext(),Insurance_user.class);
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


}