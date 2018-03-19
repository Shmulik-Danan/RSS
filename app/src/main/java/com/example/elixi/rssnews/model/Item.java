package com.example.elixi.rssnews.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import java.io.Serializable;

/**
 * Created by Shmulik on 18 מרץ 2018.
 */
@Entity
@Root(name="item",strict = false)
public class Item implements Serializable {

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @PrimaryKey(autoGenerate = true)
    private int id;

    @Element(name="title")
    private String title;

    @ColumnInfo(name = "link")
    @Element(required = false,name="link")
    private String link;

    @ColumnInfo(name = "pubDate")
    @Element(required = false,name="pubDate")
    private String pubDate;

    @ColumnInfo(name = "description")
    @Element(required = false,name="description")
    private  String description;
    public Item() {
    }

    public Item(String title, String link, String pubDate,String description) {
        this.title = title;
        this.link = link;
        this.pubDate = pubDate;
        this.description=description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    @Override
    public String toString() {
        return "Item{" +
                "title='" + title + '\'' +
                ", link='" + link + '\'' +
                ", pubDate='" + pubDate + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}