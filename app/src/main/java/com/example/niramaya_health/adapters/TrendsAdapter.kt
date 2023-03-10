package com.example.niramaya_health.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.niramaya_health.DetailActivity
import com.example.niramaya_health.Home_Page
import com.example.niramaya_health.R
import com.example.niramaya_health.models.Trends
import org.w3c.dom.Text
import java.util.ArrayList

class TrendsAdapter(val context:Context, val trendList: ArrayList<Trends>):
    RecyclerView.Adapter<TrendsAdapter.TrendsViewHolder> (){



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrendsViewHolder {
        val view: View= LayoutInflater.from(context).inflate(R.layout.trends_card,parent,false)
        return TrendsViewHolder(view)
    }

    override fun onBindViewHolder(holder: TrendsViewHolder, position: Int) {
        val currentitem=trendList[position]
        holder.imageurl= currentitem.image_url.toString().trim()
        holder.url=currentitem.url.toString().trim()
        holder.textcontent.text=currentitem.title
        holder.textdiscription.text=currentitem.discription

        Glide.with(holder.itemView.context)
            .load(currentitem.image_url)
            .into(holder.image)

        holder.itemView.setOnClickListener {
            var intent= Intent(context,DetailActivity::class.java)
            intent.putExtra("Title",currentitem.title)
            intent.putExtra("Discription",currentitem.discription)
            intent.putExtra("url",currentitem.url)
            intent.putExtra("Content",currentitem.content)
            intent.putExtra("image_url",currentitem.image_url)
            context.startActivity(intent)


        }
    }

    override fun getItemCount(): Int {
        return trendList.size
    }


    class TrendsViewHolder (itemView: View):RecyclerView.ViewHolder(itemView){

        val image=itemView.findViewById<ImageView>(R.id.image_view)
        var imageurl:String=""
        var url:String=""
        var textcontent=itemView.findViewById<TextView>(R.id.title_view)
        var textdiscription=itemView.findViewById<TextView>(R.id.content_view)


    }


}
