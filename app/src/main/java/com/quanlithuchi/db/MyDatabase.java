package com.quanlithuchi.db;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MyDatabase extends SQLiteOpenHelper {

    public MyDatabase(@Nullable Context context) {
        super(context, "ASM_MOB202", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqldb) {
        String[] phanloai = new String[] {
                "create table PhanLoai ( MaLoai integer primary key autoincrement, TenLoai text, TrangThai text) ",
                "INSERT INTO PhanLoai VALUES(null, 'Lương', 'Thu')",
                "INSERT INTO PhanLoai VALUES(null, 'Thu Nợ', 'Thu')",
                "INSERT INTO PhanLoai VALUES(null, 'Trúng Số', 'Thu')",
                "INSERT INTO PhanLoai VALUES(null, 'Thuê nhà', 'Chi')",
                "INSERT INTO PhanLoai VALUES(null, 'Sinh hoạt', 'Chi')",
                "INSERT INTO PhanLoai VALUES(null, 'Đỗ xăng', 'Chi')",
                "INSERT INTO PhanLoai VALUES(null, 'Khác', 'Thu')",
                "INSERT INTO PhanLoai VALUES(null, 'Khác', 'Chi')"
        };
        for (String query : phanloai) {
            sqldb.execSQL(query);
        }

        String[] giaodich = new String[] {
                "create table GiaoDich ( MaGD integer primary key autoincrement, TenGD text,Ngay text," +
                        "Tien float, MoTa text, MaLoai integer references PhanLoai(MaLoai)) ",
                "INSERT INTO GiaoDich VALUES(null, 'Lương tháng 9', '25/09/2002', 10000000, '', 1)",
                "INSERT INTO GiaoDich VALUES(null, 'Đỗ xăng', '29/09/2022', 100000, '', 6)",
                "INSERT INTO GiaoDich VALUES(null, 'Thuê nhà', '01/10/2022', 2500000, '', 4)",
                "INSERT INTO GiaoDich VALUES(null, 'Lương tháng 10', '25/10/2022', 11500000, '', 1)",
                "INSERT INTO GiaoDich VALUES(null, 'Thuê nhà', '01/11/2022', 2800000, '', 4)",
                "INSERT INTO GiaoDich VALUES(null, 'Thu nợ', '01/11/2022', 2000000, '', 2)",
                "INSERT INTO GiaoDich VALUES(null, 'Khác', '01/11/2022', 2000000, 'Dự đám cưới ', 8)"
        };
        for (String query : giaodich) {
            sqldb.execSQL(query);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS PhanLoai");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS GiaoDich");
        onCreate(sqLiteDatabase);
    }
}
