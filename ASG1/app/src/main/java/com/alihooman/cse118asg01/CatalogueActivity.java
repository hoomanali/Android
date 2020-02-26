package com.alihooman.cse118asg01;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cursoradapter.widget.SimpleCursorAdapter;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CatalogueActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    ListView listView;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalogue);


        // Setup columns
        String[] fromColumns = {BookDBContract.BookEntry.COLUMN_NAME_TITLE,
                                BookDBContract.BookEntry.COLUMN_NAME_AUTHOR,
                                BookDBContract.BookEntry.COLUMN_NAME_YEAR};
        int[] toView = {R.id.listItem_Title, R.id.listItem_Author, R.id.listItem_Year};

        // Get the database
        BookDBHelper dbHelper = new BookDBHelper(getApplicationContext());
        cursor = dbHelper.getCursor();

        // Create cursor adapter
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.item_book,
                cursor, fromColumns, toView, 0);

        // Attach cursor to list view
        listView = findViewById(R.id.viewCatalogueList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.d("Catalogue", parent.toString() + view.toString() + position + id);
        Log.d("Catalogue", listView.getItemAtPosition(position).toString());
        Intent intent = new Intent(this, ViewBookActivity.class);

        cursor.moveToPosition(position);

        String title = cursor.getString(1);
        String author = cursor.getString(2);
        String year = cursor.getString(3);
        String description = cursor.getString(4);
        String isbn = cursor.getString(5);
        String ebook = cursor.getString(6);

        ArrayList<String> book = new ArrayList<String>(Arrays.asList(title, author, year,
                description, isbn, ebook));

        intent.putExtra("book", book);
        startActivity(intent);
    }

}
