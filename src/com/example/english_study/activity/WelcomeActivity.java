package com.example.english_study.activity;

import java.util.Timer;
import java.util.TimerTask;

import com.example.english_study.R;
import com.example.english_study.control.StudyManager;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;


public class WelcomeActivity extends ActionBarActivity {
	private Button btn_continue;
	private TextView text_totalNum;
	public int totalNum=0;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);
        text_totalNum = (TextView)findViewById(R.id.totalNum);
        StudyManager sm = new StudyManager(getApplicationContext(), "CET4");
        totalNum = sm.getStudyedCount();
        text_totalNum.setText(String.valueOf(totalNum));
        //点击跳转
        btn_continue=(Button)findViewById(R.id.btn_continue);
        btn_continue.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(WelcomeActivity.this,MainActivity.class);
				startActivity(i);
				finish();
			}
		});
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
