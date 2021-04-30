package com.example.babycon;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

public class DataBaseHelper extends SQLiteOpenHelper{


    private static final String DATABASE_NAME = "BabyCon";
    private static final String TABLE_NAME = "USER_DATA";
    private static final String TABLE_NAME2 = "BABY_DATA";
    private static final String COL_1 = "ID";
    private static final String COL_2 = "USERNAME";
    private static final String COL_3 = "EMAIL";
    private static final String COL_4 = "PASSWORD";



    public DataBaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS "+TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT , USERNAME TEXT , EMAIL TEXT , PASSWORD TEXT )");
        db.execSQL("CREATE TABLE IF NOT EXISTS "+TABLE_NAME2 + "(BABY_ID INTEGER PRIMARY KEY AUTOINCREMENT , ID INTEGER , BABYNAME TEXT , BIRTHDAY DATE , FOREIGN KEY(ID) REFERENCES USER_DATA(ID) )");

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean registerUser(String username , String email , String password){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_2 , username);
        values.put(COL_3 , email);
        values.put(COL_4 , password);

        long result = db.insert(TABLE_NAME , null , values);
        if(result == -1)
            return false;
        else
            return true;
    }

    public boolean checkUser(String username , String password){

        SQLiteDatabase db = this.getWritableDatabase();
        String [] columns = { COL_1 };
        String selection = COL_2 + "=?" + " and " + COL_4 + "=?";
        String [] selectionargs = { username , password};
        Cursor cursor = db.query(TABLE_NAME , columns , selection ,selectionargs , null , null , null);
        int count = cursor.getCount();
        db.close();
        cursor.close();
        if (count > 0)
            return true;
        else
            return false;
    }

    public boolean checkBaby(int ID){

        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues values = new ContentValues();

        String Query = "Select * from " + TABLE_NAME2 + " where " + ID + " = " + "Select ID from "+ TABLE_NAME;
        Cursor cursor = db.rawQuery(Query, null);

        long result = db.insert(TABLE_NAME , null , values);
        if(result == -1)
            return false;
        else
            return true;
    }


    public int getUser(String username , String password) {
        int _id = 0;
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues values = new ContentValues();
        String Query = "Select ID from " + TABLE_NAME + " where " + COL_2 + " ='" + username + "' AND "+ COL_4 + " = '" + password + "'";
        String Query2 = "Select ID from " + TABLE_NAME + " where " + COL_2 + " = " + username + " AND "+ COL_4 + " = " + password;
        Cursor cursor = db.rawQuery(Query, null);
        _id = cursor.getCount();
        return _id;
    }
}
