package com.chinjiaxiong.headevaluator;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class SelectType extends AppCompatActivity {

    private final String TAG = "testresult";
    private String user = "";
    private String gender = "";
    private int age = 20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.select_type);

        Button selfButton = (Button) findViewById(R.id.button4);
        selfButton.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent homeIntent = new Intent(SelectType.this,LoginScreen.class);
                homeIntent.putExtra("type", "self");
                startActivity(homeIntent);
                finish();
            }
        });
        Button carerButton = (Button) findViewById(R.id.button5);
        carerButton.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent homeIntent = new Intent(SelectType.this, LoginScreen.class);
                homeIntent.putExtra("type", "carer");
                startActivity(homeIntent);
                finish();
            }
        });
    }
}
