package com.example.projectandroid.model;

public class Loaisp {
    int id;
    String loaisp;
    String hiinhanh;

    public Loaisp(int id, String loaisp, String hiinhanh) {
        this.id = id;
        this.loaisp = loaisp;
        this.hiinhanh = hiinhanh;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLoaisp() {
        return loaisp;
    }

    public void setLoaisp(String loaisp) {
        this.loaisp = loaisp;
    }

    public String getHiinhanh() {
        return hiinhanh;
    }

    public void setHiinhanh(String hiinhanh) {
        this.hiinhanh = hiinhanh;
    }
}
