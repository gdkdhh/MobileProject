package com.swdm.mp.lab4_2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    Button openButton;
    Button closeButton;
    RelativeLayout slidingArea;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        openButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animation anim = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.translate1);
                slidingArea.startAnimation(anim);
                slidingArea.setVisibility(View.VISIBLE);
            }
        });
        closeButton.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view){
                Animation anim = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.translate2);
                slidingArea.startAnimation(anim);
                slidingArea.setVisibility(View.GONE);
            }
        });
    }

    public void init() {
        openButton = findViewById(R.id.openBtn);
        closeButton = findViewById(R.id.closeBtn);
        slidingArea = findViewById(R.id.slidingArea);
    }
}
