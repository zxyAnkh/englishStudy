package com.example.english_study.control;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import com.example.english_study.activity.DictionaryActivity;

public class NetOperator {
	//http://www.cxyclub.cn/n/54661/
	private String url = DictionaryActivity.URL;
    
    public static InputStream getInputStreamByUrl(String urlStr){
        InputStream tempInput=null;
        URL url=null;
        HttpURLConnection connection=null;  
        //设置超时时间
             
        try{
            url=new URL(urlStr);
            connection=(HttpURLConnection)url.openConnection(); 
            connection.setConnectTimeout(8000);
            connection.setReadTimeout(10000);
            tempInput=connection.getInputStream();
        }catch(Exception e){
            e.printStackTrace();
        }
        return tempInput;
    }
}
