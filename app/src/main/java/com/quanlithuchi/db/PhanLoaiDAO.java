package com.quanlithuchi.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.quanlithuchi.models.GiaoDich;
import com.quanlithuchi.models.PhanLoai;

import java.util.ArrayList;

public class PhanLoaiDAO {
    private Context mContext;
    private SQLiteDatabase myDatabase;
    public static final String THU = "Thu";
    public static final String CHI = "Chi";

    public PhanLoaiDAO(Context mContext) {
        this.mContext = mContext;
        MyDatabase dbHelper = new MyDatabase(mContext);
        myDatabase = dbHelper.getWritableDatabase();
    }

    public ArrayList<PhanLoai> layTatCa(String trangThaiz) {
        ArrayList<PhanLoai> list = new ArrayList<>();

        Cursor cursor = myDatabase.rawQuery("SELECT * FROM PhanLoai WHERE TrangThai = ?",new String[]{ trangThaiz });
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                int maLoai = cursor.getInt(0);
                String tenLoai = cursor.getString(1);
                String trangThai = cursor.getString(2);
                list.add(new PhanLoai(maLoai, tenLoai, trangThai));
            } while (cursor.moveToNext());
        }
        return list;
    }

    public void them(PhanLoai phanLoai) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("TenLoai", phanLoai.getTenLoai());
        contentValues.put("TrangThai", phanLoai.getTrangThai());
        long result = myDatabase.insert("PhanLoai", null, contentValues);
    }

    public void sua(PhanLoai phanLoai) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("TenLoai", phanLoai.getTenLoai());
        contentValues.put("TrangThai", phanLoai.getTrangThai());
        long result = myDatabase.update("PhanLoai", contentValues, "MaLoai=?", new String[]{phanLoai.getMaLoai() + ""});
    }

    public void xoa(PhanLoai phanLoai) {
        long result = myDatabase.delete("PhanLoai", "MaLoai=?", new String[]{ phanLoai.getMaLoai() + "" });
        long result2 = myDatabase.delete("GiaoDich", "MaLoai=?", new String[]{ phanLoai.getMaLoai() + "" });
    }
}
