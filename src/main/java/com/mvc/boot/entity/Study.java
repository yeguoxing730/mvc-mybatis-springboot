package com.mvc.boot.entity;

import java.io.Serializable;

/**
 * Created by yeguo on 2018/3/11.
 */
public class Study implements Serializable{
    private int id;

    private String studyname;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStudyname() {
        return studyname;
    }

    public void setStudyname(String studyname) {
        this.studyname = studyname;
    }
}
