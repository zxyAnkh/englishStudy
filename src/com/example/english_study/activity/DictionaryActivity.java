package com.example.english_study.activity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

import com.example.english_study.R;
import com.example.english_study.control.*;
import com.example.english_study.control.DictionaryManager.DicAsyncTask;
import com.example.english_study.model.DicWord;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class DictionaryActivity extends ActionBarActivity {
	private ImageButton imgbtn_back;
	private ImageButton imgbtn_search;
	private TextView text_pUK;
	private TextView text_pUS;
	private TextView text_means;
	private ListView lv_examples;
	private EditText edt_word;
	private String word;
	private ArrayAdapter<String> adapter;
	private DicWord dicWord = null;
	private DictionaryManager dm = null;
	private String tableName = "DIC";
	private DicAsyncTask task = null;

	public static final String URL = "http://dict-co.iciba.com/api/dictionary.php?key=B3CF1E57148A7E7AA82FC64C2719EEAD&w=";

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dictionary);
		dm = new DictionaryManager(this, tableName, URL);
		imgbtn_back = (ImageButton) findViewById(R.id.imgbtn_back);
		imgbtn_back.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(DictionaryActivity.this,
						MainActivity.class);
				startActivity(i);
				finish();
			}
		});
		text_pUK = (TextView) findViewById(R.id.text_uk);
		text_pUS = (TextView) findViewById(R.id.text_us);
		edt_word = (EditText) findViewById(R.id.edt_word);
		imgbtn_search = (ImageButton) findViewById(R.id.imgbtn_search);
		text_means = (TextView) findViewById(R.id.text_means);
		lv_examples = (ListView) findViewById(R.id.lv_examples);
		Toast.makeText(getApplicationContext(), "欢迎使用词典功能", Toast.LENGTH_LONG)
				.show();
		imgbtn_search.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// else
				word = edt_word.getText().toString();
				if (word.trim().equals(""))
					Toast.makeText(DictionaryActivity.this, "请输入内容",
							Toast.LENGTH_SHORT);
				else {
					dicWord = dm.search(word);
					try {
						/*
						 * 通过线程进行网络访问获取
						 */
						task = new DicAsyncTask();
						Object params[] = { word, DictionaryManager.mAppContext };
						task.execute(params);
						showResult(dicWord);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}

		});
	}

	public void showResult(DicWord word) throws IOException {
		if (word == null)
			Toast.makeText(this, "查无此词", Toast.LENGTH_SHORT).show();
		else {
			ArrayList<String> arrayList = new ArrayList<String>();
			for (int i = 0; i < word.getExamplesList().size(); i++) {
				arrayList.add(word.getExamplesList().get(i) + "\n"
						+ word.getExamplesMeansList().get(i));
			}
			text_pUK.setText(word.getpUK());
			text_pUS.setText(word.getpUS());
			text_means.setText(word.getMeans());
			adapter = new ExamplesAdapter(this, arrayList);
			lv_examples.setAdapter(adapter);
		}
	}

	private class ExamplesAdapter extends ArrayAdapter<String> {
		// http://blog.csdn.net/yelbosh/article/details/7831812
		private ArrayList<String> example;
		private Context context;

		public ExamplesAdapter(Context context, ArrayList<String> example) {
			super(context, 0, example);
			this.context = context;
			this.example = example;
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return example.size();
		}

		@Override
		public String getItem(int position) {
			// TODO Auto-generated method stub
			return example.get(position);
		}

		@Override
		public long getItemId(int itemId) {
			// TODO Auto-generated method stub
			return itemId;
		}

		public View getView(int position, View convertView, ViewGroup parent) {
			if (convertView == null) {
				convertView = new TextView(context);
			}
			TextView text_lv_examples_item = (TextView) convertView;
			text_lv_examples_item.setText(example.get(position));
			return convertView;

		}
	}
}
