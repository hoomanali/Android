package com.example.cse118asg02.imageDB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.cse118asg02.images.MyImage;

import java.util.ArrayList;

/**
 * Helper class for creating and managing a database.
 */
public class DBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Images.db";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DBContract.DBEntry.SQL_CREATE_ENTRIES);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DBContract.DBEntry.SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    /**
     * Adds an image url and title as a new row in the database.
     * @param image Image object to be added to database.
     * @return The ID of the new row.
     */
    public long addImage(MyImage image) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DBContract.DBEntry.COLUMN_URL, image.getUrlString());
        values.put(DBContract.DBEntry.COLUMN_TITLE, image.getTitle());

        return db.insert(DBContract.DBEntry.TABLE_NAME, null, values);
    }

    /**
     * Deletes an image (row) from database.
     * @param id The row ID for the image.
     */
    public void deleteImage(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(DBContract.DBEntry.TABLE_NAME, "_id=?", new String[]{id});
    }

    /**
     * Searches the database and returns a cursor with items.
     * @param searchString Terms to search for.
     * @return A cursor pointing to relevant rows in database.
     */
    public Cursor searchImage(String searchString) {
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor c;
        String table = DBContract.DBEntry.TABLE_NAME;
        String[] columns = new String[]{DBContract.DBEntry._ID,
                                        DBContract.DBEntry.COLUMN_URL,
                                        DBContract.DBEntry.COLUMN_TITLE};
        searchString = "%" + searchString + "%";
        String where = DBContract.DBEntry.COLUMN_TITLE + " LIKE ?";
        String[] whereArgs = new String[]{searchString};

        c = db.query(table, columns, where, whereArgs, null,null,null);

        c.moveToFirst();
        return c;
    }

    /**
     * Creates a cursor for a writable database.
     * @return A SQLite database cursor.
     */
    public Cursor getCursor() {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("SELECT * FROM " + DBContract.DBEntry.TABLE_NAME, null);
    }
}
