package com.srssoftwsarelogics.task1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.security.PrivateKey;

public class MainActivity extends AppCompatActivity {
    private ImageView ivAppicon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       ivAppicon = findViewById(R.id.ivappIcon);
       ivAppicon.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               startActivity(new Intent(MainActivity.this, LauncherActivity.class));
           }
       });
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
    }
}