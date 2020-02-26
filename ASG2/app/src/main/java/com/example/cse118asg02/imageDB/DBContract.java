package com.example.cse118asg02.imageDB;

import android.provider.BaseColumns;

/**
 * Helper class to define the table and keep definitions organized.
 */
final class DBContract {

    /**
     * Private constructor to block accidental instantiation.
     */
    private DBContract() {};

    /**
     * Table definition
     */
    static class DBEntry implements BaseColumns {
        static final String TABLE_NAME = "ImageDataBase";
        static final String COLUMN_URL = "URL";
        static final String COLUMN_TITLE = "Title";

        static final String SQL_CREATE_ENTRIES =
                "CREATE TABLE " + TABLE_NAME + " ("
                + _ID + " Integer Primary Key, "
                + COLUMN_URL + " TEXT, "
                        + COLUMN_TITLE + " TEXT);";

        static final String SQL_DELETE_ENTRIES =
                "DROP TABLE IF EXISTS" + TABLE_NAME;
    }
}
