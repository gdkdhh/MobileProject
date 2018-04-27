package com.swdm.mp.lab3_3;

        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public Button tab1Btn;
    public Button tab2Btn;

    Fragment1 firFragment;
    Fragment2 secFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        setListener();
    }
    public void init(){
        tab1Btn=(Button)findViewById(R.id.tab1Btn);
        tab2Btn=(Button)findViewById(R.id.tab2Btn);

        firFragment=new Fragment1();
        secFragment=new Fragment2();
    }
    private void setListener(){
        tab1Btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                getSupportFragmentManager().beginTransaction().replace(R.id.frameForFragment,firFragment).commit();
            }
        });
        tab2Btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                getSupportFragmentManager().beginTransaction().replace(R.id.frameForFragment,secFragment).commit();
            }
        });
    }
}
