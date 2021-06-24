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
    private static final String TABLE_NAME5 = "BABY_SZCZEPIENIA";
    private static final String TABLE_NAME6 = "MILOWE";
    private static final String COL_1 = "ID";
    private static final String COL_2 = "USERNAME";
    private static final String COL_3 = "EMAIL";
    private static final String COL_4 = "PASSWORD";
    private static final String COL_5 = "ID";
    private static final String COL_6 = "BABYNAME";
    private static final String COL_7 = "BIRTHDAY";
    private static final String COL_8 = "PLEC";

    private static final String COL_10 = "BABY_ID";
    private static final String COL_11 = "DATA";
    private static final String COL_12 = "OBWOD_GL";
    private static final String COL_13 = "OBWOD_KLATKI";
    private static final String COL_14 = "NOTATKA";
    private static final String COL_15 = "WAGA";
    private static final String COL_16 = "WZROST";
    private static final String COL_17 = "ID_SZCZEPIENIA";
    private static final String COL_18 = "TERMIN";
    private static final String COL_19 = "POTWIERDZENIE";
    private static final String COL_20 = "NAZWA";

    public DataBaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS "+TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT , USERNAME TEXT , EMAIL TEXT , PASSWORD TEXT )");
        db.execSQL("CREATE TABLE IF NOT EXISTS "+TABLE_NAME2 + "(BABY_ID INTEGER PRIMARY KEY AUTOINCREMENT , ID INTEGER , BABYNAME TEXT , BIRTHDAY DATE, PLEC TEXT ,FOREIGN KEY(ID) REFERENCES USER_DATA(ID) )");
        db.execSQL("CREATE TABLE IF NOT EXISTS "+TABLE_NAME3 + "(ID_SZCZEPIENIA INTEGER PRIMARY KEY, NAZWA TEXT, DATA TEXT, NASZA_DATA TEXT, TERMIN INTEGER, OPIS TEXT)");
        db.execSQL("CREATE TABLE IF NOT EXISTS "+TABLE_NAME4 + "(ID_DATA INTEGER PRIMARY KEY AUTOINCREMENT , BABY_ID INTEGER, DATA TEXT, OBWOD_GL INTEGER, OBWOD_KLATKI INTEGER, WAGA INTEGER, WZROST INTEGER, NOTATKA TEXT, FOREIGN KEY(BABY_ID) REFERENCES BABY_DATA(BABY_ID) )");
        db.execSQL("CREATE TABLE IF NOT EXISTS "+TABLE_NAME5 + "(ID_CHSZ INTEGER PRIMARY KEY AUTOINCREMENT, BABY_ID INTEGER, ID_SZCZEPIENIA INTEGER, POTWIERDZENIE INTEGER, DATA TEXT, FOREIGN KEY(BABY_ID) REFERENCES BABY_DATA(BABY_ID), FOREIGN KEY(ID_SZCZEPIENIA) REFERENCES SZCZEPIENIA(ID_SZCZEPIENIA))");
        db.execSQL("CREATE TABLE IF NOT EXISTS "+TABLE_NAME6 + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, BABY_ID INTEGER, NAZWA TEXT, DATA TEXT, FOREIGN KEY(BABY_ID) REFERENCES BABY_DATA(BABY_ID))");

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME2);
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME3);
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME4);
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME5);
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME6);
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

    public Cursor checkPlec(String ID){
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

    public boolean addBaby(String ID, String name, String data, String plec){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_5 , ID);
        values.put(COL_6 , name);
        values.put(COL_7 , data);
        values.put(COL_8 , plec);

        long result = db.insert(TABLE_NAME2 , null , values);
        if(result == -1)
            return false;
        else
            return true;
    }

    public boolean insertData(String ID, String DATA, String obgl, String obkl, String notatka, String waga, String wzrost){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COL_10,ID);
        values.put(COL_11,DATA);
        values.put(COL_12,obgl);
        values.put(COL_13,obkl);
        values.put(COL_14,notatka);
        values.put(COL_15,waga);
        values.put(COL_16,wzrost);

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

    public Cursor getSzczepionki(){
        SQLiteDatabase db = this.getReadableDatabase();
        String Query = "Select * from " + TABLE_NAME3 + " order by " + COL_18 + " ASC";
        String Query2 = "SELECT * FROM" +  TABLE_NAME3 +" b" + " LEFT OUTER JOIN" + TABLE_NAME3 +" s"+ " on B.ID_SZCZEPIENIA = S.ID_SZCZEPIENIA WHERE BABY_ID = 2";
        Cursor cursor = db.rawQuery(Query, null);
        return cursor;
    }

    public Cursor getSzczepionkiCH(String ID){
        SQLiteDatabase db = this.getReadableDatabase();
        String Query2 = "SELECT NAZWA, s.DATA, TERMIN, POTWIERDZENIE FROM BABY_SZCZEPIENIA b LEFT OUTER JOIN SZCZEPIENIA s on B.ID_SZCZEPIENIA = S.ID_SZCZEPIENIA WHERE BABY_ID = "+ ID + " AND B.DATA = "+ "\"null\"" +" ORDER BY TERMIN ASC";
        Cursor cursor = db.rawQuery(Query2, null);
        return cursor;
    }

