package com.example.niramaya_health.adapters;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;



import com.bumptech.glide.Glide;
import com.example.niramaya_health.R;
import com.example.niramaya_health.TimelineItem;

public class PostTextViewHolder extends BaseViewHolder{

    private final TextView txtPost;
    private final TextView txtTime;
    private final ImageView imgUser;

    private TextView diagonisis;
    private TextView date_of_visit;
    private TextView followup;
    private TextView medicines;

    public PostTextViewHolder( View itemView) {

        super(itemView);
        txtPost=itemView.findViewById(R.id.post_text_content);
        txtTime= itemView.findViewById(R.id.post_text_time);
        imgUser = itemView.findViewById(R.id.post_text_img);
        diagonisis= itemView.findViewById(R.id.diagonisis_text);
        date_of_visit= itemView.findViewById(R.id.date_visit);
        followup=itemView.findViewById(R.id.followup_text);
        medicines=itemView.findViewById(R.id.medi_text);

    }

    @Override
    void setData(TimelineItem item) {



        PostTextItem post =item.getPostTextItem();
        if(post!=null){
            txtPost.setText(post.getPostText());
            txtTime.setText(post.getTime());
            Glide.with(itemView.getContext()).load(post.getImgUser()).into(imgUser);
            diagonisis.setText(post.getDiagonisis());
            date_of_visit.setText(post.getDate_of_visit());
            followup.setText(post.getFollowup());
            medicines.setText(post.getMedicines());
        }

    }
}
