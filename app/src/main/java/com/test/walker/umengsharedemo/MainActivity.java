package com.test.walker.umengsharedemo;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.test.walker.umengsharedemo.umengshare.ShareBean;
import com.test.walker.umengsharedemo.umengshare.ShareUtils;
import com.umeng.socialize.UMShareAPI;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_share).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShareBean shareBean = new ShareBean();
                shareBean.setTitle("标题");
                shareBean.setSummary("描述。。。。。");
                shareBean.setUrl("http://www.baidu.com");
                shareBean.setIcon_url("http://t2.hddhhn.com/uploads/tu/201901/5/1.jpg");
//                shareBean.setIcon_res(R.drawable.ic_share_wechat);
                new ShareUtils().init(MainActivity.this,shareBean).share();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }
}