/*    public Cursor insertSzczepionki(){
        SQLiteDatabase db = this.getWritableDatabase();

        *//*String Query ="INSERT INTO " +TABLE_NAME3 + "("+COL_11+",  "+COL_12+", "+COL_13+", "+COL_14+", "+COL_15+", "+COL_16+") VALUES (1, 'BCG', '24H', 'a', 1, 'choroba zakaźna');";*//*

        String Query ="";

        Cursor cursor = db.rawQuery(Query, null);
        return cursor;
    }*/

    public boolean stworzSzczepionki(String id){
        SQLiteDatabase db = this.getReadableDatabase();
        String Query = "Select Count(ID_SZCZEPIENIA) from " + TABLE_NAME3;
        Cursor cursor = db.rawQuery(Query, null);

        ContentValues values = new ContentValues();


        for(int i = 1; i < 31; i++) {

            values.put(COL_10,id);
            values.put(COL_17,i);
            values.put(COL_11,"null");
            db.insert(TABLE_NAME5 , null , values);

        }

        long result = db.insert(TABLE_NAME5 , null , values);
        if(result == -1)
            return false;
        else
            return true;

    }
    public boolean accSzczepienia(String idch, String data, String idszcz){
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_19,data);
        long result = db.update(TABLE_NAME5 , values , "BABY_ID = ? AND ID_SZCZEPIENIA = ?", new String[]{idch, idszcz});

        if(result == -1)
            return false;
        else
            return true;
    }

    public Cursor getNazwa(String nazwasz){
        SQLiteDatabase db = this.getReadableDatabase();
        String Query2 = "SELECT ID_SZCZEPIENIA FROM SZCZEPIENIA  WHERE NAZWA = \""+nazwasz+ "\"";
        Cursor cursor = db.rawQuery(Query2, null);
        return cursor;
    }

    public boolean addMilowy(String idch, String data, String nazwa){
        SQLiteDatabase db = this.getReadableDatabase();

        ContentValues values = new ContentValues();
        values.put(COL_10 , idch);
        values.put(COL_11 , data);
        values.put(COL_20 , nazwa);

        long result = db.insert(TABLE_NAME6 , null , values);
        if(result == -1)
            return false;
        else
            return true;
    }

    public Cursor getMilowe(String idch){
        SQLiteDatabase db = this.getReadableDatabase();
        String Query2 = "SELECT * FROM MILOWE  WHERE BABY_ID = "+ idch;
        Cursor cursor = db.rawQuery(Query2, null);
        return cursor;
    }

    public Cursor getSzczepionkiWyk(String idch){
        SQLiteDatabase db = this.getReadableDatabase();
        String Query = "SELECT POTWIERDZENIE, NAZWA FROM BABY_SZCZEPIENIA b LEFT OUTER JOIN SZCZEPIENIA s on B.ID_SZCZEPIENIA = S.ID_SZCZEPIENIA WHERE BABY_ID = "+ idch + " AND POTWIERDZENIE != \"NULL\"";
        Cursor cursor = db.rawQuery(Query, null);
        return cursor;
    }

}
