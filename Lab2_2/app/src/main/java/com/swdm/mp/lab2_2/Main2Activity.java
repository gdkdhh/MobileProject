package com.swdm.mp.lab2_2;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {

    TextView textView;
    Button goBtn;
    Button backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        textView = (TextView) findViewById(R.id.textView);
        goBtn = (Button) findViewById(R.id.Button1);
        backBtn = (Button) findViewById(R.id.Button2);

        Intent passedIntent = getIntent();
        final String passedUrl = passedIntent.getStringExtra("Url");
        textView.setText(passedUrl);

        goBtn.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view){
                if(!passedUrl.equals("")){
                    Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse("http://"+passedUrl));
                    startActivity(intent);
                }
                else{
                    Toast.makeText(getApplicationContext(),"주소를 다시 입력해주세요.",Toast.LENGTH_SHORT).show();
                }
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view){
                Toast.makeText(getApplicationContext(),"뒤로가기버튼을 눌렀습니다.",Toast.LENGTH_LONG).show();
                finish();
            }
        });

    }
}
