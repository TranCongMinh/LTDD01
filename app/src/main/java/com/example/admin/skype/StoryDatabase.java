package com.example.admin.skype;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class StoryDatabase extends SQLiteOpenHelper {

    private static final String TAG = "SQLite";
    private static final int DATABASE_VERSION = 1;

    // Tên cơ sở dữ liệu.
    private static final String DATABASE_NAME = "Story_Manager";
    private static final String TABLE_STORY = "Story";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_AUTHOR = "author";
    private static final String COLUMN_CONTENT = "content";

    public StoryDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String script = "CREATE TABLE " + TABLE_STORY + "("
                + COLUMN_NAME + " TEXT PRIMARY KEY," + COLUMN_AUTHOR + " TEXT," + COLUMN_CONTENT + " TEXT" + ")";
        db.execSQL(script);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STORY);
        onCreate(db);
    }

    public Story getStory(String name) {
        Story story = null;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_STORY, new String[]{COLUMN_NAME,
                        COLUMN_AUTHOR, COLUMN_CONTENT}, COLUMN_NAME + "=?",
                new String[]{String.valueOf(name)}, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            story = new Story(cursor.getString(0), cursor.getString(1), cursor.getString(2));
        }
        return story;
    }

    public void addStory(Story story) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, story.getName());
        values.put(COLUMN_AUTHOR, story.getAuthor());
        values.put(COLUMN_CONTENT, story.getContent());
        db.insert(TABLE_STORY, null, values);
        db.close();
    }

    public int getStorysCount() {
        String countQuery = "SELECT  * FROM " + TABLE_STORY;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();
        return count;
    }

    public void createDefaultStorysIfNeed() {
        int count = this.getStorysCount();
        if (count == 0) {
            Story s1 = new Story("Sự im lặng của bầy cừu","Thomar","Tâm lý học tội phạm");
            Story s2 = new Story("Thạch Sanh","Tố Hữu","Một hôm, như thường ngày chàng tiều phu vác rìu " +
                    "vào rừng để đốn củi, trong lúc đang chặt củi cạnh bờ sông thì chẳng may chiếc rìu của chàng bị gãy cán và " +
                    "lưỡi rìu văng xuống sông.");
            Story s3 = new Story("Cô bé bán diêm","Tản Đà","Anh chàng tiều phu vui vẻ đỡ lấy hai lưỡi rìu " +
                    "mà ông cụ tặng và cảm tạ. Ông cụ hóa phép và biến mất. Lúc đó anh chàng tiều phu mới biết rằng mình vừa được" +
                    " bụt giúp đỡ.");
            Story s4 = new Story("Cô bé lọ lem","Nguyên Hồng","Ngày xưa, có một bà có ba cô con gái. " +
                    "Con lớn tên là Một Mắt vì cô chỉ có độc một mắt ở giữa trán. " +
                    "Cô thứ hai tên là Hai Mắt vì cô có hai mắt như mọi người khác.");
            this.addStory(s1);
            this.addStory(s2);
            this.addStory(s3);
            this.addStory(s4);
        }
    }

    public List<Story> getAllStorys() {
        List<Story> list = new ArrayList<Story>();
        String selectQuery = "SELECT  * FROM " + TABLE_STORY;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Story story = new Story();
                story.setName(cursor.getString(0));
                story.setAuthor(cursor.getString(1));
                story.setContent(cursor.getString(2));
                list.add(story);
            } while (cursor.moveToNext());
        }
        return list;
    }

    public void deleteStory(Story story) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_STORY, COLUMN_NAME + " = ?",
                new String[] { String.valueOf(story.getName()) });
        db.close();
    }
}