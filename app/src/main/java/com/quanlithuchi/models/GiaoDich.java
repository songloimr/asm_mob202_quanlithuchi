package com.quanlithuchi.models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GiaoDich {

    private int maGD;
    private String tenGD;
    private String ngay;
    private float tien;
    private String moTa;
    private int maLoai;

    public String getTenLoai() {
        return tenLoai;
    }

    public void setTenLoai(String tenLoai) {
        this.tenLoai = tenLoai;
    }

    private String tenLoai;

    public GiaoDich(String tenGD, String ngay, float tien, String moTa, int maLoai) {
        this.tenGD = tenGD;
        this.ngay = ngay;
        this.tien = tien;
        this.moTa = moTa;
        this.maLoai = maLoai;
    }

//    private SimpleDateFormat dateTimeFormat = new SimpleDateFormat("dd-MM-yyyy");

    public int getMaGD() {
        return maGD;
    }

    public void setMaGD(int maGD) {
        this.maGD = maGD;
    }

    public String getTenGD() {
        return tenGD;
    }

    public void setTenGD(String tenGD) {
        this.tenGD = tenGD;
    }

    public String getNgay() {
        return ngay;
    }

    public void setNgay(String ngay) {
        this.ngay = ngay;
    }

    public float getTien() {
        return tien;
    }

    public void setTien(float tien) {
        this.tien = tien;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public int getMaLoai() {
        return maLoai;
    }

    public void setMaLoai(int maLoai) {
        this.maLoai = maLoai;
    }

    @Override
    public String toString() {
        return "GiaoDich{" +
                "maGD=" + maGD +
                ", tenGD='" + tenGD + '\'' +
                ", ngay='" + ngay + '\'' +
                ", tien=" + tien +
                ", moTa='" + moTa + '\'' +
                ", maLoai=" + maLoai +
                '}';
    }

    public GiaoDich(int maGD, String tenGD, String ngay, float tien, String moTa, int maLoai) {
        this.maGD = maGD;
        this.tenGD = tenGD;
        this.ngay = ngay;
        this.tien = tien;
        this.moTa = moTa;
        this.maLoai = maLoai;
    }
}
