package com.example.voteapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {



    ImageView iv[] = new ImageView[9];  // 1. 9개 배열 생성
    Button btnResult,btnSortResult;
    int voteCount[]=new int[9];//2.정수형 배열 생성
    Integer ivID[] = {R.id.iv1,R.id.iv2,R.id.iv3,R.id.iv4,R.id.iv5,R.id.iv6,R.id.iv7,R.id.iv8,R.id.iv9}; //3. 배열로 그림 결과 처리하기 위해 생성
    String ivName[] =  {"독서하는 소녀","꽃장식 모자 소녀","부채를 든 소녀","이레느깡 단 베르양","잠자는 소녀","테라스의 두 자매","피아노 레슨","피아노 앞의 소녀들","해변에서" };  //4.명화 이름을 보여주기 위해 만드는 배열

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("명화 선호도 투표");
        actionBar.setIcon(R.drawable.pici_icon);
        actionBar.setDisplayShowHomeEnabled(true);


        for (int i = 0;  i<ivID.length; i++) {
            iv[i] = (ImageView)findViewById(ivID[i]);
            voteCount[i]=0;  //5. 배열은 초기값이 들어있기 때문에 설정안해도 되지만 확실하게 하기 위해 초기값을 부여하였다.
        }


        for (int  i =0; i<ivID.length; i++) {
            final int index;
            index = i;
            //6. setOnClickListener 를 9개 만들지않고 배열을 통해 for문을 돌려 간략화 한다.
            iv[index].setOnClickListener(new View.OnClickListener() {  //7.  final int index;   index = i;  반드시 항상 외어두자!! 입력 후 배열의 값에i대신 index 입력 ★★★★★★
                @Override
                public void onClick(View v) {
                    voteCount[index]++;   //8. index값만큼씩 증가
                    Toast.makeText(getApplicationContext(),ivName[index]+" : 총 "+voteCount[index]+"표",Toast.LENGTH_SHORT).show();
                }
            });
        }

        btnResult=(Button)findViewById(R.id.btnResult);
        btnResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent(getApplicationContext(),AnswerActivity.class);  //9.인스턴스 객체 생성
                mIntent.putExtra("ImageName",ivName);  //11..putExtra() 호출하기 전에 무언가를 '담아서 호출한다'   2가지  (변수,값)
                // 11."ImageName" -> 일반 변수랑 구분하기 위해 대문자를 쓴다.  값의 성격에 따라 변수부분이 알아서 준비해준다.(일반 변수면 변수값, 배열이면 배열)
                mIntent.putExtra("VoteCount",voteCount);  //12. 11번과 마찬가지.   13번부터는 두번째 자바로 이동
                startActivity(mIntent);  //10.화면을 호출한다.
            }
        });

        btnSortResult=(Button)findViewById(R.id.btnSortResult);

        btnSortResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent(getApplicationContext(),TActivity.class);
                mIntent.putExtra("ImageName",ivName);
                mIntent.putExtra("VoteCount",voteCount);
                startActivity(mIntent);
            }
        });



    }
}
