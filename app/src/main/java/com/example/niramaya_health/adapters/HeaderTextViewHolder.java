package com.example.niramaya_health.adapters;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.niramaya_health.R;
import com.example.niramaya_health.TimelineItem;
import com.example.niramaya_health.adapters.HeaderTextItem;

public class HeaderTextViewHolder extends BaseViewHolder{

    private final TextView tvHeader;

    @Override
    void setData(TimelineItem item) {
        HeaderTextItem headerTextItem = item.getHeaderTextItem();
        tvHeader.setText(headerTextItem.getHeaderText());

    }

    public HeaderTextViewHolder(@NonNull View itemView) {

        super(itemView);
        tvHeader = itemView.findViewById(R.id.header_text);
    }



}
