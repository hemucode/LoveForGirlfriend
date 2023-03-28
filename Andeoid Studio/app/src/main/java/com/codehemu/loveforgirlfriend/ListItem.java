package com.codehemu.loveforgirlfriend;

public class ListItem {
    private String desc;
    int isfav;
    int isturned;

    public ListItem(String desc, int isfav, int isturned) {
        this.desc = desc;
        this.isfav = isfav;
        this.isturned = isturned;
    }

    public int getIsturned() {
        return this.isturned;
    }

    public ListItem(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return this.desc;
    }
}
