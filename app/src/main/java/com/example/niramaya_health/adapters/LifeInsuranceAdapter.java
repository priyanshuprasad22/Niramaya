package com.example.niramaya_health.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.niramaya_health.R;
import com.example.niramaya_health.models.InsuraneModel;

import java.util.ArrayList;

public class LifeInsuranceAdapter  extends RecyclerView.Adapter<LifeInsuranceAdapter.ViewHolder> {

    private ArrayList<InsuraneModel> lifeinsurance;
    private Context context;

    public LifeInsuranceAdapter(ArrayList<InsuraneModel> lifeinsurance,Context context)
    {
        this.lifeinsurance=lifeinsurance;
        this.context=context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.card_lifeinsurance,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        InsuraneModel model= lifeinsurance.get(position);

        holder.companyName.setText(model.getCompanyname());
        holder.validity.setText(model.getValidity());
        holder.amount.setText(model.getAmountcovered());

        String url=model.getImageurl();

        Glide.with(this.context)
                .load(url)
                .into(holder.logo);






    }

    @Override
    public int getItemCount() {
        return lifeinsurance.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView logo;
        TextView companyName;
        TextView validity;
        TextView amount;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            logo=itemView.findViewById(R.id.companyLogoImageView);
            companyName=itemView.findViewById(R.id.companyNameTextView);
            validity=itemView.findViewById(R.id.validityTextView);
            amount=itemView.findViewById(R.id.amountTextView);


        }


    }

}
