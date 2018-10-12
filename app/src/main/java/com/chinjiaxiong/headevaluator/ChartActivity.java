package com.chinjiaxiong.headevaluator;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class ChartActivity extends AppCompatActivity {

    private String user = "";
    private int[] results = new int[10];
    String gender = "";
    int age = 20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chart_main);

        // set up for tutorial
//        final TextView textView = findViewById(R.id.textView);

//        slider.setBeginTrackingListener(new Function0<Unit>() {
//            @Override
//            public Unit invoke() {
//                textView.setVisibility(TextView.VISIBLE);
//                return Unit.INSTANCE;
//            }
//        });
//
//        slider.setEndTrackingListener(new Function0<Unit>() {
//            @Override
//            public Unit invoke() {
//                textView.setVisibility(TextView.VISIBLE);
//                return Unit.INSTANCE;
//            }
//        });
//
//        slider.setPositionListener(new Function1<Float, Unit>() {
//            @Override
//            public Unit invoke(Float aFloat) {
//                if(aFloat < 0.4) {
//                    if(!textView.getText().equals(getResources().getString(R.string.tutorial1))){
//                        final AnimatorSet mAnimationSet = new AnimatorSet();
//                        final ObjectAnimator fadeOut, fadeIn;
//                        fadeOut = ObjectAnimator.ofFloat(textView, "alpha",  1f, 0f);
//                        fadeOut.setDuration(500);
//                        mAnimationSet.play(fadeOut);
//                        mAnimationSet.start();
//                        textView.setText(getResources().getString(R.string.tutorial1));
//                        fadeIn = ObjectAnimator.ofFloat(textView, "alpha", 0f, 1f);
//                        fadeIn.setDuration(500);
//                        mAnimationSet.play(fadeIn);
//                        mAnimationSet.start();
//                    }
//                }
//                else if(aFloat < 0.7){
//                    if(!textView.getText().equals(getResources().getString(R.string.tutorial2))){
//                        final AnimatorSet mAnimationSet = new AnimatorSet();
//                        final ObjectAnimator fadeOut, fadeIn;
//                        fadeOut = ObjectAnimator.ofFloat(textView, "alpha",  1f, 0f);
//                        fadeOut.setDuration(500);
//                        mAnimationSet.play(fadeOut);
//                        mAnimationSet.start();
//                        textView.setText(getResources().getString(R.string.tutorial2));
//                        fadeIn = ObjectAnimator.ofFloat(textView, "alpha", 0f, 1f);
//                        fadeIn.setDuration(500);
//                        mAnimationSet.play(fadeIn);
//                        mAnimationSet.start();
//                    }
//                }
//                else{
//                    if(!textView.getText().equals(getResources().getString(R.string.tutorial3))){
//                        final AnimatorSet mAnimationSet = new AnimatorSet();
//                        final ObjectAnimator fadeOut, fadeIn;
//                        fadeOut = ObjectAnimator.ofFloat(textView, "alpha",  1f, 0f);
//                        fadeOut.setDuration(500);
//                        mAnimationSet.play(fadeOut);
//                        mAnimationSet.start();
//                        textView.setText(getResources().getString(R.string.tutorial3));
//                        fadeIn = ObjectAnimator.ofFloat(textView, "alpha", 0f, 1f);
//                        fadeIn.setDuration(500);
//                        mAnimationSet.play(fadeIn);
//                        mAnimationSet.start();
//                    }
//                }
//                return null;
//            }
//        });

        // grab data from last page
        // get data
        Intent inn = getIntent();
        Bundle b = inn.getExtras();
        user = (String)b.get("user");
        results = (int[])b.get("results");
        gender = (String)b.get("gender");
        age = (int)b.get("age");

        // calculate number of flags
        int total = 0;
        for(int i: results){
            if(i == 1) total ++;
        }

        if(total <= 3){
            // green signal
            ImageView iv = (ImageView) findViewById(R.id.imageView4);
            iv.setImageResource(R.drawable.green);
            TextView tv = (TextView)findViewById(R.id.textView6);
            tv.setText("Green Signal");
            TextView tv2 = (TextView)findViewById(R.id.textView);
            tv2.setText(total + " / 10");
            TextView tv3 = (TextView)findViewById(R.id.textView5);
            tv3.setText("You may rest at home, feel free to take assessment again anytime!");
        }else if(total <= 7){
            // yellow signal
            ImageView iv = (ImageView) findViewById(R.id.imageView4);
            iv.setImageResource(R.drawable.yellow);
            TextView tv = (TextView)findViewById(R.id.textView6);
            tv.setText("Yellow Warning");
            TextView tv2 = (TextView)findViewById(R.id.textView);
            tv2.setText(total + " / 10");
            TextView tv3 = (TextView)findViewById(R.id.textView5);
            tv3.setText("You have to do assessment again after 24 hours. If you got yellow warning for 3 continuous days, Please go to hospital!");
        }else{
            ImageView iv = (ImageView) findViewById(R.id.imageView4);
            iv.setImageResource(R.drawable.red);
            TextView tv = (TextView)findViewById(R.id.textView6);
            tv.setText("Red Alert");
            TextView tv2 = (TextView)findViewById(R.id.textView);
            tv2.setText(total + " / 10");
            TextView tv3 = (TextView)findViewById(R.id.textView5);
            tv3.setText("You have to go to the nearest hospital immediately.");
        }

        // save to user data if logged in
        if(!user.isEmpty()){
            // init signal
            String signal;
            if(total <= 3) signal = "Green Signal";
            else if (total <= 7) signal = "Yellow Warning";
            else signal = "Red Alert";
            String score = total + " / 10";
            Date currentTime = Calendar.getInstance().getTime();
            SimpleDateFormat mdformat = new SimpleDateFormat("HH:mm:ss");
            String strTime = mdformat.format(currentTime.getTime());
            SimpleDateFormat mdformat2 = new SimpleDateFormat("yyyy / MM / dd");
            String strTime2 = mdformat2.format(currentTime.getTime());
            ArrayList<String> oldData = FileHelper.readFile();
            String newData = user + "," + Integer.toString(age) + "," + gender + "," + signal + "," + score + "," + strTime2 + "," + strTime;
            oldData.add(newData);
            FileHelper.saveToFile(oldData);
        }

        Button backButton = (Button) findViewById(R.id.button4);
        backButton.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent homeIntent = new Intent(ChartActivity.this, SelectType.class);
                startActivity(homeIntent);
                finish();
            }
        });
    }
}
