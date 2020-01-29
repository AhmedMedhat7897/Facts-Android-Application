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

    private static final String dbName = "facts_db";
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
                InputStream mInput = context.getAssets().open("facts_db");
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
//        db.execSQL(Fact.CREATE_GENERAL_TABLE);
//        db.execSQL(Fact.CREATE_ANIMAL_TABLE);
//        db.execSQL(Fact.CREATE_COMPUTER_TABLE);
//        db.execSQL(Fact.CREATE_COUNTRIES_TABLE);
//        db.execSQL(Fact.CREATE_FOOD_TABLE);
//        db.execSQL(Fact.CREATE_HUMANBODY_TABLE);
//        db.execSQL(Fact.CREATE_PEOPLE_TABLE);
//        db.execSQL(Fact.CREATE_PSYCHOLOGY_TABLE);
//        db.execSQL(Fact.CREATE_SCIENCE_TABLE);
//        db.execSQL(Fact.CREATE_UNIVERSE_TABLE);
//        db.execSQL(Fact.CREATE_WEATHER_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        db.execSQL("DROP TABLE IF EXISTS " + Fact.TABLE_GENERAL_NAME);
//        db.execSQL("DROP TABLE IF EXISTS " + Fact.TABLE_ANIMAL_NAME);
//        db.execSQL("DROP TABLE IF EXISTS " + Fact.TABLE_COMPUTER_NAME);
//        db.execSQL("DROP TABLE IF EXISTS " + Fact.TABLE_COUNTRIES_NAME);
//        db.execSQL("DROP TABLE IF EXISTS " + Fact.TABLE_FOOD_NAME);
//        db.execSQL("DROP TABLE IF EXISTS " + Fact.TABLE_HUMANBODY_NAME);
//        db.execSQL("DROP TABLE IF EXISTS " + Fact.TABLE_PEOPLE_NAME);
//        db.execSQL("DROP TABLE IF EXISTS " + Fact.TABLE_PSYCHOLOGY_NAME);
//        db.execSQL("DROP TABLE IF EXISTS " + Fact.TABLE_SCIENCE_NAME);
//        db.execSQL("DROP TABLE IF EXISTS " + Fact.TABLE_UNIVERSE_NAME);
//        db.execSQL("DROP TABLE IF EXISTS " + Fact.TABLE_WEATHER_NAME);
//        onCreate(db);
    }

    public long insertFact(String fact, Boolean fav, String factFav, String factName, String tableName) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(factFav, fav);
        values.put(factName, fact);
        long id = db.insert(tableName, null, values);
        db.close();
        return id;
    }

    public Fact getFact(long id, String tableName, String factId, String factName, String factFav) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(tableName,
                new String[]{factId, factName, factFav},
                factId + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        Fact fact = new Fact(
                cursor.getInt(cursor.getColumnIndex(factId)),
                cursor.getString(cursor.getColumnIndex(factName)),
                cursor.getInt(cursor.getColumnIndex(factFav)));
        cursor.close();
        return fact;
    }

    public ArrayList<Fact> getFavFacts(String tableName, String factId, String factName, String factFav) {
        ArrayList<Fact> facts = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + tableName + " WHERE " + factFav + "= 1";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Fact fact = new Fact();
                fact.setFactId(cursor.getInt(cursor.getColumnIndex(factId)));
                fact.setFact(cursor.getString(cursor.getColumnIndex(factName)));
                fact.setFavorite(Integer.valueOf(cursor.getString(cursor.getColumnIndex(factFav))));
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


    public int updateFact(int factId, String factColumnId, Boolean fav, String factFav, String tableName, String factName, String name) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(factFav, fav);
        values.put(factName, name);
        // updating row
        return db.update(tableName, values, factColumnId + " = ?",
                new String[]{String.valueOf(factId)});
    }

//    public boolean isFavorite(int factId, String factColumnId, Boolean fav, String factFav, String tableName, String factName, String name){
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor cursor = db.query(tableName, )
//    }

}