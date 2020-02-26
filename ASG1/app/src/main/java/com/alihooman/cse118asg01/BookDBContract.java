package com.alihooman.cse118asg01;

import android.provider.BaseColumns;

/**
 * Contract for SQLite database layout.
 */
public final class BookDBContract {
    private BookDBContract() {}

    /**
     * Define columns
     */
    public static class BookEntry implements BaseColumns {
        public static final String TABLE_NAME = "books";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_AUTHOR = "author";
        public static final String COLUMN_NAME_YEAR = "year";
        public static final String COLUMN_NAME_DESCRIPTION = "description";
        public static final String COLUMN_NAME_ISBN = "isbn";
        public static final String COLUMN_NAME_EBOOK = "ebook";
    }
}
