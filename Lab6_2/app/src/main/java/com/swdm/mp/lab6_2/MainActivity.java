package com.swdm.mp.lab6_2;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText edt1;
    EditText edt2;
    Button readBtn;
    Button writeBtn;
    Button clearBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edt1 = (EditText)findViewById(R.id.edt1);
        edt2 = (EditText)findViewById(R.id.edt2);
        readBtn = (Button)findViewById(R.id.readBtn);
        writeBtn = (Button)findViewById(R.id.writeBtn);
        clearBtn = (Button)findViewById(R.id.clearBtn);

        readBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sp = getSharedPreferences("student",MODE_PRIVATE);
                edt1.setText(sp.getString("studentNumber",""));
                edt2.setText(sp.getString("studentName",""));
            }
        });

        writeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sp = getSharedPreferences("student",MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                editor.putString("studentNumber",edt1.getText().toString());
                editor.putString("studentName",edt2.getText().toString());
                editor.commit();
            }
        });

        clearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edt1.setText("");
                edt2.setText("");
            }
        });
    }
}
