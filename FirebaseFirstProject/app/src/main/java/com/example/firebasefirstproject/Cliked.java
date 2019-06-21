package com.example.firebasefirstproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Cliked extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliked);
        Intent intent=getIntent();
        String text=intent.getStringExtra("RecyclerText");
        TextView t=(TextView) findViewById(R.id.itemcliked);
        t.setText(text);
    }
}
