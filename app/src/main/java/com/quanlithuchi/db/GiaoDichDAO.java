package com.quanlithuchi.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.quanlithuchi.models.GiaoDich;

import java.util.ArrayList;

public class GiaoDichDAO {
    private Context mContext;
    private SQLiteDatabase myDatabase;

    public static final String THU = "Thu";
    public static final String CHI = "Chi";

    public GiaoDichDAO(Context mContext) {
        this.mContext = mContext;
        MyDatabase dbHelper = new MyDatabase(mContext);
        myDatabase = dbHelper.getWritableDatabase();
    }

    public ArrayList<GiaoDich> layTatCa(String trangThai) {
        ArrayList<GiaoDich> list = new ArrayList<>();

        Cursor cursor = myDatabase.rawQuery("SELECT * FROM GiaoDich INNER JOIN PhanLoai ON GiaoDich.MaLoai = PhanLoai.MaLoai WHERE TrangThai = ?",new String[]{ trangThai });
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                int maGD = cursor.getInt(0);
                String tenGD = cursor.getString(1);
                String ngay = cursor.getString(2);
                float tien = cursor.getFloat(3);
                String moTa = cursor.getString(4);
                int maLoai = cursor.getInt(5);
                String tenLoai = cursor.getString(7);
                GiaoDich gd = new GiaoDich(maGD, tenGD, ngay, tien, moTa, maLoai);
                gd.setTenLoai(tenLoai);
                list.add(gd);
            } while (cursor.moveToNext());
        }
        return list;
    }


    public void them(GiaoDich giaoDich) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("TenGD", giaoDich.getTenGD());
        contentValues.put("Ngay", giaoDich.getNgay());
        contentValues.put("Tien", giaoDich.getTien());
        contentValues.put("MoTa", giaoDich.getMoTa());
        contentValues.put("MaLoai", giaoDich.getMaLoai());
        long result = myDatabase.insert("GiaoDich", null, contentValues);
    }

    public void sua(GiaoDich giaoDich) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("TenGD", giaoDich.getTenGD());
        contentValues.put("Ngay", giaoDich.getNgay());
        contentValues.put("Tien", giaoDich.getTien());
        contentValues.put("MoTa", giaoDich.getMoTa());
        contentValues.put("MaLoai", giaoDich.getMaLoai());
        long result = myDatabase.update("GiaoDich", contentValues, "MaGD=?", new String[]{giaoDich.getMaGD() + ""});
    }

    public void xoa(GiaoDich giaoDich) {
        long result = myDatabase.delete("GiaoDich", "MaGD=?", new String[]{ giaoDich.getMaGD() + "" });
    }

}
