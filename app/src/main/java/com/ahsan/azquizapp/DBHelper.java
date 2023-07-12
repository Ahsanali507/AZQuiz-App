package com.ahsan.azquizapp;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "mydatabase.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "results";
    private static final String COLUMN_GAIN_SCORE = "gain_score";
    private static final String COLUMN_TOTAL_SCORE = "total_score";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableQuery = "CREATE TABLE " + TABLE_NAME + "(" +
                COLUMN_GAIN_SCORE + " INTEGER, " +
                COLUMN_TOTAL_SCORE + " INTEGER" +
                ")";
        db.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop the table if it exists
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        // Recreate the table
        onCreate(db);
    }
    public void addScores(int gainScore, int totalScore) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_GAIN_SCORE, gainScore);
        values.put(COLUMN_TOTAL_SCORE, totalScore);

        db.insert(TABLE_NAME, null, values);
        db.close();
    }
    public Score getRecentScores() {
        Score score = null;
        String selectQuery = "SELECT * FROM " + TABLE_NAME + " ORDER BY rowid DESC LIMIT 1";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            @SuppressLint("Range") int gainScore = cursor.getInt(cursor.getColumnIndex(COLUMN_GAIN_SCORE));
            @SuppressLint("Range") int totalScore = cursor.getInt(cursor.getColumnIndex(COLUMN_TOTAL_SCORE));

            score = new Score(gainScore, totalScore);
        }

        cursor.close();
        db.close();

        return score;
    }
}
