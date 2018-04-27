package com.swdm.mp.lab3_2;


        import android.content.Intent;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    Button goBackBtn;
    TextView textName;
    TextView textOld;
    TextView textReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        textName=(TextView)findViewById(R.id.textName);
        textOld=(TextView)findViewById(R.id.textOld);
        textReceiver=(TextView)findViewById(R.id.textReceiver);

        String data[]=getIntent().getStringExtra("sendInfo").split(",");

        textName.setText(data[0]);
        textOld.setText(data[1]);
        textReceiver.setText(data[2]);

        goBackBtn=(Button)findViewById(R.id.goBackBtn);
        goBackBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                setResult(RESULT_OK);
                finish();
            }
        });
    }
}
