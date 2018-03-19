package com.example.elixi.rssnews.model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import java.io.Serializable;

/**
 * Created by Shmulik on 18 מרץ 2018.
 */
@Root(name = "rss", strict = false)
public class Rss implements Serializable{

    @Element(name="channel")
     Channel channel;


    public Rss() {
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    @Override
    public String toString() {
        return "Rss{" +
                "title='" + channel + '\'' +
                '}';
    }
}

