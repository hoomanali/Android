package com.example.cse118asg02.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.cse118asg02.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * Create an intent for DownloadActivity and start it.
     * @param view The current view.
     */
    public void onDownloadClick(View view) {
        Intent intent = new Intent(this, DownloadActivity.class);
        startActivity(intent);
    }

    /**
     * Create an intent for ViewActivity and start it.
     * @param view The current view.
     */
    public void onViewClick(View view) {
        Intent intent = new Intent(this, ViewActivity.class);
        startActivity(intent);
    }

    /**
     * Create an intent for SearchActivity and start it.
     * @param view The current view.
     */
    public void onSearchClick(View view) {
        Intent intent = new Intent(this, SearchActivity.class);
        startActivity(intent);
    }
}
