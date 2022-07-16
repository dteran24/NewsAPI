package com.example.newsapi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText mHeadlineInput;
    TextView mHeadlineText;
    RecyclerView recyclerView;
    RecyclerAdapter adapter;
    String[] test = {"test", "test","test", "test",
            "test", "test","test", "test","test", "test","test", "test","test", "test",
            "Burger", "test",
            "test", "test","test", "test","test", "test","test", "test","test", "test",


    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mHeadlineInput = findViewById(R.id.editHeadline);

        recyclerView = findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RecyclerAdapter(test);
        recyclerView.setAdapter(adapter);



        mHeadlineText = findViewById(R.id.textView);
    }

    public void getHeadLine(View view) {
        String queryString = mHeadlineInput.getText().toString();

        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = null;
        if (connMgr != null) {
            networkInfo = connMgr.getActiveNetworkInfo();
        }

        if (networkInfo != null && networkInfo.isConnected()
                && queryString.length() != 0) {

            new FetchHeadline(mHeadlineText).execute(queryString);
            mHeadlineInput.setText("");
            mHeadlineText.setText("Loading...");
        } else {
            if (queryString.length() == 0) {
                mHeadlineInput.setText("");
                mHeadlineText.setText("No Results Found!");
            } else {
                mHeadlineInput.setText("");
                mHeadlineText.setText("No internet!");
            }
        }
    }
}