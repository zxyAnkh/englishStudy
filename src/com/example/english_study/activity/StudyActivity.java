package com.example.english_study.activity;

import com.example.english_study.R;
import com.example.english_study.control.StudyManager;
import com.example.english_study.model.CET4;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class StudyActivity extends ActionBarActivity{
	private String TAG = "StudyActivity";
	private TextView text_study;
	private TextView text_sword;
	private TextView text_smeans;
	private Button btn_cstudy;
	private CET4 word = null;
	private String sTableName = null;
	private StudyManager sm =null;
	public int count_Cet4study;
	public static int count_go = 0;
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.study);
		count_go++;
		sTableName = getIntent().getStringExtra(StudyChooseActivity.studyTableName);
		sm = new StudyManager(this,sTableName);
		text_study=(TextView)findViewById(R.id.text_study);
		text_study.setText(sTableName);
		text_sword = (TextView)findViewById(R.id.text_sword);
		text_smeans = (TextView)findViewById(R.id.text_smeans);
		wordWhenCreate();
		btn_cstudy = (Button)findViewById(R.id.btn_cstudy);
		btn_cstudy.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				count_Cet4study ++;
				word.setFlag("true");
				sm.setWordFlag(word);
				Log.d(TAG,word.getFlag());
				word = sm.getWord(count_Cet4study);
				while(word.getFlag()=="true"){
					count_Cet4study++;
					word=sm.getWord(count_Cet4study);
				}
				if(word.getWord()==""){
					Toast.makeText(getApplicationContext(), "恭喜，你已经学完所有单词", Toast.LENGTH_SHORT).show();
					btn_cstudy.setText("Back");
					btn_cstudy.setOnClickListener(new OnClickListener() {
						
						@Override
						public void onClick(View v) {
							// TODO Auto-generated method stub
							Intent i = new Intent(StudyActivity.this,StudyChooseActivity.class);
							startActivity(i);
							finish();
						}
					});
				}
				showResult(word);
			}
		});
	}
	private void showResult(CET4 word){
		text_sword.setText(word.getWord());
		text_smeans.setText(word.getMeans());
	}
	private void wordWhenCreate(){
		count_Cet4study = sm.getStudyedCount();
		Log.d("count",String.valueOf(count_Cet4study));
		word = sm.getWord(count_Cet4study);
		showResult(word);
	}
	
}
