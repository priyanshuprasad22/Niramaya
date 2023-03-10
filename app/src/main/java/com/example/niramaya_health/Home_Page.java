package com.example.niramaya_health;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

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

public class Home_Page extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    AutoCompleteTextView autoCompleteTextView ;
    RecyclerView recyclerView;

    ArrayList<Trends> topiclist;

    String[] topic;

    private static String url=" ";


    private String mParam1;
    private String mParam2;

    public Home_Page() {

    }

    public static Home_Page newInstance(String param1, String param2) {
        Home_Page fragment = new Home_Page();
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
        View rootview= inflater.inflate(R.layout.fragment_home__page, container, false);
        autoCompleteTextView=rootview.findViewById(R.id.autoCompleteTextView);
        recyclerView=rootview.findViewById(R.id.feed_list);
        topic=getResources().getStringArray(R.array.currenttopics);

        topiclist=new ArrayList<>();
        settrends();
        settopic();

        return rootview;



    }

    public void settrends() {
        RequestQueue queue = Volley.newRequestQueue(Home_Page.this.getContext());
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
                        TrendsAdapter adapter = new TrendsAdapter(Home_Page.this.getContext(), topiclist);
                        recyclerView.setAdapter(adapter);
                        recyclerView.setLayoutManager(new LinearLayoutManager(Home_Page.this.getContext()));
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

    public void settopic() {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), R.layout.dropdown, topic);

        autoCompleteTextView.setAdapter(adapter);

        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = parent.getItemAtPosition(position).toString();
            }
        });
    }



}