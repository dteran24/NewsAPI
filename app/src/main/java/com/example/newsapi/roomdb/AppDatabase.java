package com.example.newsapi.roomdb;

import android.content.Context;
import android.util.Log;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.newsapi.HeadlineModel;

@Database(entities = {HeadlineModel.class}, version = 1, exportSchema = false)
/**
 * Creating a singleton for the database
 */
public abstract class AppDatabase extends RoomDatabase {
    private static final String LOG_TAG = AppDatabase.class.getSimpleName();
    private static final Object LOCK = new Object();
    private static final String DATABASE_NAME = "headlinelist";
    private static AppDatabase sInstance;

    public static AppDatabase getInstance(Context context) {
        if (sInstance == null) {
            synchronized (LOCK) {
                Log.d(LOG_TAG, "Creating new database instance");
                sInstance = Room.databaseBuilder(context.getApplicationContext(),
                                AppDatabase.class, AppDatabase.DATABASE_NAME).allowMainThreadQueries()
                        .build();
            }
        }
        Log.d(LOG_TAG, "Getting the database instance");
        return sInstance;
    }

    public abstract HeadlineDao headlineDao();
}
