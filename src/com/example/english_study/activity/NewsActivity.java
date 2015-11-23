package com.example.english_study.activity;

import com.example.english_study.R;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.KeyEvent;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;


public class NewsActivity extends ActionBarActivity{
	private static final String urlNews = "http://www.chinadaily.com.cn/";
	private WebView webView;
	private int keyCode;
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news);
        init();
    }
	private void init(){
		webView = (WebView)findViewById(R.id.wv_news);
		webView.loadUrl(urlNews);
		webView.getSettings().setJavaScriptEnabled(true);
		webView.getSettings().setUseWideViewPort(true);
		webView.getSettings().setLoadWithOverviewMode(true);
		webView.getSettings().setAppCacheEnabled(true);
		webView.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
		webView.setWebViewClient(new WebViewClient(){
			public boolean shouldOverridUrlLoading(WebView view,String url){
				view.loadUrl(urlNews);
				return true;
			}
		});
		
	}
	@Override
	 public boolean onKeyDown(int keyCode, KeyEvent event) {
	  if (keyCode == KeyEvent.KEYCODE_BACK && webView.canGoBack()) {
	   webView.goBack();// 返回前一个页面
	   return true;
	  }
	  return super.onKeyDown(keyCode, event);
	 }
}
