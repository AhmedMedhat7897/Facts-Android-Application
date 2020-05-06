package com.pearamone.didyouknow;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class DatabaseFacts extends SQLiteOpenHelper {

    public static final String TABLE_GENERAL_NAME = "general_fact_table";
    public static final String FACT_NAME = "fact_name";
    public static final String FACT_ID = "fact_id";
    public static final String FACT_FAV = "fact_fav";

    public static final String TABLE_ANIMAL_NAME = "animal_fact_table";
    public static final String TABLE_COMPUTER_NAME = "computer_fact_table";
    public static final String TABLE_HACKS_NAME = "hacks_fact_table";
    public static final String TABLE_COUNTRIES_NAME = "countries_fact_table";
    public static final String TABLE_FOOD_NAME = "food_fact_table";
    public static final String TABLE_HUMANBODY_NAME = "humanbody_fact_table";
    public static final String TABLE_PEOPLE_NAME = "people_fact_table";
    public static final String TABLE_PSYCHOLOGY_NAME = "psychology_fact_table";
    public static final String TABLE_SCIENCE_NAME = "science_fact_table";
    public static final String TABLE_UNIVERSE_NAME = "universe_fact_table";
    public static final String TABLE_WEATHER_NAME = "weather_fact_table";

    private static final String dbName = "facts_db_2";
    private static final int dbVersion = 3;
    private Context context;


    public DatabaseFacts(Context context) {
        super(context, dbName, null, dbVersion);
        this.context = context;

        this.copyDbIfNotExists();
    }

    public void copyDbIfNotExists() {
        // Ensure /data/data/YOUR_PACKAGE_NAME/databases/ directory is created.
        File dbDir = new File(context.getDatabasePath(dbName).getParentFile().getPath());
        if (!dbDir.exists())
            dbDir.mkdir();

        // Copy database starts here.
        String appDbPath = this.context.getDatabasePath(dbName).getAbsolutePath();
        File dbFile = new File(appDbPath);
        if (!dbFile.exists()) {
            try {
                InputStream mInput = context.getAssets().open("facts_db_2");
                OutputStream mOutput = new FileOutputStream(appDbPath);
                byte[] mBuffer = new byte[1024];
                int mLength;
                while ((mLength = mInput.read(mBuffer, 0, 1024)) > 0)
                    mOutput.write(mBuffer, 0, mLength);
                mOutput.flush();

                mOutput.close();
                mInput.close();
            } catch (IOException ex) {
                throw new Error("Error copying database: " + ex.getMessage());
            }
        }
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
//

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//
    }


    public Fact getFact(long factId, String tableName) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(tableName,
                new String[]{FACT_ID, FACT_NAME, FACT_FAV},
                FACT_ID + "=?",
                new String[]{String.valueOf(factId)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        Fact fact = new Fact(
                cursor.getInt(cursor.getColumnIndex(FACT_ID)),
                cursor.getString(cursor.getColumnIndex(FACT_NAME)),
                cursor.getInt(cursor.getColumnIndex(FACT_FAV)),
                tableName);
        cursor.close();
        db.close();
        return fact;
    }

    public ArrayList<Fact> getFavFacts(String tableName) {
        ArrayList<Fact> facts = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + tableName + " WHERE " + FACT_FAV + "= 1";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Fact fact = new Fact();
                fact.setFactId(cursor.getInt(cursor.getColumnIndex(FACT_ID)));
                fact.setFact(cursor.getString(cursor.getColumnIndex(FACT_NAME)));
                fact.setFavorite(Integer.parseInt(cursor.getString(cursor.getColumnIndex(FACT_FAV))));
                fact.setTableName(tableName);
                facts.add(fact);
            } while (cursor.moveToNext());
        }
        db.close();
        return facts;
    }

    public int getFactsCount(String tableName) {
        String countQuery = "SELECT  * FROM " + tableName;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();
        return count;
    }


    public int updateFact(int factId, boolean fav,String tableName) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(FACT_ID, factId);
        values.put(FACT_FAV, fav);
        // updating row
        return db.update(tableName, values, FACT_ID + " = ?",
                new String[]{String.valueOf(factId)});
    }

    public ArrayList<Fact> getSearchedFacts(String tableName, String searchString) {
        ArrayList<Fact> facts = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + tableName + " WHERE " + FACT_NAME + " LIKE " +"'%" + searchString + "%'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Fact fact = new Fact();
                fact.setFactId(cursor.getInt(cursor.getColumnIndex(FACT_ID)));
                fact.setFact(cursor.getString(cursor.getColumnIndex(FACT_NAME)));
                fact.setFavorite(Integer.parseInt(cursor.getString(cursor.getColumnIndex(FACT_FAV))));
                fact.setTableName(tableName);
                facts.add(fact);
            } while (cursor.moveToNext());
        }
        db.close();
        return facts;
    }
}