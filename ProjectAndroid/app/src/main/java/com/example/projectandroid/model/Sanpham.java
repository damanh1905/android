package com.example.projectandroid.model;

import java.io.Serializable;

public class Sanpham implements Serializable {
    private int idsanpham;
    private String tensanpham;
    private String gia;
    private String hinhanhsanpham;
    private String motasanpham;
    private int idloaisp;

    public Sanpham(int idsanpham, String tensanpham, String gia, String hinhanhsanpham, String motasanpham, int idloaisp) {
        this.idsanpham = idsanpham;
        this.tensanpham = tensanpham;
        this.gia = gia;
        this.hinhanhsanpham = hinhanhsanpham;
        this.motasanpham = motasanpham;
        this.idloaisp = idloaisp;
    }

    public int getIdsanpham() {
        return idsanpham;
    }

    public void setIdsanpham(int idsanpham) {
        this.idsanpham = idsanpham;
    }

    public String getTensanpham() {
        return tensanpham;
    }

    public void setTensanpham(String tensanpham) {
        this.tensanpham = tensanpham;
    }

    public String getGia() {
        return gia;
    }

    public void setGia(String gia) {
        this.gia = gia;
    }

    public String getHinhanhsanpham() {
        return hinhanhsanpham;
    }

    public void setHinhanhsanpham(String hinhanhsanpham) {
        this.hinhanhsanpham = hinhanhsanpham;
    }

    public String getMotasanpham() {
        return motasanpham;
    }

    public void setMotasanpham(String motasanpham) {
        this.motasanpham = motasanpham;
    }

    public int getIdloaisp() {
        return idloaisp;
    }

    public void setIdloaisp(int idloaisp) {
        this.idloaisp = idloaisp;
    }

    @Override
    public String
    toString() {
        return "Sanpham{" +
                "idsanpham=" + idsanpham +
                ", tensanpham='" + tensanpham + '\'' +
                ", gia='" + gia + '\'' +
                ", hinhanhsanpham='" + hinhanhsanpham + '\'' +
                ", motasanpham='" + motasanpham + '\'' +
                ", idloaisp=" + idloaisp +
                '}';
    }
}
