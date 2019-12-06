package com.example.voteapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.ViewFlipper;

public class TActivity extends AppCompatActivity {


    Button btnStart,btnStop;
    TextView tvrank[] = new TextView[9];
    Integer tvrankID[] = {R.id.tvResult1,R.id.tvResult3,R.id.tvResult5,R.id.tvResult7,R.id.tvResult9,R.id.tvResult11,R.id.tvResult13,R.id.tvResult15,R.id.tvResult17};
    ImageView ivrank[] = new ImageView[9];
    Integer ivrankID[] = {R.id.ivResult,R.id.ivResult1,R.id.ivResult2,R.id.ivResult3,R.id.ivResult4,R.id.ivResult5,R.id.ivResult6,R.id.ivResult7,R.id.ivResult8};
    ViewFlipper viewFlipper1;
    TextView tvname[] = new TextView[9];
    Integer tvnameID[]  = {R.id.tvResult2,R.id.tvResult4,R.id.tvResult6,R.id.tvResult8,R.id.tvResult10,R.id.tvResult12,R.id.tvResult14,R.id.tvResult16,R.id.tvResult18};

    Integer imgFild[] = { R.drawable.pic1,R.drawable.pic2,R.drawable.pic3,R.drawable.pic4,R.drawable.pic5,R.drawable.pic6,R.drawable.pic7,R.drawable.pic8,R.drawable.pic9};  //1.등수에 따른 이미지 변경을 위한 배열 생성

    int tmp;  // 2.소트 알고리즘을 위한 변수 선언
    String strtmp;  //3. 소트 알고리즘을 위한 변수 선언 (명화이름)

    int voteResult[] ;  //4.투표수 배열 선언
    String ingName[] ; // 5.명화 이름 배열 선언



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_t);
        btnStart=(Button)findViewById(R.id.btnStart);
        btnStop=(Button)findViewById(R.id.btnStop);
        viewFlipper1=(ViewFlipper)findViewById(R.id.viewFlipper1);
        for(int i =0; i< imgFild.length ; i++) {  //6.배열로 케스팅 하는 방법이니 잘 기억하고 써먹자.
            tvrank[i]=(TextView)findViewById(tvrankID[i]);
            ivrank[i]=(ImageView)findViewById(ivrankID[i]);
            tvname[i]=(TextView)findViewById(tvnameID[i]);
        }
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("투표결과");
        actionBar.setIcon(R.drawable.pici_icon);

            // 7.메인엑티비티에서 보내 준 데이터 인텐트로 받기
            Intent gIntent =getIntent();
            voteResult=gIntent.getIntArrayExtra("VoteCount");
            ingName=gIntent.getStringArrayExtra("ImageName");
            //8.소트 알고리즘으로 투표수 정렬(내림차순)
            for(int a=0; a<voteResult.length-1; a++) {//비교횟수
                for(int b = a+1; b<voteResult.length; b++) {
                    if(voteResult[a] < voteResult[b]) {//오름차순정렬
                        tmp=voteResult[a];
                        voteResult[a]=voteResult[b];
                        voteResult[b]=tmp;  //---> 투표수 가지고 비교를 하지만

                        strtmp=ingName[a];
                        ingName[a]=ingName[b];
                        ingName[b]=strtmp;  //---> 이름도 변경해야되고,

                        tmp=imgFild[a];
                        imgFild[a]=imgFild[b];
                        imgFild[b]=tmp; //---> 이미지도 그에 맞게 변경해야된다.

                    }
                }
            }
            //9. 정렬된 데이터를 위젯에 대입
        for (int  i =0  ; i<imgFild.length; i++) {
            tvrank[i].setText((i+1)+"등");
            ivrank[i].setImageResource(imgFild[i]);
            tvname[i].setText(ingName[i]+("총"+voteResult[i]+"표"));
        }
        viewFlipper1.setFlipInterval(2000);




        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewFlipper1.startFlipping();


            }
        });


        btnStop.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    viewFlipper1.stopFlipping();
                }
            });




    }
}
