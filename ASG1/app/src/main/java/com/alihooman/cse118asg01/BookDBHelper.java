package com.alihooman.cse118asg01;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


class BookDBHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "BookDatabase.db";

    BookDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        try {
            SQLiteDatabase checkDB;
            checkDB = SQLiteDatabase.openDatabase(BookDBContract.BookEntry.TABLE_NAME, null,
                    SQLiteDatabase.OPEN_READONLY);
            checkDB.close();

        } catch(Exception e) {
            String SQL_CREATE_ENTRIES = "CREATE TABLE " + BookDBContract.BookEntry.TABLE_NAME
                    + " (" + BookDBContract.BookEntry._ID + " INTEGER PRIMARY KEY,"
                    + BookDBContract.BookEntry.COLUMN_NAME_TITLE + " TEXT,"
                    + BookDBContract.BookEntry.COLUMN_NAME_AUTHOR + " TEXT,"
                    + BookDBContract.BookEntry.COLUMN_NAME_YEAR + " TEXT,"
                    + BookDBContract.BookEntry.COLUMN_NAME_DESCRIPTION + " TEXT,"
                    + BookDBContract.BookEntry.COLUMN_NAME_ISBN + " TEXT,"
                    + BookDBContract.BookEntry.COLUMN_NAME_EBOOK + " Text)";
            db.execSQL(SQL_CREATE_ENTRIES);
        }

    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + BookDBContract.BookEntry.TABLE_NAME;
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    public void onDownGrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    /**
     *
     * @param book The book to be added to the database.
     * @return The new row's ID if successful, or -1 if failed.
     */
    long addBook(Book book, BookDBHelper dbHelper) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(BookDBContract.BookEntry.COLUMN_NAME_TITLE, book.getTitle());
        values.put(BookDBContract.BookEntry.COLUMN_NAME_AUTHOR, book.getAuthor());
        values.put(BookDBContract.BookEntry.COLUMN_NAME_YEAR, book.getYear());
        values.put(BookDBContract.BookEntry.COLUMN_NAME_DESCRIPTION, book.getDescription());
        values.put(BookDBContract.BookEntry.COLUMN_NAME_ISBN, book.getIsbn());
        if(book.getE_book()) {
            values.put(BookDBContract.BookEntry.COLUMN_NAME_EBOOK, "Yes");
        } else {
            values.put(BookDBContract.BookEntry.COLUMN_NAME_EBOOK, "No");
        }

        long rowID;
        rowID = db.insert(BookDBContract.BookEntry.TABLE_NAME, null, values);

        return rowID;
    }

    Cursor getCursor() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.query(BookDBContract.BookEntry.TABLE_NAME, null, null,
                null, null, null, null, null);
        c.moveToFirst();
        return c;
    }

}
