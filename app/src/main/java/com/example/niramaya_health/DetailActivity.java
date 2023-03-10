package com.example.niramaya_health;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class DetailActivity extends AppCompatActivity {

    TextView txt_title,txt_discription,txt_contentview;
    ImageView img_view;
    Button btn_detail;

    @SuppressLint("CheckResult")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        String title=getIntent().getExtras().getString("Title");
        String content=getIntent().getExtras().getString("Content");
        String url=getIntent().getExtras().getString("url");
        String image_url=getIntent().getExtras().getString("image_url");
        String discription=getIntent().getExtras().getString("Discription");

        txt_title=findViewById(R.id.titleTextView);
        txt_discription=findViewById(R.id.descriptionTextView);
        txt_contentview=findViewById(R.id.contentTextView);

        btn_detail=findViewById(R.id.detail_button);

        img_view=findViewById(R.id.detail_imageView);

        String plainText = Html.fromHtml(content).toString();

        txt_title.setText(title);
        txt_discription.setText(discription);


        Glide.with(DetailActivity.this).load(image_url).into(img_view);

        txt_contentview.setText(plainText);

        btn_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(DetailActivity.this,webview_detail.class);
                intent.putExtra("url",url);
                startActivity(intent);
            }
        });








    }
}