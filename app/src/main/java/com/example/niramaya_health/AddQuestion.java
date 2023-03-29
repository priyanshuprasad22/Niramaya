package com.example.niramaya_health;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.niramaya_health.adapters.QuestionAdapter;
import com.example.niramaya_health.models.Question_model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class AddQuestion extends AppCompatActivity {

    ArrayList<Question_model>question_modelslist;
    RecyclerView recyclerView;

    TextView editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_question);

        editText=findViewById(R.id.text_about_question);

        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(AddQuestion.this,question_view.class);
                startActivity(intent);
            }
        });


        String json = null;
        question_modelslist=new ArrayList<>();

        try {
            InputStream is = getAssets().open("SymptomsOutput.json"); // replace "your_file_name" with the actual name of your JSON file
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        JSONArray jsonArray = null; // yourJsonString is the JSON string containing the array
        try {
            jsonArray = new JSONArray(json);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        String type="";
        String text="";
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = null;
            try {
                jsonObject = jsonArray.getJSONObject(i);
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
            try {
                text = jsonObject.getString("text");
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
            try {
                type = jsonObject.getString("type");
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }

            if(type.equals("integer"))
            {
                Question_model question_model=new Question_model(text,type);
                question_modelslist.add(question_model);
            }
            else if(type.equals("categorical"))
            {
                try {
                    JSONArray choicesArray = jsonObject.getJSONArray("choices");

                    ArrayList<String> choicesList = new ArrayList<>();
                    for (int j = 0; j < choicesArray.length(); j++) {
                        JSONObject choiceObject = choicesArray.getJSONObject(j);
                        String choiceText = choiceObject.getString("text");
                        choicesList.add(choiceText);

                    }

                    Question_model question_model=new Question_model(text,type,choicesList);
                    question_modelslist.add(question_model);

                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }

            // do something with the text and type values
        }
        recyclerView=findViewById(R.id.recycle_question_list_add);
        QuestionAdapter questionAdapter=new QuestionAdapter(AddQuestion.this,question_modelslist);
        recyclerView.setAdapter(questionAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(AddQuestion.this));
    }
}