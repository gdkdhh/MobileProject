package com.swdm.mp.lab5_2;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import static java.lang.Integer.parseInt;

public class MainActivity extends AppCompatActivity {


    EditText editText;
    Button button;
    TextView textView1;
    TextView textView2;
    int cnt;
    int result=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText)findViewById(R.id.edt);
        button = (Button)findViewById(R.id.btn);
        textView1 = (TextView)findViewById(R.id.tv1);
        textView2 = (TextView)findViewById(R.id.tv2);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new AsyncTask<Void,Integer,Void>(){
                @Override
                protected void onPreExecute() {
                    super.onPreExecute();
                    cnt = Integer.parseInt(editText.getText().toString());
                    textView1.setText("");
                    textView2.setText("= ?");
                }


                @Override
                protected Void doInBackground(Void... voids) {
                    for(int i = cnt ; i>0 ; i--) {
                        try{
                            Thread.sleep(500);
                        }catch(InterruptedException e) {
                            e.printStackTrace();
                        }
                        result*=i;
                        publishProgress(i);
                    }
                    return null;
                }

                @Override
                protected void onProgressUpdate(Integer... values) {
                        super.onProgressUpdate(values);
                    textView1.append(values[0]+"  ");
                }


                @Override
                protected void onPostExecute(Void aVoid) {
                    super.onPostExecute(aVoid);
                    textView2.setText("= "+result);
                    result = 1;
                }

                }.execute();
            }
        });
    }

}
