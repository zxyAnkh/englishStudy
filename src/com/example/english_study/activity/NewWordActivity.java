package com.example.english_study.activity;

import java.util.ArrayList;

import com.example.english_study.R;
import com.example.english_study.control.NewWordManager;
import com.example.english_study.control.WordDatabaseHelper;
import com.example.english_study.model.DicWord;
import com.example.english_study.model.NewWord;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class NewWordActivity extends ActionBarActivity {
	private Button btn_add;
	private Button btn_del;
	private Button btn_sort;
	private ListView lv_new;
	private EditText edt_new;
	private String word = null;
	private String tableName = "NEW";
	private NewWordManager nm = null;
	private ArrayAdapter<String> adapter;
	public static String lv_word = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.newword);
		
		nm = new NewWordManager(this, tableName);
	//	listNewWord();//用于创建activity时显示list
		btn_add = (Button) findViewById(R.id.btn_add);
		btn_sort = (Button)findViewById(R.id.btn_sort);
		lv_new = (ListView) findViewById(R.id.lv_new);
		edt_new = (EditText) findViewById(R.id.edt_new);
		btn_del = (Button)findViewById(R.id.btn_del);
		btn_add.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				word = edt_new.getText().toString();
				if(word.trim()!="")
					nm.addToNew(word);
				updateNewWordList();
			}
		});
		btn_del.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				word = edt_new.getText().toString();
				nm.deleteWord(word);
				updateNewWordList();
			}
		});
		btn_sort.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				updateNewWordListSort();
			}
		});
		lv_new.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				lv_word = (String)parent.getAdapter().getItem(position);
				
				
				Intent i = new Intent(NewWordActivity.this,NewWordDialog.class);
				i.putExtra(NewWordActivity.lv_word,lv_word);
				startActivityForResult(i,0);
			}
		});
	}
	private void listNewWord() {
		// TODO Auto-generated method stub
	}

	private void updateNewWordListSort() {
		// TODO Auto-generated method stub
		ArrayList<String> arrayList = new ArrayList<String>();
		arrayList=nm.getWordListSort();
		adapter = new NewWordAdapter(this, arrayList);
		lv_new.setAdapter(adapter);
	}
	private void updateNewWordList() {
		// TODO Auto-generated method stub
		ArrayList<String> arrayList = new ArrayList<String>();
		arrayList=nm.getWordList();
		adapter = new NewWordAdapter(this, arrayList);
		lv_new.setAdapter(adapter);
	}


	
	private class NewWordAdapter extends ArrayAdapter<String> {
		// http://blog.csdn.net/yelbosh/article/details/7831812
		private ArrayList<String> newWord;
		private Context context;

		public NewWordAdapter(Context context, ArrayList<String> newWord) {
			super(context, 0, newWord);
			this.context = context;
			this.newWord = newWord;
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return newWord.size();
		}

		@Override
		public String getItem(int position) {
			// TODO Auto-generated method stub
			return newWord.get(position);
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
			text_lv_examples_item.setText(newWord.get(position));
			return convertView;

		}
		
	}
}
