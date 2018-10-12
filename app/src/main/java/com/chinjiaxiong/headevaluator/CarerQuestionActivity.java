package com.chinjiaxiong.headevaluator;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class CarerQuestionActivity extends AppCompatActivity {
    private final String TAG = "testresult";
    private String gender = "";
    private String user = "";
    private int age = 20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.carer_question_main);

        Intent inn = getIntent();
        Bundle b = inn.getExtras();
        if(b!=null){
            this.user = (String)b.get("user");
            this.gender = (String)b.get("gender");
            this.age = (int)b.get("age");
        }

        Button button = (Button) findViewById(R.id.button5);
        button.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                CheckBox checkBox1 = (CheckBox)findViewById(R.id.checkBox);
                CheckBox checkBox2 = (CheckBox)findViewById(R.id.checkBox2);
                CheckBox checkBox3 = (CheckBox)findViewById(R.id.checkBox3);
                CheckBox checkBox4 = (CheckBox)findViewById(R.id.checkBox4);
                if(checkBox1.isChecked() || checkBox2.isChecked() || checkBox3.isChecked() || checkBox4.isChecked()){
                    // result with all 1
                    int[] results = new int[10];
                    for(int i=0; i<10; i++) results[i] = 1;

                    Intent homeIntent = new Intent(CarerQuestionActivity.this, ChartActivity.class);
                    homeIntent.putExtra("user", "");
                    homeIntent.putExtra("age", age);
                    homeIntent.putExtra("gender", gender);
                    homeIntent.putExtra("results", results);
                    startActivity(homeIntent);
                    finish();
                } else{
                    Intent homeIntent = new Intent(CarerQuestionActivity.this, QuestionActivity.class);
                    homeIntent.putExtra("user", "");
                    homeIntent.putExtra("age", age);
                    homeIntent.putExtra("gender", gender);
                    homeIntent.putExtra("results", new int[10]);
                    homeIntent.putExtra("currentQuestion", 0);
                    startActivity(homeIntent);
                    finish();
                }
            }
        });
    }
}
