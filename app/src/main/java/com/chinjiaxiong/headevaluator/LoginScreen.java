package com.chinjiaxiong.headevaluator;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

public class LoginScreen  extends AppCompatActivity {
    private final String TAG = "testresult";
    private String type = "";

    // Check Permission
    private void checkRecordPermission() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO},
                    123);
        }
    }

    private void checkFilePermission(){
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, 124);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.login_menu);

        Intent inn = getIntent();
        Bundle b = inn.getExtras();
        if(b!=null){
            this.type = (String)b.get("type");
        }

        checkRecordPermission();
        checkFilePermission();
        Button maleButton = (Button) findViewById(R.id.button3);
        maleButton.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String usernameInput = ((EditText)findViewById(R.id.editText)).getText().toString();
                int ageInput = Integer.parseInt(((EditText)findViewById(R.id.editText2)).getText().toString());
                if(type.equals("self")) {
//                    this.user = (String)b.get("user");
//                    this.results = (int[])b.get("results");
//                    this.currentQuestion = (int)b.get("currentQuestion");
//                    this.gender = (String)b.get("gender");
//                    this.age = (int)b.get("age");
                    Intent homeIntent = new Intent(LoginScreen.this, QuestionActivity.class);
                    homeIntent.putExtra("user", usernameInput);
                    homeIntent.putExtra("results", new int[10]);
                    homeIntent.putExtra("currentQuestion", 0);
                    homeIntent.putExtra("gender", "male");
                    homeIntent.putExtra("age", ageInput);
                    startActivity(homeIntent);
                    finish();
                }
                else {
//                    this.user = (String)b.get("user");
//                    this.gender = (String)b.get("gender");
//                    this.age = (int)b.get("age");
                    Intent homeIntent = new Intent(LoginScreen.this, CarerQuestionActivity.class);
                    homeIntent.putExtra("user", usernameInput);
                    homeIntent.putExtra("gender", "male");
                    homeIntent.putExtra("age", ageInput);
                    startActivity(homeIntent);
                    finish();
                }
            }
        });

        // set up for info button
        FloatingActionButton fab = (FloatingActionButton)findViewById(R.id.floatingActionButton2);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent homeIntent = new Intent(LoginScreen.this, TutorialActivity.class);
                startActivity(homeIntent);
            }
        });

        Button femaleButton = (Button) findViewById(R.id.button2);
        femaleButton.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String usernameInput = ((EditText)findViewById(R.id.editText)).getText().toString();
                int ageInput = Integer.parseInt(((EditText)findViewById(R.id.editText2)).getText().toString());
                if(type.equals("self")) {
//                    this.user = (String)b.get("user");
//                    this.results = (int[])b.get("results");
//                    this.currentQuestion = (int)b.get("currentQuestion");
//                    this.gender = (String)b.get("gender");
//                    this.age = (int)b.get("age");
                    Intent homeIntent = new Intent(LoginScreen.this, QuestionActivity.class);
                    homeIntent.putExtra("user", usernameInput);
                    homeIntent.putExtra("results", new int[10]);
                    homeIntent.putExtra("currentQuestion", 0);
                    homeIntent.putExtra("gender", "female");
                    homeIntent.putExtra("age", ageInput);
                    startActivity(homeIntent);
                    finish();
                }
                else {
//                    this.user = (String)b.get("user");
//                    this.gender = (String)b.get("gender");
//                    this.age = (int)b.get("age");
                    Intent homeIntent = new Intent(LoginScreen.this, CarerQuestionActivity.class);
                    homeIntent.putExtra("user", usernameInput);
                    homeIntent.putExtra("gender", "female");
                    homeIntent.putExtra("age", ageInput);
                    startActivity(homeIntent);
                    finish();
                }
            }
        });
    }
}
