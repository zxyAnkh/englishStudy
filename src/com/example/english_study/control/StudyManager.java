package com.example.english_study.control;

import com.example.english_study.activity.StudyActivity;
import com.example.english_study.model.CET4;
import com.example.english_study.model.DicWord;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class StudyManager {
	private String TAG = "StudyManager";
	private String PREFS_FILE = "CET4";
	public static Context mAppContext;
	private WordDatabaseHelper mHelper;
	private SharedPreferences mPrefs;
	private SQLiteDatabase dbRead;
	private SQLiteDatabase dbWrite;
	private String tableName;
	private String[] words = new String[] { "test", "word", "hello", "go",
			"study" };
	private String[] means = new String[] { "测试", "单词", "打招呼", "走", "学习" };

	public StudyManager(Context appContext, String tableName) {
		mAppContext = appContext;
		this.mHelper = new WordDatabaseHelper(mAppContext, null);
		mPrefs = mAppContext.getSharedPreferences(PREFS_FILE,
				Context.MODE_PRIVATE);
		this.dbWrite = this.mHelper.getWritableDatabase();
		this.dbRead = this.mHelper.getReadableDatabase();
		this.tableName = tableName;
		if(StudyActivity.count_go==1)
		    createCet4();
	}

	public CET4 getWord(int id) {
		CET4 word = new CET4();
		String where = "word=?";
		String[] columns = new String[] { "id", "word", "means", "flag" };
		String[] str = new String[4];
		Cursor cursor = null;
		String sid = String.valueOf(id);
		cursor = dbRead.query(tableName, new String[] { "id", "word", "means",
				"flag" }, "id=? and flag=?", new String[] { sid,"false" }, null, null, null);
		while (cursor.moveToNext()) {
			for (int i = 0; i < str.length; i++) {
				str[i] = cursor.getString(cursor.getColumnIndex(columns[i]));
			}
			word = new CET4(str[0], str[1], str[2], str[3]);
		}
		return word;
	}

	public int getStudyedCount() {
		// TODO Auto-generated method stub
		int count = 0;
		Cursor cursor = null;
		cursor = dbRead.query(tableName, new String[] { "flag" }, "flag = ?",
				new String[] { "true" }, null, null, null);
		if (cursor.getCount() > 0){
			count = cursor.getCount();
			Log.d(TAG, String.valueOf(count));
		}
		else
			count = 0;
		cursor.close();
		return count;
	}

	private void createCet4() {
		for (int i = 0; i < words.length; i++) {
			Cursor cursor = null;
			try {
				ContentValues values = new ContentValues();
				values.put("id", String.valueOf(i));
				values.put("word", words[i]);
				values.put("means", means[i]);
				values.put("flag", "false");
				cursor = dbRead.query(tableName, new String[] { "word" },
						"word=?", new String[] { words[i] }, null, null, null);
				// db.query("表名",new String[]{"字段1","字段2"},"条件1"=? and "条件2"=?
				// ,new String[]{"条件1的值,条件2的值"},null,null,null);
				if (cursor.getCount() > 0) {
					dbWrite.update(tableName, values, "word=?",
							new String[] { words[i] });
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
	}

	public void setWordFlag(CET4 word) {
		// TODO Auto-generated method stub
		Cursor cursor = null;
		ContentValues values = new ContentValues();
		values.put("flag", word.getFlag());
		String id = word.getId();
		cursor = dbRead.query(tableName, new String[] { "id" ,"flag"}, "id=?",
				new String[] { id }, null, null, null);
		if (cursor.getCount() > 0) {
			dbWrite.update(tableName, values, "word=? and flag=?",
					new String[] { word.getWord(),"false" });
		} else {
			dbWrite.insert(tableName, null, values);
		}
		Log.d(TAG,word.getWord()+" "+word.getFlag());
		cursor.close();
	}
}
