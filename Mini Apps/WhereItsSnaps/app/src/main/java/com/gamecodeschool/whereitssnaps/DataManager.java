package com.gamecodeschool.whereitssnaps;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by BHAVIK PATEL on 19-May-17.
 */

public class DataManager {
    public static final String TABLE_ROW_TITLE = "image_title";
    public static final String TABLE_ROW_ID = "_id";
    public static final String TABLE_ROW_URI = "image_uri";

    private static final String DB_NAME = "wis_db";
    private static final int DB_VERSION = 1;
    private static final String TABLE_PHOTOS = "wis_table_photos";
    private static final String TABLE_TAGS = "wis_table_tags";
    private static final String TABLE_ROW_TAG1 = "tag1";
    private static final String TABLE_ROW_TAG2 = "tag2";
    private static final String TABLE_ROW_TAG3 = "tag3";
    public static final String TABLE_ROW_TAG = "tag";
    private SQLiteDatabase db;

    public DataManager(Context context){
        CustomSQLiteOpenHelper helper = new CustomSQLiteOpenHelper(context);
        db = helper.getWritableDatabase();

    }
    public void addPhoto (Photo photo){
        String query = "INSERT INTO " + TABLE_PHOTOS +
                " ( " + TABLE_ROW_TITLE +
                " , " + TABLE_ROW_URI +
                ", " + TABLE_ROW_TAG1 +
                ", " + TABLE_ROW_TAG2 +
                ", " + TABLE_ROW_TAG3 +
                ") " +
                "VALUES (" +
                "'" + photo.getTitle() + "'" + ", " +
                "'" + photo.getStorageLocation() + "'" + ", " +
                "'" + photo.getTag1() + "'" + ", " +
                "'" + photo.getTag2() + "'" + ", " +
                "'" + photo.getTag3() + "'" + ");";
        Log.i("addPhoto()",query);
        db.execSQL(query);

        query = "INSERT INTO " + TABLE_TAGS + "(" +
                TABLE_ROW_TAG + ") " +
                "SELECT '" + photo.getTag1() + "' " +
                "WHERE NOT EXISTS ( SELECT 1 FROM " +
                TABLE_TAGS +
                " WHERE " + TABLE_ROW_TAG + " = " +
                "'" + photo.getTag1() + "');";
        db.execSQL(query);
        query = "INSERT INTO " + TABLE_TAGS + "(" +
                TABLE_ROW_TAG + ") " +
                "SELECT '" + photo.getTag2() + "' " +
                "WHERE NOT EXISTS ( SELECT 1 FROM " +
                TABLE_TAGS +
                " WHERE " + TABLE_ROW_TAG + " = " +
                "'" + photo.getTag2() + "');";
        db.execSQL(query);
        query = "INSERT INTO " + TABLE_TAGS + "(" +
                TABLE_ROW_TAG + ") " +
                "SELECT '" + photo.getTag3() + "' " +
                "WHERE NOT EXISTS ( SELECT 1 FROM " +
                TABLE_TAGS +
                " WHERE " + TABLE_ROW_TAG + " = " +
                "'" + photo.getTag3() + "');";
        db.execSQL(query);
    }
    public Cursor getTitles() {
        Cursor c = db.rawQuery("SELECT " + TABLE_ROW_ID + ", " +
                TABLE_ROW_TITLE + " FROM " + TABLE_PHOTOS, null);
                c.moveToFirst();
        return c;
    }
    public Cursor getTitlesWithTag(String tag) {
        Cursor c = db.rawQuery("SELECT " + TABLE_ROW_ID + ", " +
                        TABLE_ROW_TITLE + " from " +
                        TABLE_PHOTOS + " WHERE " +
                        TABLE_ROW_TAG1 + " = '" + tag + "' or " +
                        TABLE_ROW_TAG2 + " = '" + tag + "' or " +
                        TABLE_ROW_TAG3 + " = '" + tag + "';"
                , null);
        c.moveToFirst();
        return c;
    }
    public Cursor getPhoto(int id) {
        Cursor c = db.rawQuery("SELECT * from " +
                TABLE_PHOTOS +
                " WHERE " +
                TABLE_ROW_ID + " = " + id, null);
        c.moveToFirst();
        return c;
    }
    public Cursor getTags(){
        Cursor c = db.rawQuery("SELECT " + TABLE_ROW_ID + ", " +
                TABLE_ROW_TAG + " from " + TABLE_TAGS, null);
        c.moveToFirst();
        return c;
    }

    private class CustomSQLiteOpenHelper extends SQLiteOpenHelper{
        public CustomSQLiteOpenHelper(Context context){
            super(context,DB_NAME,null,DB_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            String newTableQueryString = "create table "
                    + TABLE_PHOTOS + " ("
                    + TABLE_ROW_ID
                    + " integer primary key autoincrement not null,"
                    + TABLE_ROW_TITLE
                    + " text not null,"
                    + TABLE_ROW_URI
                    + " text not null,"
                    + TABLE_ROW_TAG1
                    + " text not null,"
                    + TABLE_ROW_TAG2
                    + " text not null,"
                    + TABLE_ROW_TAG3
                    + " text not null" + ");";
            db.execSQL(newTableQueryString);
            // Create a separate table for tags
            newTableQueryString = "create table "
                    + TABLE_TAGS + " ("
                    + TABLE_ROW_ID
                    + " integer primary key autoincrement not null,"
                    + TABLE_ROW_TAG
                    + " text not null" + ");";
            db.execSQL(newTableQueryString);
        }
        // This method only runs when we increment DB_VERSION
        @Override
        public void onUpgrade(SQLiteDatabase db,
                              int oldVersion, int newVersion) {
        }
    }

}
