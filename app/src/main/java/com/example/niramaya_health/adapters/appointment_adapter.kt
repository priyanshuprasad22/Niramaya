package com.example.niramaya_health.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.niramaya_health.R
import com.example.niramaya_health.Upcoming_appoint_checkup
import com.example.niramaya_health.models.medical_appoint_data

class appointment_adapter(val context: Context, val appointlist:ArrayList<medical_appoint_data> )
    : RecyclerView.Adapter<appointment_adapter.appointViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): appointViewHolder {
       val view = LayoutInflater.from(context).inflate(R.layout.appoint_card,parent,false)
        return appointViewHolder(view);
    }

    override fun getItemCount(): Int {
        return appointlist.size
    }

    override fun onBindViewHolder(holder: appointViewHolder, position: Int) {

        val currentitem=appointlist[position]

        holder.patientname.text=currentitem.patient_name
        holder.patientcontact.text="Contact : "+currentitem.patient_contact
        holder.patientemail.text="Email : "+currentitem.patient_email
        holder.appointment_detail.text="Appointment Date and time : "+currentitem.upcoming_appointment+" and "+currentitem.time

        holder.itemView.setOnClickListener {
            val intent = Intent(context,Upcoming_appoint_checkup::class.java)
            intent.putExtra("patient_id",currentitem.patient_id)
            context.startActivity(intent)

        }



    }





        class appointViewHolder(itemView: View):RecyclerView.ViewHolder(itemView)
        {
            var patientname=itemView.findViewById<TextView>(R.id.patient_name_text_view)
            var patientcontact=itemView.findViewById<TextView>(R.id.patient_contact_text_view)
            var patientemail=itemView.findViewById<TextView>(R.id.patient_email_text_view)
            var appointment_detail=itemView.findViewById<TextView>(R.id.appointment_text_view)

        }



}