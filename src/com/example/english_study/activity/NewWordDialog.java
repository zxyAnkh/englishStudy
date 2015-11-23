package com.example.english_study.activity;

import com.example.english_study.R;
import com.example.english_study.control.NewWordManager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class NewWordDialog extends ActionBarActivity{
	private String TAG="NewWordDialog";
	private TextView text_sy;
	private TextView text_nWord;
	private ListView lv_lj;
	private String word;
	private Button btn_newWord;
	private ArrayAdapter<String> adapter;
	private String tableName = "NEW";
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.newworddialog);
		text_sy = (TextView)findViewById(R.id.text_sy);
		text_nWord=(TextView)findViewById(R.id.text_nWord);
		btn_newWord = (Button)findViewById(R.id.btn_newWord);
		lv_lj = (ListView)findViewById(R.id.lv_lj);
		word = getIntent().getStringExtra(NewWordActivity.lv_word);
		Log.d(TAG, word);
		text_nWord.setText(word);
		btn_newWord.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(NewWordDialog.this,NewWordActivity.class);
				NewWordDialog.this.setResult(RESULT_OK, i);  
				NewWordDialog.this.finish();  
			}
		});
	}
}
