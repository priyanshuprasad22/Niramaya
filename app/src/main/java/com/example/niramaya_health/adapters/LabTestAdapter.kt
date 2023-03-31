package com.example.niramaya_health.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.niramaya_health.R
import com.example.niramaya_health.models.Test
import com.example.niramaya_health.webview_detail

class labTestAdapter(val labitems:ArrayList<Test>,val context:Context): RecyclerView.Adapter<labTestAdapter.labTestViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): labTestViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.lab_test_card,parent,false)

        return  labTestViewHolder(view)
    }

    override fun getItemCount(): Int {
        return labitems.size
    }

    override fun onBindViewHolder(holder: labTestViewHolder, position: Int) {
        val currentLabItem = labitems[position]
        holder.titleView.text=currentLabItem.title
        holder.labdescView.text=currentLabItem.description

        Glide.with(holder.itemView.context).load(currentLabItem.imageurl).into(holder.companylogo)
        val url=currentLabItem.lab_company_url


        holder.itemView.setOnClickListener {
            val intent= Intent(context, webview_detail::class.java)
            intent.putExtra("url",url)
            context.startActivity(intent)

        }
    }
    class labTestViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val titleView: TextView = itemView.findViewById(R.id.Labs)
        val labdescView:TextView=itemView.findViewById(R.id.lab_desc)
        val companylogo:ImageView=itemView.findViewById(R.id.labCompany_logo)
    }
}