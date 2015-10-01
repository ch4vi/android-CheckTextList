package com.ch4vi.sample;

/**
 * Created by Chavi on 01/10/2015.
 */
public class Custom {

    private int id;
    private String title;
    private String date;


    public Custom(int id, String title, String date) {
        this.id = id;
        this.title = title;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDate() {
        return date;
    }
}
