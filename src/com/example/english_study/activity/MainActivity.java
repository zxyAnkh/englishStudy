package com.example.english_study.activity;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.BDNotifyListener;//假如用到位置提醒功能，需要import该类
import com.baidu.location.LocationClientOption.LocationMode;
import com.baidu.location.Poi;
import com.example.english_study.R;
import com.example.english_study.control.MyLocationListener;
import com.example.english_study.control.WordDatabaseHelper;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;



/*相关百度地图API信息
 * http://developer.baidu.com/map/index.php?title=android-locsdk/guide/v5-0

*/
public class MainActivity extends ActionBarActivity {
	private Button btn_study;
	private Button btn_dic;
	private Button btn_news;
	private Button btn_new;
	private Button btn_sta;
	private TextView text_places;
	private TextView text_placess;
	
//	private LocationClient mLocationClient = null;
//	private BDLocationListener myListener = null;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
//		myListener = new MyLocationListener();
//		mLocationClient = new LocationClient(getApplicationContext()); // 声明LocationClient类
//		mLocationClient.registerLocationListener(myListener); // 注册监听函数
//		mLocationClient.start();
//		initLocation();
//		mLocationClient.requestLocation();
//		text_places = (TextView)findViewById(R.id.text_places);
//		text_places.setText(mLocationClient.getLastKnownLocation().getAddrStr());
//		text_placess = (TextView)findViewById(R.id.text_placess);
//		text_placess.setText(mLocationClient.getLastKnownLocation().getCity()+mLocationClient.getLastKnownLocation().getCountry());
		
		btn_study = (Button) findViewById(R.id.btn_study);
		btn_study.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(MainActivity.this,
						StudyChooseActivity.class);
				startActivity(i);
			}

		});
		btn_dic = (Button) findViewById(R.id.btn_dictionary);
		btn_dic.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(MainActivity.this,
						DictionaryActivity.class);
				startActivity(i);
			}

		});
		btn_news = (Button) findViewById(R.id.btn_news);
		btn_news.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(MainActivity.this, NewsActivity.class);
				startActivity(i);
			}

		});
		btn_new = (Button) findViewById(R.id.btn_new);
		btn_new.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(MainActivity.this, NewWordActivity.class);
				startActivity(i);
			}

		});
		btn_sta = (Button)findViewById(R.id.btn_statistics);
		btn_sta.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(MainActivity.this,StatisticsActivity.class);
				startActivity(i);
			}
		});
	}

//	private void initLocation() {
//		LocationClientOption option = new LocationClientOption();
//		option.setLocationMode(LocationMode.Hight_Accuracy);// 可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
//		option.setCoorType("bd09ll");// 可选，默认gcj02，设置返回的定位结果坐标系
//		int span = 1000;
//		option.setScanSpan(span);// 可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的
//		option.setIsNeedAddress(true);// 可选，设置是否需要地址信息，默认不需要
//		option.setOpenGps(true);// 可选，默认false,设置是否使用gps
//		option.setLocationNotify(true);// 可选，默认false，设置是否当gps有效时按照1S1次频率输出GPS结果
//		option.setIsNeedLocationDescribe(true);// 可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
//		option.setIsNeedLocationPoiList(true);// 可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
//		option.setIgnoreKillProcess(false);// 可选，默认false，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认杀死
//		option.SetIgnoreCacheException(false);// 可选，默认false，设置是否收集CRASH信息，默认收集
//		option.setEnableSimulateGps(false);// 可选，默认false，设置是否需要过滤gps仿真结果，默认需要
//		mLocationClient.setLocOption(option);
//	}
}
