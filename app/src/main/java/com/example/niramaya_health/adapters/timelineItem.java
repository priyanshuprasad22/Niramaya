package com.example.niramaya_health.adapters;

public class timelineItem {

    private HeaderTextItem headerTextItem;
    private PostTextItem postTextItem;
    private int viewType;

    public timelineItem(HeaderTextItem headerTextItem){
        this.headerTextItem = headerTextItem;
        viewType = Constant.Item_Header_Text_ViewType;

    }

    public timelineItem(PostTextItem postTextItem) {
        this.postTextItem = postTextItem;
        viewType= Constant.Item_Postr_Text_ViewType;

    }

    public HeaderTextItem getHeaderTextItem() {
        return headerTextItem;
    }

    public void setHeaderTextItem(HeaderTextItem headerTextItem) {
        this.headerTextItem = headerTextItem;
    }

    public PostTextItem getPostTextItem() {
        return postTextItem;
    }

    public void setPostTextItem(PostTextItem postTextItem) {
        this.postTextItem = postTextItem;
    }

    public int getViewType() {
        return viewType;
    }

    public void setViewType(int viewType) {
        this.viewType = viewType;
    }

    @Override
    public String toString() {
        return "timelineItem{" +
                "headerTextItem=" + headerTextItem +
                ", postTextItem=" + postTextItem +
                ", viewType=" + viewType +
                '}';
    }
}
