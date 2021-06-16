package com.example.projectandroid.model;

import java.io.Serializable;

public class Account implements Serializable {
    int idAcc;
    String email,ten,matkhau,sodt,quyen;

    public Account() {
    }

    public int getIdAcc() {
        return idAcc;
    }

    public void setIdAcc(int idAcc) {
        this.idAcc = idAcc;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getMatkhau() {
        return matkhau;
    }

    public void setMatkhau(String matkhau) {
        this.matkhau = matkhau;
    }

    public String getSodt() {
        return sodt;
    }

    public void setSodt(String sodt) {
        this.sodt = sodt;
    }

    public String getQuyen() {
        return quyen;
    }

    public void setQuyen(String quyen) {
        this.quyen = quyen;
    }

    public Account(int idAcc, String email, String ten, String matkhau, String sodt, String quyen) {
        this.idAcc = idAcc;
        this.email = email;
        this.ten = ten;
        this.matkhau = matkhau;
        this.sodt = sodt;
        this.quyen = quyen;
    }

    @Override
    public String toString() {
        return "Account{" +
                "idAcc=" + idAcc +
                ", email='" + email + '\'' +
                ", ten='" + ten + '\'' +
                ", matkhau='" + matkhau + '\'' +
                ", sodt='" + sodt + '\'' +
                ", quyen='" + quyen + '\'' +
                '}';
    }
}
