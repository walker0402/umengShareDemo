package com.test.walker.umengsharedemo;

import android.app.Application;

import com.sina.weibo.sdk.api.WeiboMessage;
import com.test.walker.umengsharedemo.umengshare.QQConstants;
import com.test.walker.umengsharedemo.umengshare.WeChatConstants;
import com.test.walker.umengsharedemo.umengshare.WeiBoConstants;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareConfig;

/**
 * author 刘鉴钊
 * create at 2019/1/11
 * description
 */
public class App extends Application {

    static {
        PlatformConfig.setQQZone(QQConstants.APP_ID, QQConstants.APP_KEY);
        PlatformConfig.setSinaWeibo(WeiBoConstants.APP_KEY, WeiBoConstants.Secret, WeiBoConstants.REDIRECT_URL);
        PlatformConfig.setWeixin(WeChatConstants.APP_ID, WeChatConstants.APPSECRET);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        UMConfigure.init(this,"5c384c38b465f5580d0012a6","umeng",UMConfigure.DEVICE_TYPE_PHONE,"");//5a12384aa40fa3551f0001d1是项目在友盟平台上获取的唯一appkey
    }
}
