package com.example.niramaya_health;

import com.example.niramaya_health.adapters.Constant;
import com.example.niramaya_health.adapters.HeaderTextItem;
import com.example.niramaya_health.adapters.PostTextItem;

import java.io.Serializable;

public class TimelineItem implements Serializable {
    private HeaderTextItem headerTextItem;
    private PostTextItem postTextItem;
    private int viewType;

    public TimelineItem(PostTextItem postTextItem){
        this.postTextItem = postTextItem;
        viewType = Constant.Item_Postr_Text_ViewType;
    }

    public HeaderTextItem getHeaderTextItem() {
        return headerTextItem;
    }

    public PostTextItem getPostTextItem() {
        return postTextItem;
    }

    public int getViewType() {
        return viewType;
    }

    public void setViewType(int viewType) {
        this.viewType = viewType;
    }

}
