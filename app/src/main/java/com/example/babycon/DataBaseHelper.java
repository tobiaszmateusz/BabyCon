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
    private static final String TABLE_NAME3 = "SZCZEPIENIA";
    private static final String TABLE_NAME4 = "BABY_POMIARY";
    private static final String COL_1 = "ID";
    private static final String COL_2 = "USERNAME";
    private static final String COL_3 = "EMAIL";
    private static final String COL_4 = "PASSWORD";
    private static final String COL_5 = "ID";
    private static final String COL_6 = "BABYNAME";
    private static final String COL_7 = "BIRTHDAY";

    private static final String COL_10 = "BABY_ID";
    private static final String COL_11 = "DATA";
    private static final String COL_12 = "OBWOD_GL";
    private static final String COL_13 = "OBWOD_KLATKI";
    private static final String COL_14 = "NOTATKA";
<<<<<<< HEAD
    private static final String COL_15 = "WAGA";
<<<<<<< HEAD
    private static final String COL_16 = "WZROST";
    private static final String COL_17 = "ID_SZCZEPIENIA";
=======

>>>>>>> parent of b1455a9 (Commit 11)
=======
>>>>>>> parent of f6e8aea (Commit 12)


    public DataBaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS "+TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT , USERNAME TEXT , EMAIL TEXT , PASSWORD TEXT )");
        db.execSQL("CREATE TABLE IF NOT EXISTS "+TABLE_NAME2 + "(BABY_ID INTEGER PRIMARY KEY AUTOINCREMENT , ID INTEGER , BABYNAME TEXT , BIRTHDAY DATE , FOREIGN KEY(ID) REFERENCES USER_DATA(ID) )");
        db.execSQL("CREATE TABLE IF NOT EXISTS "+TABLE_NAME3 + "(ID_SZCZEPIENIA INTEGER PRIMARY KEY, NAZWA TEXT, DATA TEXT, NASZA_DATA TEXT, POTWIERDZENIE INTEGER, OPIS TEXT)");
<<<<<<< HEAD
<<<<<<< HEAD
        db.execSQL("CREATE TABLE IF NOT EXISTS "+TABLE_NAME4 + "(ID_DATA INTEGER PRIMARY KEY AUTOINCREMENT , BABY_ID INTEGER, DATA TEXT, OBWOD_GL INTEGER, OBWOD_KLATKI INTEGER, WAGA INTEGER, WZROST INTEGER, NOTATKA TEXT, FOREIGN KEY(BABY_ID) REFERENCES BABY_DATA(BABY_ID) )");
=======
        db.execSQL("CREATE TABLE IF NOT EXISTS "+TABLE_NAME4 + "(ID_DATA INTEGER PRIMARY KEY AUTOINCREMENT , BABY_ID INTEGER, DATA TEXT, OBWOD_GL INTEGER, OBWOD_KLATKI INTEGER, WAGA INTEGER, NOTATKA TEXT, FOREIGN KEY(BABY_ID) REFERENCES BABY_DATA(BABY_ID) )");
>>>>>>> parent of f6e8aea (Commit 12)
        db.execSQL("CREATE TABLE IF NOT EXISTS "+TABLE_NAME5 + "(ID_CHSZ INTEGER PRIMARY KEY AUTOINCREMENT, BABY_ID INTEGER, ID_SZCZEPIENIA INTEGER ,FOREIGN KEY(BABY_ID) REFERENCES BABY_DATA(BABY_ID), FOREIGN KEY(ID_SZCZEPIENIA) REFERENCES SZCZEPIENIA(ID_SZCZEPIENIA))");
=======
        db.execSQL("CREATE TABLE IF NOT EXISTS "+TABLE_NAME4 + "(ID_DATA INTEGER PRIMARY KEY AUTOINCREMENT , BABY_ID INTEGER, DATA TEXT, OBWOD_GL INTEGER, OBWOD_KLATKI INTEGER, NOTATKA TEXT, FOREIGN KEY(BABY_ID) REFERENCES BABY_DATA(BABY_ID) )");
>>>>>>> parent of b1455a9 (Commit 11)
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME2);
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME3);
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME4);

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

    public Cursor checkBaby(String ID){
        SQLiteDatabase db = this.getReadableDatabase();
        String Query = "Select * from " + TABLE_NAME2 + " where " + COL_5 + " = " + ID;
        Cursor cursor = db.rawQuery(Query, null);
        return cursor;
    }


    public Cursor getUser(String username , String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues values = new ContentValues();
        String Query = "Select ID from " + TABLE_NAME + " where " + COL_2 + " ='" + username + "' AND "+ COL_4 + " = '" + password + "'";
        Cursor cursor = db.rawQuery(Query, null);
        return cursor;
    }

    public boolean addBaby(String ID, String name, String data){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_5 , ID);
        values.put(COL_6 , name);
        values.put(COL_7 , data);

        long result = db.insert(TABLE_NAME2 , null , values);
        if(result == -1)
            return false;
        else
            return true;
    }

<<<<<<< HEAD
<<<<<<< HEAD
    public boolean insertData(String ID, String DATA, String obgl, String obkl, String notatka, String waga, String wzrost){
=======
    public boolean insertdata(String ID, String DATA, String obgl, String obkl, String notatka){
>>>>>>> parent of b1455a9 (Commit 11)
=======
    public boolean insertdata(String ID, String DATA, String obgl, String obkl, String notatka, String waga){
>>>>>>> parent of f6e8aea (Commit 12)
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COL_10,ID);
        values.put(COL_11,DATA);
        values.put(COL_12,obgl);
        values.put(COL_13,obkl);
        values.put(COL_14,notatka);
<<<<<<< HEAD
        values.put(COL_15,waga);
<<<<<<< HEAD
        values.put(COL_16,wzrost);
=======
>>>>>>> parent of b1455a9 (Commit 11)
=======
>>>>>>> parent of f6e8aea (Commit 12)

        long result = db.insert(TABLE_NAME4 , null , values);
        if(result == -1)
            return false;
        else
            return true;
    }

    public Cursor getWpisy(String ID){
        SQLiteDatabase db = this.getReadableDatabase();
        String Query = "Select * from " + TABLE_NAME4 + " where " + COL_10 + " = " + ID;
        Cursor cursor = db.rawQuery(Query, null);
        return cursor;
    }

/*    public Cursor insertSzczepionki(){
        SQLiteDatabase db = this.getWritableDatabase();

        *//*String Query ="INSERT INTO " +TABLE_NAME3 + "("+COL_11+",  "+COL_12+", "+COL_13+", "+COL_14+", "+COL_15+", "+COL_16+") VALUES (1, 'BCG', '24H', 'a', 1, 'choroba zakaźna');";*//*

        String Query ="";

        Cursor cursor = db.rawQuery(Query, null);
        return cursor;
    }*/
}
