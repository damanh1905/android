package com.example.projectandroid.model;

public class user {
    private int id;
    private String Email;
    private String ten;

    private String matkhau;
    private String quyen;

    public user(int id, String email, String ten,String matkhau, String quyen) {
        this.id = id;
        this.Email = email;
        this.ten=ten;
        this.matkhau = matkhau;
        this.quyen = quyen;
    }

    public user() {
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getMatkhau() {
        return matkhau;
    }

    public void setMatkhau(String matkhau) {
        this.matkhau = matkhau;
    }

    public String getQuyen() {
        return quyen;
    }

    public void setQuyen(String quyen) {
        this.quyen = quyen;
    }

    @Override
    public String toString() {
        return "user{" +
                "id=" + id +
                ", Email='" + Email + '\'' +
                ", matkhau='" + matkhau + '\'' +
                ", quyen='" + quyen + '\'' +
                '}';
    }
}
