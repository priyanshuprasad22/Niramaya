package com.example.niramaya_health.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.niramaya_health.Prescription;
import com.example.niramaya_health.R;
import com.example.niramaya_health.TimelineItem;
import com.example.niramaya_health.models.Symptom;
import com.example.niramaya_health.models.patient_medical_data;

import java.util.List;



public class TimelineAdapter extends RecyclerView.Adapter<BaseViewHolder> {


    private Context mContext;
    private final List<TimelineItem> mdata;
    private final List<patient_medical_data> symptomList;

    public TimelineAdapter(Context mContext, List<TimelineItem> mdata, List<patient_medical_data> symptomList) {
        this.mContext = mContext;
        this.mdata = mdata;
        this.symptomList=symptomList;
    }


    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view ;
        switch(viewType){

            case(Constant.Item_Header_Text_ViewType):
                view = LayoutInflater.from(mContext).inflate(R.layout.item_header,parent,false);
                return new HeaderTextViewHolder(view);
            case(Constant.Item_Postr_Text_ViewType):
                view=LayoutInflater.from(mContext).inflate(R.layout.item_post_text,parent,false);
                return new PostTextViewHolder(view);
            default: throw new IllegalArgumentException();

        }

    }

    @Override
    public void onBindViewHolder( BaseViewHolder holder, int position) {

        holder.setData(mdata.get(position));
        patient_medical_data symptom1=symptomList.get(position);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(mContext, Prescription.class);
                intent.putExtra("Symptom",symptom1);
                mContext.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemViewType(int position) {
        return mdata.get(position).getViewType();
    }

    @Override
    public int getItemCount() {
        return mdata.size();
    }
}
