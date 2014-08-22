package com.zhihui.zhihuijiating;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Intent;
import android.view.Window;
import android.widget.ImageView;

public class StartUpActivity extends Activity {

    private final int STARTUP_STAY_TIME = 1000 * 2;
    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        ImageView v = new ImageView(this);
        v.setBackgroundResource(R.drawable.startup);
        setContentView(v);
        gotoMainView();
    }

    void gotoMainView() {
        handler.postDelayed(new Runnable() {

            @Override
            public void run() {

                Intent intent = new Intent();

                intent.setClass(StartUpActivity.this, MainActivity.class);

                startActivity(intent);
                finish();
            }

        }, STARTUP_STAY_TIME);
    }

}
