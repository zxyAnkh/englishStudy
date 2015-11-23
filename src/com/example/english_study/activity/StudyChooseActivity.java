package com.example.english_study.activity;

import com.example.english_study.R;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class StudyChooseActivity extends ActionBarActivity{
	private Button btn_cet4;
	private Button btn_cet6;
	public static String studyTableName;
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.study_choose);
        btn_cet4=(Button)findViewById(R.id.btn_cet4);
        btn_cet4.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				studyTableName ="CET4";
				Intent i = new Intent(StudyChooseActivity.this,StudyActivity.class);
				i.putExtra(StudyChooseActivity.studyTableName, "CET4");
				startActivity(i);
			}
		});
        btn_cet6=(Button)findViewById(R.id.btn_cet6);
        btn_cet6.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				studyTableName ="CET6";
				Intent i = new Intent(StudyChooseActivity.this,StudyActivity.class);
				i.putExtra(StudyChooseActivity.studyTableName, "CET6");
				startActivity(i);
			}
		});
    }
}
