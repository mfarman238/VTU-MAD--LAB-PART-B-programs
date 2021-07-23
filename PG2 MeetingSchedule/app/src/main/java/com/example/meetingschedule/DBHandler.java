package com.example.meetingschedule;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHandler extends SQLiteOpenHelper {
    public DBHandler(Context context) {
        super(context, "meeting", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table schedule(time TEXT, date TEXT, agenda TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists schedule");

    }

    public boolean insert(String mt, String md, String ma){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("time",mt);
        cv.put("date",md);
        cv.put("agenda",ma);

        long result = db.insert("schedule", null, cv);

        if (result == 1)
            return true;
        else
            return false;


    }


}
