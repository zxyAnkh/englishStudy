package com.example.english_study.control;

import java.io.File;

import com.example.english_study.activity.DictionaryActivity;
import com.example.english_study.activity.MainActivity;
import com.example.english_study.model.DicWord;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.SyncStateContract.Columns;

public class WordDatabaseHelper extends SQLiteOpenHelper{
	private static String DicDB_NAME = "Word.db";
	private static int DicVERSION = 1;
	public WordDatabaseHelper(Context context,CursorFactory factory){
		super(context,DicDB_NAME,factory,DicVERSION);
	}
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL("create table DIC(word text,pUK text," +
				"pUS text,means text,examples text," +
				"examples_means text)");
		db.execSQL("create table NEW(word text)");
		db.execSQL("create table CET4(id text,word text,pUK text," +
				"pUS text,means text,examples text," +
				"examples_means text,flag text)");
	}
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
	}
}
