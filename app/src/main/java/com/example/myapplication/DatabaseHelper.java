package com.example.myapplication;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Application.db";
    public static final String TABLE_NAME = "Article_table";
    public static final String COL_1= "NOM_UNIQUE";
    public static final String COL_2 = "PRIX";
    public static final String COL_3 = "MAGASIN";
    public static final String COL_4 = "CATHEGORIE";
    public DatabaseHelper( Context context) {
        super(context, DATABASE_NAME,null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + "(NOM_UNIQUE TEXT PRIMARY KEY, PRIX INTEGER, MAGASIN TEXT, CATHEGORIE TEXT)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS " +TABLE_NAME);
        onCreate(db);
    }
    public boolean insertData(String prix, String magasin, String cathegorie){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, prix);
        contentValues.put(COL_3, magasin);
        contentValues.put(COL_4, cathegorie);
        long result = db.insert(TABLE_NAME, null, contentValues);
        if(result==-1){
            return false;
        }
        else{
            return true;
        }
    }
}

