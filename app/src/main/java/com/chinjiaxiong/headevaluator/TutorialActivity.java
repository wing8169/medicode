package com.chinjiaxiong.headevaluator;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.ramotion.paperonboarding.PaperOnboardingFragment;
import com.ramotion.paperonboarding.PaperOnboardingPage;

import java.util.ArrayList;

public class TutorialActivity extends AppCompatActivity {

    private final String TAG = "testresult";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.tutorial_main);
        // set up for info button
        FloatingActionButton fab = (FloatingActionButton)findViewById(R.id.floatingActionButton3);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        PaperOnboardingPage scr1 = new PaperOnboardingPage("Initiation",
                "Mild head injury can be destructive. Take only about 3 minutes to do this assessment for you or someone you care.",
                Color.parseColor("#678FB4"), R.drawable.tutorial1, R.drawable.tutorial1);
        PaperOnboardingPage scr2 = new PaperOnboardingPage("Assessment",
                "Answer the questionnaire within a few clicks.",
                Color.parseColor("#65B0B4"), R.drawable.tutorial2, R.drawable.tutorial2);
        PaperOnboardingPage scr3 = new PaperOnboardingPage("Report",
                "You will own your own records stored locally. Always refer to keep track of your condition!",
                Color.parseColor("#9B90BC"), R.drawable.tutorial3, R.drawable.tutorial3);

        ArrayList<PaperOnboardingPage> elements = new ArrayList<>();
        elements.add(scr1);
        elements.add(scr2);
        elements.add(scr3);
        PaperOnboardingFragment onBoardingFragment = PaperOnboardingFragment.newInstance(elements);
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = manager.beginTransaction();
        fragmentTransaction.add(R.id.fragment_container, onBoardingFragment);
        fragmentTransaction.commit();
    }
}
