package com.example.injector;

import com.kituri.inject.InjectData;
import com.kituri.inject.Injector;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class MainActivity extends Activity implements OnClickListener {

	//当注解上"@InjectData"时，该数据类型会在被系统释放时恢复回来。(自动恢复）
	//所以以下代码建议加在BaseActivity类型中
	@InjectData
	private String injectorData;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		findViewById(R.id.tv_click).setOnClickListener(this);
		if (savedInstanceState != null) {
			Injector.onRestore(this, savedInstanceState);
		} else {
			//接受Intent传值等操作
			//getIntent().getExtras()
		}
	}

	
	
	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		//这里是恢复数据的关键
		Injector.save(this, outState);
	}



	@Override
	public void onClick(View v) {
		//给一个对象获得了数据
		injectorData = "new injectorData";
	}
	
}
