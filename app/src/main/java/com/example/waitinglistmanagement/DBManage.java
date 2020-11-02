package com.example.waitinglistmanagement;

import android.content.ClipData;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.provider.SyncStateContract;

import java.lang.reflect.Array;
import java.net.IDN;
import java.util.ArrayList;

public class DBManage extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "student.db";
    public static final String TABLE_NAME = "student_table";
    public static final String col1 = "ID";
    public static final String col2 = "Name";
    public static final String col3 = "Course";
    public static final String col4 = "Status";
    public static final String col5 = "Priority";

    public DBManage(Context context){
        super(context, DATABASE_NAME, null, 1);
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table " + TABLE_NAME +" (ID INTEGER PRIMARY KEY AUTOINCREMENT, Name TEXT, Course TEXT, Status TEXT, Priority TEXT)" );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //sqLiteDatabase.execSQL();
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public boolean addData(String ID, String Name, String Course, String Status, String Priority){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(col2, Name);
        cv.put(col3, Course);
        cv.put(col4, Status);
        cv.put(col5, Priority);
        long result = sqLiteDatabase.insert(TABLE_NAME, null, cv);

        if (result == -1)
            return false;
        else
            return true;
    }

    // Returns all the data from database
    public Cursor showAllData() {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        return cursor;
    }

    public boolean updateData(String ID, String Name, String Course, String Status, String Priority){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(col1, ID);
        contentValues.put(col2, Name);
        contentValues.put(col3, Course);
        contentValues.put(col4, Status);
        contentValues.put(col5, Priority);
        sqLiteDatabase.update(TABLE_NAME, contentValues, "ID = ?", new String[] {ID});
        return true;
    }

    public Integer deleteRecord(String id) {
       SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
       return sqLiteDatabase.delete(TABLE_NAME, "ID = ?", new String[] {id});
    }
}
