package com.example.nazar.myfirstapplication.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by nazar on 22.04.2017.
 */

public class DbHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "persons.db";
    public static final String TABLE_NAME = "persons";
    public static final String COL_ID = "id";
    public static final String COL_PERSON_NAME = "personname";
    public static final String COL_PERSON_AGE = "personage";
    public static final String COL_PERSON_COLOREYE = "personcoloreye";
    public static final String COL_PERSON_TEMPERAMENT = "persontemperament";

    public DbHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
         String query = "CREATE TABLE IF NOT EXISTS " +  TABLE_NAME + "(" +
                 COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                 COL_PERSON_NAME + " TEXT, " +
                 COL_PERSON_AGE + " INTEGER, " +
                 COL_PERSON_COLOREYE + " TEXT, " +
                 COL_PERSON_TEMPERAMENT + " TEXT " +
                 ");";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }


    public String[] columnToString(String COL_NAME) {
        SQLiteDatabase db = getReadableDatabase();

        ArrayList<String> namesArray = new ArrayList<>();

        Cursor c = db.query(TABLE_NAME, new String[] {COL_NAME},null,null,null,null,null);

        while(c.moveToNext()) {
            if(c.getString(c.getColumnIndex(COL_NAME))!=null)
                namesArray.add(c.getString(c.getColumnIndex(COL_NAME)));
        }
        c.close();
        db.close();
        String[] names = new String[namesArray.size()];
        names = namesArray.toArray(names);
        return names;
    }

    public int findIdByName(String name) {
        SQLiteDatabase db = getReadableDatabase();

        Cursor c = db.query(TABLE_NAME, new String[] {COL_ID, COL_PERSON_NAME},COL_PERSON_NAME + "=?",new String[]{name},null,null,null);
        int foundId = 0;
        while(c.moveToNext()) {
                    foundId = c.getInt(c.getColumnIndex(COL_ID));
            }
        c.close();
        db.close();
        return foundId;
    }

    public String getNamebyId(int id) {
        SQLiteDatabase db = getReadableDatabase();

        String name = "";

        Cursor c = db.query(TABLE_NAME, new String[] {COL_ID, COL_PERSON_NAME},COL_ID + "=?",new String[]{String.valueOf(id)},null,null,null);

        while(c.moveToNext()) {
            name = c.getString(c.getColumnIndex(COL_PERSON_NAME));
        }
        c.close();
        db.close();
        return name;
    }

    public int getAgebyId(int id) {
        SQLiteDatabase db = getReadableDatabase();
        int n=0;

        Cursor c = db.query(TABLE_NAME, new String[] {COL_ID,COL_PERSON_AGE}, COL_ID + "=?", new String[] {String.valueOf(id)},null,null,null);

        if(c.moveToFirst()) {
            n = c.getInt(c.getColumnIndex(COL_PERSON_AGE));
        }
        c.close();
        db.close();

        return n;
    }

    public String getColorEyebyId(int id) {
        SQLiteDatabase db = getReadableDatabase();

        String colorEye = "";

        Cursor c = db.query(TABLE_NAME, new String[] {COL_ID,COL_PERSON_COLOREYE}, COL_ID + "=?", new String[] {String.valueOf(id)},null,null,null);

        if(c.moveToFirst()) {
            colorEye = c.getString(c.getColumnIndex(COL_PERSON_COLOREYE));
        }
        c.close();
        db.close();

        return  colorEye;
    }

    public String getTemperamentbyId(int id) {
        SQLiteDatabase db = getReadableDatabase();

        String temperament = "";

        Cursor c = db.query(TABLE_NAME, new String[] {COL_ID,COL_PERSON_TEMPERAMENT}, COL_ID + "=?", new String[] {String.valueOf(id)},null,null,null);

        if(c.moveToFirst()) {
            temperament = c.getString(c.getColumnIndex(COL_PERSON_TEMPERAMENT));
        }
        c.close();
        db.close();

        return  temperament;
    }

    public String databaseToString() {
        SQLiteDatabase db = getReadableDatabase();

        String dbString = "";

        String query = "SELECT * FROM " + TABLE_NAME;

        Cursor c = db.rawQuery(query,null);

        if(c.moveToFirst()) {
            while(!c.isAfterLast()) {
                dbString += c.getString(c.getColumnIndex(COL_PERSON_NAME)) + " ";
                dbString += c.getString(c.getColumnIndex(COL_PERSON_AGE)) + " ";
                dbString += c.getString(c.getColumnIndex(COL_PERSON_COLOREYE)) + " ";
                dbString += c.getString(c.getColumnIndex(COL_PERSON_TEMPERAMENT)) + " ";
                dbString += "\n";
                c.moveToNext();
            }
        }
        c.close();
        db.close();
        return dbString;
    }

    public void addPerson(String name, String typedAge, String coloreye, String temperament) {
        SQLiteDatabase db = getReadableDatabase();
        ContentValues person = new ContentValues();

        int age = Integer.parseInt(typedAge);

        person.put(COL_PERSON_NAME, name);
        person.put(COL_PERSON_AGE, age);
        person.put(COL_PERSON_COLOREYE, coloreye);
        person.put(COL_PERSON_TEMPERAMENT, temperament);

        db.insert(TABLE_NAME, null, person);
        db.close();
    }

    public void deletePerson(String name) {
        SQLiteDatabase db = getReadableDatabase();

        db.execSQL("DELETE FROM " + TABLE_NAME + " WHERE " + COL_PERSON_NAME + " = '" + name + "'");

        db.close();
    }

}








