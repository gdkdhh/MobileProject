package com.swdm.mp.movie_harmony_master;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;

public class IntroActivity extends AppCompatActivity {
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        progressBar = (ProgressBar)findViewById(R.id.progressBar);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                progressBar.setProgress(1500);
                Intent intent = new Intent(IntroActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, 2000);
    }
}
