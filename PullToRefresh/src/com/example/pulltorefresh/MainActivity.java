package com.example.pulltorefresh;

import com.utils.inject.From;
import com.utils.inject.Injector;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener {
	
	@From(R.id.test_listview_btn)
	Button listviewBtn;
	@From(R.id.test_gridview_btn)
	Button gridviewBtn;
	@From(R.id.test_scrollview_btn)
	Button scrollviewBtn;
	
	public static Context context;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		Injector.inject(this);
		init();
		context = this;
	}

	private void init() {
//		listviewBtn = (Button) findViewById(R.id.test_listview_btn);
//		gridviewBtn = (Button) findViewById(R.id.test_gridview_btn);
//		scrollviewBtn = (Button) findViewById(R.id.test_scrollview_btn);

		listviewBtn.setOnClickListener(this);
		gridviewBtn.setOnClickListener(this);
		scrollviewBtn.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		if (v == listviewBtn) {
			Intent intent = new Intent(this, TestListView.class);
			startActivity(intent);
		} else if (v == gridviewBtn) {
			Intent intent = new Intent(this, TestGridView.class);
			startActivity(intent);
		} else if (v == scrollviewBtn) {
			Intent intent = new Intent(this, TestScrollView.class);
			startActivity(intent);
		}
	}
}
