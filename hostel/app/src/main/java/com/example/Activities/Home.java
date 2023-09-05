package com.example.hoste;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

public class Home extends AppCompatActivity
{
    ImageView bgapp,cloverimg;
    LinearLayout splashtext,hometext,menus;
    Animation frombottom;
    String username;
    LinearLayout register,trackcomplaint,submitfeedback,profileview;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_ACTION_BAR);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        ActionBar actionBar = getSupportActionBar();
        username = getIntent().getStringExtra("name");
        //Toast.makeText(this, "hi"+username, Toast.LENGTH_SHORT).show();
        if (actionBar != null) {
            actionBar.hide();
        }
        setContentView(R.layout.activity_home);
        frombottom= AnimationUtils.loadAnimation(this,R.anim.frombottom);

        bgapp=(ImageView)findViewById(R.id.bgapp);
        cloverimg=(ImageView)findViewById(R.id.cloverimg);
        splashtext=(LinearLayout)findViewById(R.id.splashtext);
        hometext=(LinearLayout)findViewById(R.id.hometext);
        menus=(LinearLayout)findViewById(R.id.menus);


        register=findViewById(R.id.register);
        trackcomplaint=findViewById(R.id.track);
        submitfeedback=findViewById(R.id.submitfeed);
        profileview=findViewById(R.id.viewprofile);

        bgapp.animate().translationY(-1100).setDuration(800).setDuration(300);
        cloverimg.animate().alpha(0).setDuration(800).setStartDelay(500);
        splashtext.animate().translationY(140).alpha(0).setDuration(800).setStartDelay(300);
        hometext.startAnimation(frombottom);
        menus.startAnimation(frombottom);


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Home.this,complaintRegistration.class);
                startActivity(intent);
            }
        });

        trackcomplaint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Home.this,TrackComplaint.class);
                startActivity(intent);
            }
        });


        submitfeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Home.this,Submitfeedback.class);
                startActivity(intent);
            }
        });


        profileview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Home.this,profile.class);
                intent.putExtra("name",username);
                startActivity(intent);
            }
        });
    }
}