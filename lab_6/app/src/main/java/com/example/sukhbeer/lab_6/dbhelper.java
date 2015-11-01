package com.example.sukhbeer.lab_6;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by sukhbeer on 14/10/15.
 */
public class dbhelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "friends.db";
    public static final String TABLE_NAME = "friend_list";
    private static final int DATABASE_VERSION =2;
    private static String DB_PATH;
    private SQLiteDatabase database = null;
    public dbhelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        this.DB_PATH = "/data/data/" + context.getPackageName() + "/databases/";
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Drop table if exists" + TABLE_NAME);
        db.execSQL("create table" + TABLE_NAME + "( id integer , name char , number integer);");
        Log.d("table", "created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
