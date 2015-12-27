package com.example.english_study.activity;

import java.util.Timer;
import java.util.TimerTask;

import com.example.english_study.R;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

public class StartActivity extends ActionBarActivity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.start);
        //自动跳转
        final Intent it = new Intent(this, WelcomeActivity.class);   
        Timer timer = new Timer(); 
        TimerTask task = new TimerTask() {  
            @Override  
            public void run() {   
            startActivity(it); //执行  
            finish();
             } 
         };
        timer.schedule(task, 1000); //1秒后
	}
}
