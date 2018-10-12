package com.chinjiaxiong.headevaluator;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class ReportActivity extends AppCompatActivity implements MyRecyclerViewAdapter.ItemClickListener {

    MyRecyclerViewAdapter adapter;

    String user = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.report_main);

        Intent inn = getIntent();
        Bundle b = inn.getExtras();
        user = (String)b.get("user");
        Log.d("testresult", user);
        // data to populate the RecyclerView with
        ArrayList<String> data = FileHelper.readFile();
        ArrayList<String> realData = new ArrayList<>();
        for(String d: data){
            StringTokenizer st = new StringTokenizer(d, ",");
            Log.d("testresult", d);
            if(st.nextToken().equals(user)) realData.add(0, d);
            Log.d("testresult", realData.toString());
        }

        // set up the RecyclerView
        RecyclerView recyclerView = findViewById(R.id.records);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MyRecyclerViewAdapter(this, realData);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);
//        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
//                new LinearLayoutManager(this).getOrientation());
//        recyclerView.addItemDecoration(dividerItemDecoration);
    }

    @Override
    public void onItemClick(View view, int position) {
        // do nothing
    }
}
