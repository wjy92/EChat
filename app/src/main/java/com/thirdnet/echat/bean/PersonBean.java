package com.thirdnet.echat.bean;

/**
 * 作者：杨水强
 * 时间：2016/2/29
 */
public class PersonBean {
    private long id;
    private int portrait;


    public PersonBean() {
    }

    public PersonBean(long id, int portrait) {
        this.id = id;
        this.portrait = portrait;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getPortrait() {
        return portrait;
    }

    public void setPortrait(int portrait) {
        this.portrait = portrait;
    }
}
