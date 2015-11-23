package com.example.english_study.control;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLEncoder;

import javax.xml.parsers.SAXParserFactory;

import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.example.english_study.activity.DictionaryActivity;
import com.example.english_study.model.DicWord;

public class DictionaryManager {

	private String TAG = "DictionaryManager";
	private String PREFS_FILE = "DIC";
	public static Context mAppContext;
	private WordDatabaseHelper mHelper;
	private SharedPreferences mPrefs;
	private SQLiteDatabase dbRead;
	private SQLiteDatabase dbWrite;
	private String tableName;
	private String url;
	private Cursor explainCursor = null;

	public DictionaryManager(Context appContext, String tableName, String url) {
		// TODO Auto-generated constructor stub
		mAppContext = appContext;
		this.mHelper = new WordDatabaseHelper(mAppContext, null);
		mPrefs = mAppContext.getSharedPreferences(PREFS_FILE,
				Context.MODE_PRIVATE);
		this.dbWrite = this.mHelper.getWritableDatabase();
		this.dbRead = this.mHelper.getReadableDatabase();
		this.tableName = tableName;
		this.url = url;
	}

	private void insertDicWord(DicWord word) {
		if (word == null)
			return;
		Cursor cursor = null;
		try {
			ContentValues values = new ContentValues();
			values.put("word", word.getWord());
			values.put("pUK", word.getpUK());
			values.put("pUS", word.getpUS());
			values.put("means", word.getMeans());
			values.put("examples", word.getExamples());
			values.put("examples_means", word.getExamples_means());
			cursor = dbRead.query(tableName, new String[] { "word" }, "word=?",
					new String[] { word.getWord() }, null, null, null);
			// db.query("表名",new String[]{"字段1","字段2"},"条件1"=? and "条件2"=?
			// ,new String[]{"条件1的值,条件2的值"},null,null,null);
			if (cursor.getCount() > 0) {
				dbWrite.update(tableName, values, "word=?",
						new String[] { word.getWord() });
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

	public DicWord search(String word) {
		test();
		DicWord dicWord = select(word);
		if (dicWord == null) {
			// 网络查词并返回结果
			dicWord=getWord(word,url);
		}
		return dicWord;
	}

	public DicWord select(String word) {
		DicWord dicWord = new DicWord();
		String where = "word=?";
		String[] columns = new String[] { "word", "pUK", "pUS", "means",
				"examples", "examples_means" };
		String[] str = new String[6];
		String[] wherevalue = new String[] { word };
		Cursor cursor = dbRead.query(tableName, columns, where, wherevalue,
				null, null, null);
		while (cursor.moveToNext()) {
			for (int i = 0; i < str.length; i++) {
				str[i] = cursor.getString(cursor.getColumnIndex(columns[i]));

			}
			dicWord = new DicWord(str[0], str[1], str[2], str[3], str[4],
					str[5]);
		}
		cursor.close();
		return dicWord;
	}

	private class XMLParser{
		//http://www.cxyclub.cn/n/54661/
		public SAXParserFactory factory=null;
	    public XMLReader  reader=null;
	    
	    public XMLParser(){
	        
	        try {
	            factory=SAXParserFactory.newInstance();
	            reader=factory.newSAXParser().getXMLReader();
	        } catch (Exception e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	    }
	    
	    public void parseXml(DefaultHandler content, InputSource inSource){
	        if(inSource==null)
	            return;
	        try {
	            reader.setContentHandler(content);
	            reader.parse(inSource);
	        } catch (Exception e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();  
	        } 
	        
	    }
	}
	

	public DicWord getWord(String word,String url) {
		//http://www.cxyclub.cn/n/54661/
		 DicWord dicWord=null;
	        String tempWord=word;
	        if(tempWord==null&& tempWord.equals(""))
	            return null;
	        char[] array=tempWord.toCharArray();
	        if(array[0]>256)           //是中文，或其他语言的的简略判断
	            tempWord="_"+URLEncoder.encode(tempWord);
	        InputStream in=null;
	        String str=null;
	        try{
	            String tempUrl=url+word;
	            Log.d("Word", tempUrl);
	            in=NetOperator.getInputStreamByUrl(tempUrl);   //从网络获得输入流
	            Log.d("Word", "Net");
	            if(in!=null){    
	                XMLParser xmlParser=new XMLParser();
	                InputStreamReader reader=new InputStreamReader(in,"utf-8");        //最终目的获得一个InputSource对象用于传入形参
	                ContentHandler contentHandler=new ContentHandler();
	                xmlParser.parseXml(contentHandler, new InputSource(reader));
	                dicWord=contentHandler.getDicWord();
	                dicWord.setWord(word);
		        	
	            }
	        }catch(Exception e){
	            e.printStackTrace();
	        }
	        return dicWord;
	}

	public void test() {
		DicWord dicWord = new DicWord();
		dicWord.setWord("word");
		dicWord.setpUS("wɜ:rd");
		dicWord.setpUK("wɜ:d");
		dicWord.setMeans("n.单词；话语；诺言；消息\n"+
		"vt.措辞，用词；用言语表达；\n"+
		"vi.讲话；");
		dicWord.setExamples("Using Servers controller to call Word to create templates , which can create Word documents.\n"+
				"This file is on the PDF file format into WORD document format.\n"+
				"We WORD file on a comprehensive test.\n"+
				"Runs on Microsoft Word: You do not have to handle several windows on the screen.\n"+
				"Also, make your resume available in several formats -- text only , Microsoft Word a PDF.");
		dicWord.setExamples_means("用Servers控件调用Word能够较好地实现Delphi对Word的控制.\n"+
				"此文件是关于把PDF文件格式转换成WORD文件格式的.\n"+
				"对WORD档我们进行了全方位的测试.\n"+
				"运行在微软Word中: 你不必在桌面上操作多个窗口.\n"+
				"另外, 要确保你的简历要有几个版本 — 纯文字 、 Word档、PDF档.");
		insertDicWord(dicWord);
	}
	public static class DicAsyncTask extends AsyncTask<Object, Void, DicWord>{

		private String url = DictionaryActivity.URL;
		private DicWord dicWord;
		private StringBuilder mStringBuilder = new StringBuilder();
		protected DicWord doInBackground(Object... params) {
			// TODO Auto-generated method stub
			try{
				String tmp;
		        tmp = ((String) params[0]).trim();
		        if (tmp.isEmpty())
		            Toast.makeText(mAppContext, "请输入单词", Toast.LENGTH_SHORT).show();
		        DictionaryManager dm = new DictionaryManager((Context)params[1], "DIC", null);
		        if (dm.select(tmp).getWord()==""){
		        	Log.d("Word", dm.TAG);
		        	dicWord = dm.getWord(tmp,url);
		        	
		        	dm.insertDicWord(dicWord);
		        }else{
		        	dicWord = dm.select(tmp);
		        }
		        Log.d("Word", dicWord.getExamples());
		        Log.d("Word", dicWord.getMeans());
//		        dicWord.printDicWord();
//		        DictionaryActivity.dicWord = dicWord;
			}catch(Exception e){
				e.printStackTrace();
			}
			return dicWord;
		}
		@Override
		protected void onPostExecute(DicWord result) {
			// TODO Auto-generated method stub
			dicWord = result;
			super.onPostExecute(result);
		}
	}
}
