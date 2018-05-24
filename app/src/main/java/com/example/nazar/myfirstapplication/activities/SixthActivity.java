package com.example.nazar.myfirstapplication.activities;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;

import com.example.nazar.myfirstapplication.R;

public class SixthActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sixth);

        showGif();
    }

    private void showGif() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                finish();
            }
        }, 5000);
    }
}