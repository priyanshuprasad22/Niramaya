package com.example.niramaya_health;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.niramaya_health.adapters.LifeInsuranceAdapter;
import com.example.niramaya_health.models.InsuraneModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Insurance_user extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<InsuraneModel> insurancelist;

    ProgressDialog progressDialog;

    String url;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insurance_user);

        insurancelist=new ArrayList<>();
        recyclerView=findViewById(R.id.recycle_insurance);

        progressDialog=new ProgressDialog(this);
        progressDialog.setTitle("Fetching insurances ... ");
        progressDialog.show();


        settrends();







    }

    public void settrends() {
        RequestQueue queue = Volley.newRequestQueue(Insurance_user.this);
        url = "https://api.npoint.io/7894161c7afd317d0ad9";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                progressDialog.dismiss();

                try {
                    Log.d("insurance response",response.toString());

                    for (int i = 0; i < response.length(); i++) {
                        JSONObject jsonObject = response.getJSONObject(i);

                        String amount = jsonObject.getString("amountCovered");
                        String validity = jsonObject.getString("validity");
                        String url = jsonObject.getString("imageUrl");
                        String websiteurl = jsonObject.getString("websiteUrl");
                        String description = jsonObject.getString("description");
                        String insurancename = jsonObject.getString("insurerName");

                        InsuraneModel model = new InsuraneModel(url, insurancename, description, validity, amount, websiteurl);

                        insurancelist.add(model);


                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

                LifeInsuranceAdapter lifeInsuranceAdapter=new LifeInsuranceAdapter(insurancelist,Insurance_user.this);
                recyclerView.setAdapter(lifeInsuranceAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(Insurance_user.this));



            }

        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        queue.add(jsonArrayRequest);

    }








}