package com.chinjiaxiong.headevaluator;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.support.design.widget.FloatingActionButton;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class QuestionActivity extends AppCompatActivity {

    private String user = "";
    private String gender = "";
    private int age = 0;
    private int[] results = new int[10];
    private int currentQuestion = 0;
    private String[] questionSet =
            {       "Do you feel pain anywhere on your head?",
                    "Are there any events that were just BEFORE or AFTER the injury that you have no memory of?",
                    "Did you lose consciousness?",
                    "Have you vomitted or do you feel nauseous (feel of wanting to vomit)? ",
                    "Do you have trouble standing or balancing yourself?",
                    "Do you have trouble seeing nearby objects?",
                    "Do you hear ringing in your ears? ",
                    "Have other people noticed a difference in your speaking behaviour?",
                    "Do people say that you are more irritable or emotional? ",
                    "Did you happen to have any periods of uncontrollable fits (shaking and trembling of the whole body)?",
            };

    private void displayQuestion(){
        String[] currentQuestionSet = questionSet;
        TextView tv = (TextView)findViewById(R.id.textView6);
        final AnimatorSet mAnimationSet = new AnimatorSet();
        final ObjectAnimator fadeOut, fadeIn;
        fadeOut = ObjectAnimator.ofFloat(tv, "alpha",  1f, 0f);
        fadeOut.setDuration(500);
        fadeIn = ObjectAnimator.ofFloat(tv, "alpha", 0f, 1f);
        fadeIn.setDuration(500);
        mAnimationSet.play(fadeOut);
        mAnimationSet.start();
        tv.setText(currentQuestionSet[currentQuestion]);
        mAnimationSet.play(fadeIn);
        mAnimationSet.start();
        TextView tv2 = (TextView)findViewById(R.id.textView5);
        String tmp = "Question " + (currentQuestion+1) + " out of " + currentQuestionSet.length;
        tv2.setText(tmp);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.question_main);
        Intent inn = getIntent();
        Bundle b = inn.getExtras();
        if(b!=null){
            this.user = (String)b.get("user");
            this.results = (int[])b.get("results");
            this.currentQuestion = (int)b.get("currentQuestion");
            this.gender = (String)b.get("gender");
            this.age = (int)b.get("age");
        }
        displayQuestion();

        FloatingActionButton fab2 = (FloatingActionButton)findViewById(R.id.floatingActionButton);
        if(user.isEmpty()) fab2.setEnabled(false);
        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent homeIntent = new Intent(QuestionActivity.this, ReportActivity.class);
                homeIntent.putExtra("user", user);
                startActivity(homeIntent);
            }
        });

        Button yesButton = (Button) findViewById(R.id.button4);
        yesButton.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                results[currentQuestion] = 1;
                if(currentQuestion != questionSet.length-1){
                    currentQuestion++;
                    displayQuestion();
                }else{
                    Intent homeIntent = new Intent(QuestionActivity.this, ChartActivity.class);
                    homeIntent.putExtra("user", user);
                    homeIntent.putExtra("results", results);
                    homeIntent.putExtra("age", age);
                    homeIntent.putExtra("gender", gender);
                    startActivity(homeIntent);
                    finish();
                }
            }
        });

        Button noButton = (Button) findViewById(R.id.button5);
        noButton.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                results[currentQuestion] = 0;
                if(currentQuestion != questionSet.length-1){
                    currentQuestion++;
                    displayQuestion();
                }else{
                    Intent homeIntent = new Intent(QuestionActivity.this, ChartActivity.class);
                    homeIntent.putExtra("user", user);
                    homeIntent.putExtra("results", results);
                    homeIntent.putExtra("age", age);
                    homeIntent.putExtra("gender", gender);
                    startActivity(homeIntent);
                    finish();
                }
            }
        });
    }
}
