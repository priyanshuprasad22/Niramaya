package com.example.niramaya_health.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.niramaya_health.Patient_List
import com.example.niramaya_health.Patient_full_medical_detail
import com.example.niramaya_health.R
import com.example.niramaya_health.models.medical_appoint_data
import com.example.niramaya_health.models.patient_medical_data

class patient_data(var context: Context,val patientList: ArrayList<patient_medical_data>)
    :RecyclerView.Adapter<patient_data.patientViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): patientViewHolder {
        val view:View =LayoutInflater.from(context).inflate(R.layout.patient_card,parent,false)
        return patientViewHolder(view)
    }

    override fun getItemCount(): Int {
        return patientList.size

    }

    override fun onBindViewHolder(holder: patientViewHolder, position: Int) {

        var currentitempatient=patientList[position]

        holder.patientname.text=currentitempatient.patient_name
        holder.diagnosis.text=currentitempatient.diagonosis
        holder.lastappointment.text="Last Appointment: -"+currentitempatient.dateofvist
        holder.followup.text="Upcoming Appointment: -"+currentitempatient.followup

        holder.itemView.setOnClickListener {
            val intent=Intent(context,Patient_full_medical_detail::class.java)
            intent.putExtra("patient_id",currentitempatient.patient_id)
            context.startActivity(intent)
        }

    }

    class patientViewHolder(itemView: View):RecyclerView.ViewHolder(itemView)
    {
        var patientname=itemView.findViewById<TextView>(R.id.patientNameTextView)
        var lastappointment=itemView.findViewById<TextView>(R.id.lastAppointmentTextView)
        var followup=itemView.findViewById<TextView>(R.id.followupAppointmentTextView)
        var diagnosis=itemView.findViewById<TextView>(R.id.diagnosisTextView)
    }


}
