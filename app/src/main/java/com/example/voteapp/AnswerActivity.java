package com.example.voteapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

public class AnswerActivity extends AppCompatActivity {

    TextView tv[]=new TextView[9];
    RatingBar rBar[] = new RatingBar[9];
    Button btnreturn;
    TextView tvid;
    int maxEntry = 0;
    ImageView ivResult;

    Integer tvID[]= {R.id.tv1,R.id.tv2,R.id.tv3,R.id.tv4,R.id.tv5,R.id.tv6,R.id.tv7,R.id.tv8,R.id.tv9};
    Integer rBarID[] ={R.id.rbar1,R.id.rbar2,R.id.rbar3,R.id.rbar4,R.id.rbar5,R.id.rbar6,R.id.rbar7,R.id.rbar8,R.id.rbar9};
    Integer imageFileID[] = {R.drawable.pic1,R.drawable.pic2,R.drawable.pic3,R.drawable.pic4,R.drawable.pic5,R.drawable.pic6,R.drawable.pic7,R.drawable.pic8,R.drawable.pic9};

     int voteResult[];//15. 정수형 배열 선언  - > 투표수를 받을 배열
     String imageName[]; //16. 문자형 배열 선언 -> 이미지 이름을 받을 배열


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("투표결과");
        actionBar.setIcon(R.drawable.pici_icon);
        actionBar.setDisplayShowHomeEnabled(true);
        tvid = (TextView)findViewById(R.id.tvid);
        ivResult=(ImageView)findViewById(R.id.ivResult);


        for (int i=0; i<rBar.length ; i++ ) {
            tv[i] = (TextView)findViewById(tvID[i]);    // 13. 배열로 선언
            rBar[i]=(RatingBar)findViewById(rBarID[i]); //13-1 배열로 선언


        }
        btnreturn=(Button)findViewById(R.id.btnreturn);

        Intent gIntent = getIntent(); //14. Intent 로 보냈으니 Intent 로 받는다. 받기만 하기 때문에 new 안해도 됨
        voteResult=gIntent.getIntArrayExtra("VoteCount");//17.  .getIntArrayExtra  만약에 배열이 아니면 IntExtra 이다. 이것을 자동으로 해준다.
        imageName=gIntent.getStringArrayExtra("ImageName"); //  18. .getStringArrayExtra 마찬가지로 배열이 아니라면 .getStringIntExtra 이다.


        for (int i=0; i<rBarID.length; i++) {
            if(voteResult[maxEntry]<voteResult[i]) {   //if문을 썼을 때의 정답
                maxEntry=i;  //if문을 썼을 때의 정답
            }  //if문을 썼을 때의 정답
            tv[i].setText(imageName[i]+"  총 득표 수 : "+voteResult[i]+"표");
            rBar[i].setRating((float)voteResult[i]);   //19.(float)값을 주는 이유는 받을때 0.5개씩 받을 수도 있기 때문에적용
        }
        tvid.setText(imageName[maxEntry]);  //if문을 썼을 때의 정답
        ivResult.setImageResource(imageFileID[maxEntry]);  //if문을 썼을 때의 정답
        btnreturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
