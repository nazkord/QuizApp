package com.example.nazar.myfirstapplication;

import android.support.v7.app.AppCompatActivity;

import com.example.nazar.myfirstapplication.activities.FirstActivity;
import com.example.nazar.myfirstapplication.db.DbHandler;

/**
 * Created by nazar on 18.05.2017.
 */

public class AppService {

    private static final AppService INSTANCE = new AppService();

    private int activeId;

    private DbHandler dbHandler;

    private AppService() {
    }

    public int getActiveId() {
        return activeId;
    }

    public void setActiveId(int activeId) {
        this.activeId = activeId;
    }

    public DbHandler getDbHandler() {
        if (dbHandler == null) {
            throw new IllegalArgumentException("dbHandler is null");
        }
        return dbHandler;
    }

    public synchronized DbHandler getDbHandler(AppCompatActivity activity) {
        if (dbHandler == null) {
            dbHandler = new DbHandler(activity, null, null, 1);
        }
        return dbHandler;
    }

    public static AppService getInstance() {
        return INSTANCE;
    }
}
