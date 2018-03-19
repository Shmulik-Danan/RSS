package com.example.elixi.rssnews.model;


import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Shmulik on 18 מרץ 2018.
 */
@Root(name = "channel", strict = false)
public class Channel implements Serializable {
    public Channel() {
    }
    @Element(required = false,name = "title")
    String title;

    @Element(required = false,name = "pubDate")
    String pubDate;

    @ElementList(inline = true, name = "item")
    ArrayList<Item> item;


    public Channel(String title, String pubDate, ArrayList<Item> item) {
        this.title = title;
        this.pubDate = pubDate;
        this.item = item;
    }

    public ArrayList<Item> getItem() {
        return item;
    }

    public void setItem(ArrayList<Item> item) {
        this.item = item;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    @Override
    public String toString() {
        return "Channel{" +
                "title='" + title + '\'' +
                ", pubDate='" + pubDate + '\'' +
                ", item=" + item +
                '}';
    }
}
