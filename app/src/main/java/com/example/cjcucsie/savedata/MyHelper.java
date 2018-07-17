package com.example.cjcucsie.savedata;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyHelper extends SQLiteOpenHelper {

    private String createTab;

    public MyHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        createTab = "create table tablename (_id integer primary key AUTOINCREMENT, row1 text, row2 text)";
        db.execSQL(createTab);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        String dropTable = "drop table tablename";
        db.execSQL(dropTable);
    }
}