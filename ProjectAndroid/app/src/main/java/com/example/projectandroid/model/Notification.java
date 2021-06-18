package com.example.projectandroid.model;

import java.io.Serializable;

public class Notification implements Serializable {
    String name,detail;

    @Override
    public String toString() {
        return "Notification{" +
                "name='" + name + '\'' +
                ", detail='" + detail + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Notification(String name, String detail) {
        this.name = name;
        this.detail = detail;
    }
}
