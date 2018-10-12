package com.chinjiaxiong.headevaluator;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

public class ContinueAsGuest extends AppCompatActivity {

    private final String TAG = "testresult";
    private String user = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.continue_as_guest);

        Intent inn = getIntent();
        Bundle b = inn.getExtras();
        user = (String)b.get("user");

        Button continueButton = (Button) findViewById(R.id.button4);
        continueButton.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                int age = Integer.parseInt(((EditText)findViewById(R.id.editText3)).getText().toString());
                String gender = ((EditText)findViewById(R.id.editText6)).getText().toString();
                Intent homeIntent = new Intent(ContinueAsGuest.this, SelectType.class);
                homeIntent.putExtra("user", user);
                homeIntent.putExtra("age", age);
                homeIntent.putExtra("gender", gender);
                startActivity(homeIntent);
                finish();
            }
        });
    }
}
