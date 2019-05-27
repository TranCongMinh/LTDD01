package com.example.admin.skype;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class UserDatabase extends SQLiteOpenHelper {

    private static final String TAG = "SQLite";
    private static final int DATABASE_VERSION = 1;

    // Tên cơ sở dữ liệu.
    private static final String DATABASE_NAME = "Manager";
    private static final String TABLE_USER = "User";
    private static final String COLUMN_USERNAME ="username";
    private static final String COLUMN_PASSWORD ="password";

    public UserDatabase(Context context)  {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String script = "CREATE TABLE " + TABLE_USER + "("
                + COLUMN_USERNAME + " TEXT PRIMARY KEY," + COLUMN_PASSWORD + " TEXT" +  ")";
        db.execSQL(script);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        onCreate(db);
    }

    public void addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USERNAME, user.getUsername());
        values.put(COLUMN_PASSWORD, user.getPassword());
        db.insert(TABLE_USER, null, values);
        db.close();
    }

    public User getUser(String username) {
        User user = null;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_USER, new String[] { COLUMN_USERNAME,
                        COLUMN_PASSWORD}, COLUMN_USERNAME + "=?",
                new String[] { String.valueOf(username) }, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            user = new User(cursor.getString(0), cursor.getString(1));
        }
        return user;
    }
}