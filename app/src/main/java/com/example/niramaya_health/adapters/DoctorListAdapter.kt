package com.example.niramaya_health.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.niramaya_health.Doctor_full_view
import com.example.niramaya_health.R
import com.example.niramaya_health.models.Doctor_full_info

class DoctorListAdapter (var context:Context,var doctorlist:ArrayList<Doctor_full_info>,val formatteddate:String) :RecyclerView.Adapter<DoctorListAdapter.doctorViewHolder>(){






    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): doctorViewHolder {
       val view = LayoutInflater.from(context).inflate(R.layout.doctor_list_cardview,parent,false);
        return doctorViewHolder(view);


    }

    override fun getItemCount(): Int {
        return doctorlist.size;
    }

    override fun onBindViewHolder(holder: doctorViewHolder, position: Int) {
        val doctor= doctorlist[position];

        holder.docname.text=doctor.name;
        holder.experience.text=doctor.experience+ " years of experience";
        holder.qualificaiton.text=doctor.qualification;
        holder.location.text=doctor.location;
        holder.specialization.text=doctor.specialization;

        Glide.with(holder.itemView.context)
            .load(doctor.downloadUrl)
            .into(holder.image);

        holder.itemView.setOnClickListener {
            val intent= Intent(context,Doctor_full_view::class.java)
            intent.putExtra("doctorinfo",doctor);
            intent.putExtra("formatteddate",formatteddate);
            context.startActivity(intent)

        }



    }

    class doctorViewHolder (itemview: View):RecyclerView.ViewHolder(itemview){

        val docname:TextView=itemview.findViewById(R.id.doctor_name);
        val qualificaiton:TextView=itemview.findViewById(R.id.doctor_qualification);
        val experience:TextView=itemview.findViewById(R.id.doctor_experience);
        val specialization:TextView=itemview.findViewById(R.id.doctor_specialization);
        val image:ImageView=itemview.findViewById(R.id.doctor_photo);
        val location:TextView=itemview.findViewById(R.id.doctor_location);


    }

}