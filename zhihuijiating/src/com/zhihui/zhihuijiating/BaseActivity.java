package com.zhihui.zhihuijiating;

import com.utils.inject.Injector;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;



public class BaseActivity extends Activity {

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        Injector.inject(this);
    }

    @Override
    public void setContentView(View view) {
        super.setContentView(view);
        Injector.inject(this);
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        super.setContentView(view, params);
        Injector.inject(this);
    }

    public void onResume() {
        super.onResume();

    }

    public void onPause() {
        super.onPause();

    }

}
