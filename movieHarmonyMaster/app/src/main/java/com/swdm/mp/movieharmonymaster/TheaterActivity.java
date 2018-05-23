package com.swdm.mp.movieharmonymaster;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class TheaterActivity extends AppCompatActivity {

    //ArrayList<TheaterItem> theaterItems = new ArrayList<>();
    public String[] theaterItems = {"CGV 서현", "CGV 야탑", "CGV 오리", "CGV 판교", "메가박스 분당"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theater);

        ImageView selectedImage = (ImageView)findViewById(R.id.selectedImage);
        final TextView selectedMovie = (TextView)findViewById(R.id.selectedMovie);

        Intent intent = getIntent();
        selectedImage.setImageResource(intent.getIntExtra("image", 0));
        selectedMovie.setText(intent.getStringExtra("movie_name"));

        ListView theaterList = (ListView)findViewById(R.id.theaterList);

        /*theaterItems.add(new TheaterItem("CGV 서현"));
        theaterItems.add(new TheaterItem("CGV 야탑"));
        theaterItems.add(new TheaterItem("CGV 오리"));
        theaterItems.add(new TheaterItem("CGV 판교"));
        theaterItems.add(new TheaterItem("메가박스 분당"));*/

        ArrayAdapter<String> theaterAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, theaterItems);
        theaterList.setAdapter(theaterAdapter);


        theaterList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(getApplicationContext(), "send msg: " + data.get(position).name, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getApplicationContext(), DetailActivity.class);
                intent.putExtra("theater_name", theaterItems[position]);
                intent.putExtra("selectedMovie", selectedMovie.getText().toString());
                intent.putExtra("movieImage",getIntent().getIntExtra("image", 0));
                startActivity(intent);
            }
        });

    }
}
