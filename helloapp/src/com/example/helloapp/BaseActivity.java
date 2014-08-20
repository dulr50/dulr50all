package com.example.helloapp;

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

        /**
         * 椤甸潰璧峰锛堟敞鎰忥細 姣忎釜Activity涓兘闇�瑕佹坊鍔狅紝濡傛灉鏈夌户鎵跨殑鐖禔ctivity涓凡缁忔坊鍔犱簡璇ヨ皟鐢紝閭ｄ箞瀛怉ctivity涓姟蹇呬笉鑳芥坊鍔狅級
         * 濡傛灉璇ragmentActivity鍖呭惈浜嗗嚑涓叏椤甸潰鐨刦ragment锛岄偅涔堝彲浠ュ湪fragment閲岄潰鍔犲叆灏卞彲浠ヤ簡锛岃繖閲屽彲浠ヤ笉鍔犲叆銆�
         */
//        StatService.onResume(this);
    }

    public void onPause() {
        super.onPause();

        /**
         * 椤甸潰缁撴潫锛堟敞鎰忥細 姣忎釜Activity涓兘闇�瑕佹坊鍔狅紝濡傛灉鏈夌户鎵跨殑鐖禔ctivity涓凡缁忔坊鍔犱簡璇ヨ皟鐢紝閭ｄ箞瀛怉ctivity涓姟蹇呬笉鑳芥坊鍔狅級
         * 濡傛灉璇ragmentActivity鍖呭惈浜嗗嚑涓叏椤甸潰鐨刦ragment锛岄偅涔堝彲浠ュ湪fragment閲岄潰鍔犲叆灏卞彲浠ヤ簡锛岃繖閲屽彲浠ヤ笉鍔犲叆銆�
         */
//        StatService.onPause(this);
    }

}
