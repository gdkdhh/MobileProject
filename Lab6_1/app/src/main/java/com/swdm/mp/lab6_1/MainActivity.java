package com.swdm.mp.lab6_1;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {


    String filename = "lab6_1.txt";
    EditText editText;
    Button writebtn;
    Button clearbtn;
    Button readbtn;
    Button finishbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText=(EditText)findViewById(R.id.txtData);
        writebtn=(Button)findViewById(R.id.writeBtn);
        clearbtn=(Button)findViewById(R.id.clearBtn);
        readbtn=(Button)findViewById(R.id.readBtn);
        finishbtn=(Button)findViewById(R.id.finishBtn);


        writebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputData = editText.getText().toString();
                if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED))
                {
                    File directory = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/lab6_1");
                    directory.mkdirs();
                   try {
                       File file = new File(directory, filename);
                       OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(file));
                       osw.write(inputData);
                       osw.close();
                       System.out.println("썼습니다.");
                   }catch(Exception e) {
                       e.printStackTrace();
                   }
                }
                Toast.makeText(getApplicationContext(),"Done writing SD "+filename,Toast.LENGTH_SHORT).show();
        }
        });

        clearbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText("");
                Toast.makeText(getApplicationContext(),"Done clearing screen",Toast.LENGTH_SHORT).show();
            }
        });

        readbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED))
                {
                    File directory = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/lab6_1");
                    try
                    {
                        File file = new File(directory,filename);
                        InputStreamReader isr = new InputStreamReader(new FileInputStream(file));
                        String readData="";
                        while(isr.ready()){
                            readData = readData + (char)isr.read();
                            System.out.println("읽었습니다.");
                        }
                        editText.setText(readData);
                    }catch(Exception e) {
                        e.printStackTrace();
                    }

                }
                Toast.makeText(getApplicationContext(),"Done reading SD "+filename,Toast.LENGTH_SHORT).show();
            }
        });

        finishbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Finsh application",Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}
