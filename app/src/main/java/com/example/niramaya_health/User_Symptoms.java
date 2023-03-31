package com.example.niramaya_health;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.niramaya_health.adapters.QuestionAdapter;
import com.example.niramaya_health.models.Question_model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link User_Symptoms#newInstance} factory method to
 * create an instance of this fragment.
 */
public class User_Symptoms extends Fragment implements TextWatcher {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    ArrayList<Question_model> question_modelslist;
    ArrayList<Question_model> filtered_list;

    RecyclerView recyclerView;

    EditText edttext;
    public User_Symptoms() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment User_Symptoms.
     */
    // TODO: Rename and change types and number of parameters
    public static User_Symptoms newInstance(String param1, String param2) {
        User_Symptoms fragment = new User_Symptoms();
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
        View rootview=inflater.inflate(R.layout.fragment_user__symptoms, container, false);

        String json = null;
        question_modelslist=new ArrayList<>();
        filtered_list=new ArrayList<>();

        edttext=rootview.findViewById(R.id.user_search);

        edttext.addTextChangedListener(this);

        try {
            InputStream is = getActivity().getAssets().open("SymptomsOutput.json"); // replace "your_file_name" with the actual name of your JSON file
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
        recyclerView=rootview.findViewById(R.id.recycle_question_list);
        QuestionAdapter questionAdapter=new QuestionAdapter(User_Symptoms.this.getContext(),question_modelslist);
        recyclerView.setAdapter(questionAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(User_Symptoms.this.getContext()));






        return rootview;
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        ArrayList<Question_model> question_filterlist=new ArrayList<>();

        for(Question_model question_model:question_modelslist)
        {
            if(question_model.getQuestion().toLowerCase().contains(charSequence.toString().toLowerCase()))
            {
                question_filterlist.add(question_model);
            }
        }

        QuestionAdapter questionAdapter=new QuestionAdapter(User_Symptoms.this.getContext(),question_filterlist);
        recyclerView.setAdapter(questionAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(User_Symptoms.this.getContext()));

    }

    @Override
    public void afterTextChanged(Editable editable) {

    }
}