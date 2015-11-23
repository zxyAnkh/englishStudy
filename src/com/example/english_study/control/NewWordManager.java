package com.example.english_study.control;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.english_study.model.NewWord;

public class NewWordManager {
	private String PREFS_FILE = "NEW";
	private Context mAppContext;
	private WordDatabaseHelper mHelper;
	private SharedPreferences mPrefs;
	private SQLiteDatabase dbRead;
	private SQLiteDatabase dbWrite;
	private String tableName;
	private NewWord newWord;
	public NewWordManager(Context appContext, String tableName) {
		// TODO Auto-generated constructor stub
		mAppContext = appContext;
		this.mHelper = new WordDatabaseHelper(mAppContext, null);
		mPrefs = mAppContext.getSharedPreferences(PREFS_FILE,
				Context.MODE_PRIVATE);
		this.dbWrite = this.mHelper.getWritableDatabase();
		this.dbRead = this.mHelper.getReadableDatabase();
		this.tableName = tableName;
	}

	public void addToNew(String word) {
		if (word == null)
			return;
		Cursor cursor = null;
		try {
			ContentValues values = new ContentValues();
			values.put("word", word);
			cursor = dbRead.query(tableName, new String[] { "word" },
					"word=?", new String[] { word }, null, null, null);
			// db.query("表名",new String[]{"字段1","字段2"},"条件1"=? and "条件2"=?
			// ,new String[]{"条件1的值,条件2的值"},null,null,null);
			if (cursor.getCount() > 0) {
				dbWrite.update(tableName, values, "word=?",
						new String[] { word });
			} else {
				dbWrite.insert(tableName, null, values);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (cursor != null)
				cursor.close();
		}
	}
	public ArrayList<String> getWordList(){
		ArrayList<String> arrayList = new ArrayList<String>();
		String[] columns = new String[] { "word"};
		String str = null;
		Cursor cursor = dbRead.query(tableName, columns, null, null,
				null, null, null);
		while (cursor.moveToNext()) {
				str = cursor.getString(cursor.getColumnIndex(columns[0]));
			 newWord = new NewWord(str);
			 arrayList.add(newWord.getWord());
		}
		cursor.close();
		return arrayList;
	}
	public void deleteWord(String word){
		if (word == null)
			return;
		dbWrite.delete(tableName, "word=?", new String[]{word});
	}

	public ArrayList<String> getWordListSort() {
		ArrayList<String> arrayList = new ArrayList<String>();
		String[] columns = new String[] { "word"};
		String str = null;
		Cursor cursor = dbRead.query(tableName, columns, null, null,
				null, null, columns[0]);
		while (cursor.moveToNext()) {
				str = cursor.getString(cursor.getColumnIndex(columns[0]));
			 newWord = new NewWord(str);
			 arrayList.add(newWord.getWord());
		}
		cursor.close();
		return arrayList;
	}
}
