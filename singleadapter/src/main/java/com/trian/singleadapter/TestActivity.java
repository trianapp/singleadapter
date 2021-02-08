package com.trian.singleadapter;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

public class TestActivity extends AppCompatActivity {
    private RecyclerView rv;
    private SingleAdapter<String> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        rv = findViewById(R.id.rv);
        adapter = new SingleAdapter<>(R.layout.itemtest, onEventClick);


    }

    private onEventClick<String> onEventClick = new onEventClick<String>() {
        @Override
        public void onClick(onEventType eventType, String payload, int position) {

        }
    };
}