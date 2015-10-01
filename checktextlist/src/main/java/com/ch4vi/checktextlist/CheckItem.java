package com.ch4vi.checktextlist;

import java.io.Serializable;

/**
 * Created by Chavi on 26/09/2015
 */
public class CheckItem implements Serializable {

    private String title;

    private Object extra;


    public CheckItem(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public Object getExtra() {
        return extra;
    }

    public void setExtra(Object extra) {
        this.extra = extra;
    }

    @Override
    public String toString() {
        return this.getTitle();
    }
}
