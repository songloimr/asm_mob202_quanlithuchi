package com.quanlithuchi.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class ThongKeDAO {

    SQLiteDatabase myDatabase;

    public ThongKeDAO (Context context) {
        MyDatabase helper = new MyDatabase(context);
        myDatabase = helper.getWritableDatabase();
    }

    public float[] layTongThuChi() {
        int tongThu = 0, tongChi = 0;
        Cursor cursorThu = myDatabase.rawQuery("SELECT SUM(Tien) FROM GiaoDich WHERE MaLoai IN (SELECT MaLoai FROM PhanLoai WHERE TrangThai = 'Thu') ", null);
        if (cursorThu.getCount() != 0) {
            cursorThu.moveToFirst();
            tongThu = cursorThu.getInt(0);
        }

        Cursor cursorChi = myDatabase.rawQuery("SELECT SUM(Tien) FROM GiaoDich WHERE MaLoai IN (SELECT MaLoai FROM PhanLoai WHERE TrangThai = 'Chi')", null);
        if (cursorChi.getCount() != 0) {
            cursorChi.moveToFirst();
            tongChi = cursorChi.getInt(0);
        }
        return new float[]{ tongThu, tongChi };
    }
}
