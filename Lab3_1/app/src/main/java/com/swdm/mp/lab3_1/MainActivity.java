package com.swdm.mp.lab3_1;

        import android.graphics.Color;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.ContextMenu;
        import android.view.MenuItem;
        import android.view.View;
        import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn=(Button)findViewById(R.id.btn);
        registerForContextMenu(btn);
    }

    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo){
        super.onCreateContextMenu(menu, v, menuInfo);
        ContextMenu contextMenu = menu.setHeaderTitle("Button Menu");
        menu.add(0,0,0,"Red");
        menu.add(1,1,1,"Green");
        menu.add(2,2,2,"Blue");
    }
    public boolean onContextItemSelected(MenuItem item){
        int getId=item.getItemId();

        switch(getId){
            case 0:btn.setTextColor(Color.RED);break;
            case 1:btn.setTextColor(Color.GREEN);break;
            case 2:btn.setTextColor(Color.BLUE);break;
            default:break;
        }
        return super.onContextItemSelected(item);
    }
}
