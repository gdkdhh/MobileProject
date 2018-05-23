package com.swdm.mp.movieharmonymaster;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class DetailActivity extends AppCompatActivity {

    TextView theaterName; //theater name
    ImageView movieImage;
    Button loadBtn;

    TextView textView; //결과를 띄워줄 textView

    Elements contents;
    Elements content;
    Elements tags;
    Elements li_tags;
    Document doc = null;

    String movie_name="";
    String seat_count="";
    String movie_time="";

    int date=20180523; //날짜: 과거 날짜의 경우 인터넷에선 현재 주소로 바꿔버린다.
    String url="http://www.cgv.co.kr/common/showtimes/iframeTheater.aspx?areacode=01&theatercode=0196&date=\"+date+\"&screencodes=&screenratingcode=&regioncode=";

    String str=""; // 결과를 저장할 문자열 변수

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        theaterName = (TextView)findViewById(R.id.theaterName);

        Intent intent = getIntent();
        theaterName.setText(intent.getStringExtra("theater_name") +" - "+ intent.getStringExtra("selectedMovie"));

        textView = (TextView)findViewById(R.id.textView);
        loadBtn = (Button)findViewById(R.id.loadBtn);
        movieImage=(ImageView)findViewById(R.id.imageView);

        movieImage.setImageResource(intent.getIntExtra("movieImage",0));

        loadBtn.setOnClickListener(new View.OnClickListener() {//onclicklistener를 연결하여 터치시 실행됨
            @Override
            public void onClick(View v) {
                new AsyncTask() {//AsyncTask객체 생성
                    @Override
                    protected Object doInBackground(Object[] params) {

                        try {
                            //doc = Jsoup.connect("https://search.naver.com/search.naver?where=nexearch&sm=top_hty&fbm=1&ie=utf8&query=%EB%A1%AF%EB%8D%B0%EC%8B%9C%EB%84%A4%EB%A7%88+%EC%8B%A0%EB%A6%BC").get(); //naver페이지를 불러옴
                            //contents = doc.select("span.time_info a");//셀렉터로 span태그중 class값이 ah_k인 내용을 가져옴

                            doc = Jsoup.connect(url).get(); //url 페이지불러오기
                            contents = doc.select("div.sect-showtimes"); //전체 영화정보
                            content = contents.select("div.col-times"); //각 영화당 상영정보
                            for (Element timetable : content) //해당 영화관에사 상영중인 영화 개수만큼 반복
                            {
                                movie_name = timetable.select("a strong").text(); //영화 이름 저장 영화 이름 text

                                //str은 전체 영화+상영정보를 모두 합쳐서 보여줌.

                                if(movie_name.equals(getIntent().getStringExtra("selectedMovie")))
                                {
                                    str+=movie_name+"\n";
                                    tags = timetable.select("div.info-timetable"); //영화 상영시간표
                                    for (Element tag : tags) {
                                        li_tags = tag.select("ul li");
                                        String movie_data = ""; //영화상영정보(시간+좌석)
                                        for (Element a_tag : li_tags) {

                                            movie_time = a_tag.select("em").text(); //시간
                                            seat_count = a_tag.select("span").text(); //좌석
                                            seat_count = seat_count.replace("잔여좌석", ""); //"마감"은 span 이고 잔여좌석이 있을경우 span span 에 저장되어 있어서 "잔여좌석"문구 삭제처리.

                                            movie_data += (movie_time + " - " + seat_count + "\n");
                                        }

                                        str += movie_data; //for 한번 돌때 상위 영화의 상영정보 전체가 저장됨.
                                    }
                                    str += "\n";
                                }
                            }

                        }
                        catch (IOException e) {
                            e.printStackTrace();
                        }

                        return null;

                    }

                    @Override

                    protected void onPostExecute(Object o) {

                        super.onPostExecute(o);
                        if(str.equals("")) { //영화 제목으로 정보를 가져오지 못했을때
                            textView.setText("현재 해당 영화관에 상영중이 아닙니다.");
                            textView.setTextSize(20);
                        }
                        else { //정보를 가져왔을경우 출력해준다.
                            textView.setText(str);
                            textView.setTextSize(30);
                            str = ""; //한번더 새로운 정보를 가져올때 중첩되지 않도록한다.
                        }
                    }
                }.execute();

            }

        });

    }

}
