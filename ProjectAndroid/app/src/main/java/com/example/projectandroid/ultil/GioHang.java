package com.example.projectandroid.ultil;

public class GioHang {

    private int idsp;
    private String tensp;
    private String giasp;
    private String hinh;
    private int soluongsp;

    public GioHang() {
    }

    public GioHang(int idsp, String tensp, String giasp, String hinh, int soluongsp) {
        this.idsp = idsp;
        this.tensp = tensp;
        this.giasp = giasp;
        this.hinh = hinh;
        this.soluongsp = soluongsp;
    }

    public int getIdsp() {
        return idsp;
    }

    public void setIdsp(int idsp) {
        this.idsp = idsp;
    }

    public String getTensp() {
        return tensp;
    }

    public void setTensp(String tensp) {
        this.tensp = tensp;
    }

    public String getGiasp() {
        return giasp;
    }

    public void setGiasp(String giasp) {
        this.giasp = giasp;
    }

    public String getHinh() {
        return hinh;
    }

    public void setHinh(String hinh) {
        this.hinh = hinh;
    }

    public int getSoluongsp() {
        return soluongsp;
    }

    public void setSoluongsp(int soluongsp) {
        this.soluongsp = soluongsp;
    }

    @Override
    public String toString() {
        return "GioHang{" +
                "idsp=" + idsp +
                ", tensp='" + tensp + '\'' +
                ", giasp='" + giasp + '\'' +
                ", hinh='" + hinh + '\'' +
                ", soluongsp=" + soluongsp +
                '}';
    }
}
