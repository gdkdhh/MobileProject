package com.swdm.mp.movie_harmony_master;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

//import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ArrayList<MovieItem> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = (ListView)findViewById(R.id.listView);

        //크롤링 정보
        data.add(new MovieItem(R.drawable.movie_1, "데드풀 2"));
        data.add(new MovieItem(R.drawable.movie_2, "어벤져스: 인피니티 워"));
        data.add(new MovieItem(R.drawable.movie_3, "버닝"));
        data.add(new MovieItem(R.drawable.movie_4, "피터 래빗"));
        data.add(new MovieItem(R.drawable.movie_5, "독전"));

        MovieAdapter adapter = new MovieAdapter(this, R.layout.movie_name_items, data);
        listView.setAdapter(adapter);

        //listView click event handling
        listView.setClickable(true);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(getApplicationContext(), "send msg: " + data.get(position).name, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getApplicationContext(), TheaterActivity.class);
                intent.putExtra("image", data.get(position).image);
                intent.putExtra("movie_name", data.get(position).name);
                startActivity(intent);
            }
        });
    }
}
