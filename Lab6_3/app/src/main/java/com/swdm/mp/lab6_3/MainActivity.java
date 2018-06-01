package com.swdm.mp.lab6_3;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText nameEdt;
    EditText numEdt;
    Button apdBtn;
    Button delBtn;
    ListView listView;
    MySQLiteOpenHelper helper = new MySQLiteOpenHelper(this,"student.db",null,1);
    SQLiteDatabase db;
    ArrayAdapter<String> adapter;
    String[] str;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nameEdt = (EditText)findViewById(R.id.edt1);
        numEdt = (EditText)findViewById(R.id.edt2);
        apdBtn = (Button)findViewById(R.id.apdBtn);
        delBtn = (Button)findViewById(R.id.delBtn);
        listView = (ListView)findViewById(R.id.listView);

        apdBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(nameEdt.getText().toString().equals("")||numEdt.getText().toString().equals(""))
                    Toast.makeText(getApplicationContext(),"모든 항목을 입력해주세요.",Toast.LENGTH_SHORT).show();
                else{
                    db = helper.getWritableDatabase();
                    ContentValues cv = new ContentValues();
                    cv.put("name",nameEdt.getText().toString());
                    cv.put("studentNo",numEdt.getText().toString());
                    db.insert("student",null,cv);
                    invalidate();
                }

                }
            });

        delBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(nameEdt.getText().toString().equals(""))
                    Toast.makeText(getApplicationContext(),"이름을 입력해주세요.",Toast.LENGTH_SHORT).show();
                else{
                    db = helper.getWritableDatabase();
                    db.delete("student","name = ?",new String[] {nameEdt.getText().toString()});
                    invalidate();
                }

            }
        });
    }

    public void select(){
        db = helper.getReadableDatabase();
        Cursor c= db.query("student",null,null,null,null,null,null);

        str = new String[c.getCount()];
        int count = 0;
        while(c.moveToNext()){
            str[count] = c.getString(c.getColumnIndex("name")) + "  " + c.getString(c.getColumnIndex("studentNo"));
            count++;
        }

        c.close();
    }

    private void invalidate(){
       select();
       adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,str);
       listView.setAdapter(adapter);
    }
}
