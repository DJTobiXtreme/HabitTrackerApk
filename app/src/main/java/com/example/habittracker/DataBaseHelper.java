package com.example.habittracker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.strictmode.SqliteObjectLeakedViolation;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DataBaseHelper extends SQLiteOpenHelper {


    public static final String HABIT_TABLE = "HABIT_TABLE";
    public static final String COLUMN_HABIT_NAME = "HABIT_NAME";
    public static final String COLUMN_HABIT_RED = "HABIT_RED";
    public static final String COLUMN_HABIT_BLUE = "HABIT_BLUE";
    public static final String COLUMN_HABIT_GREEN = "HABIT_GREEN";
    public static final String COLUMN_ID = "ID";

    public DataBaseHelper(@Nullable Context context) {
        super(context, "habits.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement = "CREATE TABLE " + HABIT_TABLE + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_HABIT_NAME + " TEXT, " + COLUMN_HABIT_RED + " INT, " + COLUMN_HABIT_BLUE + " INT, " + COLUMN_HABIT_GREEN + " INT)";

        db.execSQL(createTableStatement);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public boolean updateCounter(int id, int option, int number){
        String queryString;
        SQLiteDatabase db;

        switch (option){
            case 0:
                queryString = "UPDATE " + HABIT_TABLE + " SET " + COLUMN_HABIT_RED + " = " + number + " WHERE " + COLUMN_ID + " = '" + id + "'";
                db = this.getWritableDatabase();
                db.execSQL(queryString);
                db.close();
                break;
            case 1:
                queryString = "UPDATE " + HABIT_TABLE + " SET " + COLUMN_HABIT_BLUE + " = " + number + " WHERE " + COLUMN_ID + " = '" + id + "'";
                db = this.getWritableDatabase();
                db.execSQL(queryString);
                db.close();
                break;
            case 2:
                queryString = "UPDATE " + HABIT_TABLE + " SET " + COLUMN_HABIT_GREEN + " = " + number + " WHERE " + COLUMN_ID + " = '" + id + "'";
                db = this.getWritableDatabase();
                db.execSQL(queryString);
                db.close();
                break;
        }
        return true;
    }

    public boolean addOne(Habit habit){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();


            cv.put(COLUMN_HABIT_NAME, habit.getName());
            cv.put(COLUMN_HABIT_RED, habit.getRed());
            cv.put(COLUMN_HABIT_BLUE, habit.getBlue());
            cv.put(COLUMN_HABIT_GREEN, habit.getGreen());
            long insert = db.insert(HABIT_TABLE, null, cv);
            db.close();
        return true;
    }

    public boolean deleteOne(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String queryString = "DELETE FROM " + HABIT_TABLE + " WHERE " + COLUMN_ID + " = " + id;
        db.execSQL(queryString);
        return true;
    }


    public List<Habit> getEveryone(){
        List<Habit> returnList = new ArrayList<>();

        String queryString = "SELECT * FROM " + HABIT_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString, null);

        if(cursor.moveToFirst()){
            do {
                int habitID = cursor.getInt(0);
                String habitName = cursor.getString(1);
                int habitRed = cursor.getInt(2);
                int habitBlue = cursor.getInt(3);
                int habitGreen = cursor.getInt(4);

                Habit habitToAdd = new Habit(habitID, habitName, habitRed, habitBlue, habitGreen);
                returnList.add(habitToAdd);

            } while (cursor.moveToNext());
        } else {
            // failure
        }

        cursor.close();
        db.close();
        return returnList;
    }
}
