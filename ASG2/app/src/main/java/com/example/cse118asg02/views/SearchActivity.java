package com.example.cse118asg02.views;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Adapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import com.example.cse118asg02.R;
import com.example.cse118asg02.imageDB.DBHelper;
import com.example.cse118asg02.imageDB.ImgCursorAdapter;

public class SearchActivity extends AppCompatActivity {

    SearchView searchView;
    ListView listView;
    Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        searchView = findViewById(R.id.searchView);
        listView = findViewById(R.id.searchResultsList);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                DBHelper db = new DBHelper(SearchActivity.this);
                Cursor cursor = db.searchImage(query);
                adapter = new ImgCursorAdapter(SearchActivity.this, cursor);
                listView.setAdapter((ListAdapter) adapter);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                DBHelper db = new DBHelper(SearchActivity.this);
                Cursor cursor = db.searchImage(newText);
                adapter = new ImgCursorAdapter(SearchActivity.this, cursor);
                listView.setAdapter((ListAdapter) adapter);
                return false;
            }
        });
    }
}
