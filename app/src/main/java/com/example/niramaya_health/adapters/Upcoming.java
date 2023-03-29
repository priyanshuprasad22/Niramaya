package com.example.niramaya_health.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.niramaya_health.R;
import com.example.niramaya_health.models.UserUpcomingAppoint;

import java.util.ArrayList;

public class Upcoming extends RecyclerView.Adapter<Upcoming.ViewHolder> {

    private ArrayList<UserUpcomingAppoint> appoint;
    private Context context;

    public Upcoming(ArrayList<UserUpcomingAppoint> appoint,Context context)
    {
        this.appoint=appoint;
        this.context=context;

    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.patient_seesion,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        UserUpcomingAppoint userUpcomingAppoint=appoint.get(position);


        holder.doctorname.setText(userUpcomingAppoint.getDoctorname());
        holder.doctordate.setText(userUpcomingAppoint.getDateofappointment());
        holder.doctortime.setText(userUpcomingAppoint.getTimeofappointment());
        holder.doctorlocation.setText(userUpcomingAppoint.getLocation());
        holder.doctorspecialization.setText(userUpcomingAppoint.getSpecialization());



    }

    @Override
    public int getItemCount() {
        return appoint.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView doctorname,doctorlocation,doctordate,doctortime,doctorspecialization;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            doctorname=itemView.findViewById(R.id.tv_doctor_name);
            doctorlocation=itemView.findViewById(R.id.tv_doctor_location);
            doctordate=itemView.findViewById(R.id.tv_doctor_dateofvisit);
            doctortime=itemView.findViewById(R.id.tv_doctor_timeofvisit);
            doctorspecialization=itemView.findViewById(R.id.tv_doctor_specialization);

        }
    }




}
