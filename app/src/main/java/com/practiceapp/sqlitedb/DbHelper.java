package com.practiceapp.sqlitedb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    public DbHelper(Context context) {
        super(context,"Studentdata.db",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table Studentdeatils(name TEXT primary key, contact Text,dob TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int oldVersion, int newVersion) {
        DB.execSQL("drop Table if exists StudentDeatils");

    }
    public boolean insertstudentdata(String name,String contact,String dob){
        SQLiteDatabase DB=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("name",name);
        contentValues.put("contact",contact);
        contentValues.put("dob",dob);
        long result=DB.insert("Studentdetails",null,contentValues);
        if(result==-1) {
            return false;
        }else{
            return true;
        }

    }

    public boolean updatestudentdata(String name,String contact,String dob){
        SQLiteDatabase DB=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("contact",contact);
        contentValues.put("dob",dob);
        Cursor cursor=DB.rawQuery("Select*from Studentdeatils where name=?",new String[]{name});
        if (cursor.getCount()>0){
        long result=DB.update("Studentdetails", contentValues, "name=?",new String[]{name});
        if(result==-1) {
            return false;
        }else{
            return true;
        }

    }else {
            return false;
        }
    }

    public boolean Deletestudentdata(String name){
        SQLiteDatabase DB=this.getWritableDatabase();

        Cursor cursor=DB.rawQuery("Select*from Studentdeatils where name=?",new String[]{name});
        if (cursor.getCount()>0){
            long result=DB.delete("Studentdetails", "name=?",new String[]{name});
            if(result==-1) {
                return false;
            }else{
                return true;
            }

        }else {
            return false;
        }
    }

    public Cursor getstudentdata(){
        SQLiteDatabase DB=this.getWritableDatabase();

        Cursor cursor=DB.rawQuery("Select*from Studentdeatils ",null);
        return cursor;
    }

}

