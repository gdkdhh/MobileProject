package com.swdm.mp.lab2_1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Intent passedIntent = getIntent();
        if(passedIntent!=null){
            String loginName = passedIntent.getStringExtra("loginName");
            String loginAge = passedIntent.getStringExtra("loginAge");
            Toast.makeText(getApplicationContext(),"Student info : " + loginName + ", " + loginAge,Toast.LENGTH_LONG).show();
        }

        Button button0 = (Button)findViewById(R.id.Button0);

        button0.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view)
            {
                finish();
            }
        });

    }
}
