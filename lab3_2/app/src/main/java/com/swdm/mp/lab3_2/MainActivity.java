package com.swdm.mp.lab3_2;

        import android.content.Intent;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.CheckBox;
        import android.widget.EditText;
        import android.widget.RadioButton;
        import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {

    Button submitBtn;
    RadioGroup radioGroup;
    RadioButton radioMan;
    RadioButton radioWoman;
    EditText editObject;
    CheckBox sms;
    CheckBox email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        radioGroup=(RadioGroup)findViewById(R.id.radioGroup);
        radioMan=(RadioButton)findViewById(R.id.radioMan);
        radioWoman=(RadioButton)findViewById(R.id.radioWoman);
        editObject=(EditText)findViewById(R.id.editName);
        submitBtn=(Button)findViewById(R.id.submitBtn);
        sms=(CheckBox)findViewById(R.id.chkSms);
        email=(CheckBox)findViewById(R.id.chkEmail);

        submitBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String info=editObject.getText().toString()+","+getRadioInfo()+","+getCheckInfo();
                Intent i=new Intent(MainActivity.this,Main2Activity.class);
                i.putExtra("sendInfo",info);
                startActivityForResult(i,2857);
            }
        });
    }
    public String getRadioInfo(){
        if(radioGroup.getCheckedRadioButtonId()==radioMan.getId())
            return "남";
        else
            return "여";
    }
    public String getCheckInfo(){
        String str="";
        if(sms.isChecked())
            str+="SMS";
        if(email.isChecked()) {
            if (str.charAt(0) == 'S')
                str += '&';
            str += "E-mail";
        }
        return str;
    }

    @Override
    protected void onActivityResult(int requestCode,int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        editObject.setText("");
        radioGroup.clearCheck();
        sms.setChecked(false);
        email.setChecked(false);
    }
}
