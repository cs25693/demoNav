package com.inube.demonav;

public class NavDrawerModel {
    private int icon;
    private String title;

    public NavDrawerModel(String title,int icon){
        this.title=title;
        this.icon=icon;
    }
    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
