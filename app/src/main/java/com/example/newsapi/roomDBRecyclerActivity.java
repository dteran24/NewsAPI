package com.example.newsapi;

import android.os.AsyncTask;
import android.os.Bundle;

import com.example.newsapi.roomdb.AppDatabase;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newsapi.databinding.ActivityRoomDbrecyclerBinding;

import java.util.List;

public class roomDBRecyclerActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerAdapter adapter;
    AppDatabase db;
    List<HeadlineModel> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_dbrecycler);
        recyclerView = findViewById(R.id.roomDbRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

/**
 * get instance of db, get list of all headlines from db using HeadlineDao
 * connect the list to recycler view using adapter
 */
        db = AppDatabase.getInstance(getApplicationContext());

        list = db.headlineDao().loadAllHeadlines();

        adapter = new RecyclerAdapter(this, list);

        recyclerView.setAdapter(adapter);

    }
}