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
import com.example.niramaya_health.models.Medicine
import com.example.niramaya_health.webview_detail

class medAdapter(val meditems:ArrayList<Medicine>,val context: Context): RecyclerView.Adapter<medAdapter.medViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): medViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.med_card, parent, false)

        return medViewHolder(view)
    }

    override fun getItemCount(): Int {
        return meditems.size
    }

    override fun onBindViewHolder(holder: medViewHolder, position: Int) {

        val currentMedItem = meditems[position]
        holder.titleView.text = currentMedItem.title
        holder.medDescView.text = currentMedItem.description

        Glide.with(holder.itemView.context).load(currentMedItem.imageurl).into(holder.companylogo)
        val url = currentMedItem.weburl


        holder.itemView.setOnClickListener {
            val intent = Intent(context, webview_detail::class.java)
            intent.putExtra("url", url)
            context.startActivity(intent)
        }
    }
    class medViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val titleView: TextView = itemView.findViewById(R.id.medTitle)
        val medDescView: TextView = itemView.findViewById(R.id.med_desc)
        val companylogo: ImageView = itemView.findViewById(R.id.med_logo)

    }
}