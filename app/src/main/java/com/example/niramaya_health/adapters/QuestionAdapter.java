package com.example.niramaya_health.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.niramaya_health.DisplayQuestion;
import com.example.niramaya_health.R;
import com.example.niramaya_health.models.Question_model;
import com.example.niramaya_health.question_view;

import java.util.ArrayList;
import java.util.List;

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.ViewHolder> {


    ArrayList<Question_model> question_list;
    Context context;
    public QuestionAdapter(Context context, ArrayList<Question_model> questionlist)
    {
        this.context=context;
        this.question_list=questionlist;
    }


    @NonNull
    @Override
    public QuestionAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(context).inflate(R.layout.question_cardview,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QuestionAdapter.ViewHolder holder,  int position) {


        holder.question.setText(question_list.get(position).getQuestion());
        String type=question_list.get(position).getType();
        int w=1;
        if(type.equals("integer"))
        {
            w=1;
        }
        else if(type.equals("categorical"))
        {
            w=0;
        }
        int j=w;
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, DisplayQuestion.class);
                if(j==1) {
                    intent.putExtra("question",question_list.get(position).getQuestion());
                    intent.putExtra("type", type);
                    intent.putExtra("List", "Empty");

                }
                else
                {
                    ArrayList<String> question=question_list.get(position).getChoices();
                    intent.putExtra("question",question_list.get(position).getQuestion());
                    intent.putExtra("type",type);
                    intent.putStringArrayListExtra("List",question);

                }
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return question_list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView question;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            question=itemView.findViewById(R.id.text_question);

        }
    }



}

