package com.swdm.mp.lab5_1;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ImageView imageView1;
    ImageView imageView2;
    EditText editText;
    Button button;
    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView1 = (ImageView)findViewById(R.id.img1);
        imageView2 = (ImageView)findViewById(R.id.img2);
        editText = (EditText)findViewById(R.id.edt1);
        button = (Button)findViewById(R.id.btn1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DogThread(1).start();
                new DogThread(2).start();
            }
        });

    }

    private class DogThread extends Thread{
        int whatDog;
        int stateIndex;
        ArrayList<Integer> images = new ArrayList<Integer>();

        private DogThread(int index)
        {
            whatDog = index;

            images.add(R.drawable.dog1);
            images.add(R.drawable.dog2);
            images.add(R.drawable.dog3);
        }

        public void run(){
            stateIndex = 0;
            for(int i = 0 ; i < 10 ; i++) {
                final String msg = "dog #" + whatDog + " state: " + stateIndex+"\n";
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if(whatDog==1)
                            imageView1.setImageResource(images.get(stateIndex));
                        else if(whatDog==2)
                            imageView2.setImageResource(images.get(stateIndex));

                        editText.append(msg);
                    }
                });
                if(++stateIndex>2)
                    stateIndex=0;
                try{
                    int sleepTime = getRandomTime(500,3000);
                    Thread.sleep(sleepTime);
                }catch(InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
        public int getRandomTime(int min, int max) {
            return min+(int)(Math.random() * (max-min));
        }
    }
}
