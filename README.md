
### 友盟分享
6.9.4版本----------------------------2019-01-07

[sdk下载](https://www.umeng.com/social)

下载下来的包里只有三个平台，QQ，微信，新浪微博，一般也只用到这三个，加上三个友盟的核心库（common一个jar，share两个jar），还有一共有六个jar，放在libs目录下。


包名目录下新疆wxapi文件夹，新建一个名为WXEntryActivity的activity继承WXCallbackActivity。

qq和微博的回调需要再Activity级别的onActivityResult中调用
  UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);

### Android Manifest XML配置
#### 新浪

	   <activity
	        android:name="com.umeng.socialize.media.WBShareCallBackActivity"
	        android:configChanges="keyboardHidden|orientation"
	        android:theme="@android:style/Theme.Translucent.NoTitleBar"
	        android:exported="false"
	        >
	    </activity>
	    <activity android:name="com.sina.weibo.sdk.web.WeiboSdkWebActivity"
	              android:configChanges="keyboardHidden|orientation"
	              android:exported="false"
	              android:windowSoftInputMode="adjustResize"
	    >
	
	    </activity>
	    <activity
	        android:theme="@android:style/Theme.Translucent.NoTitleBar.Fullscreen"
	        android:launchMode="singleTask"
	        android:name="com.sina.weibo.sdk.share.WbShareTransActivity">
	        <intent-filter>
	            <action android:name="com.sina.weibo.sdk.action.ACTION_SDK_REQ_ACTIVITY" />
	            <category android:name="android.intent.category.DEFAULT" />
	        </intent-filter>
	
	    </activity>

#### 微信

	 <activity
	            android:name=".wxapi.WXEntryActivity"
	            android:configChanges="keyboardHidden|orientation|screenSize"
	            android:exported="true"
	            android:theme="@android:style/Theme.Translucent.NoTitleBar" />


#### QQ 注意appid要和项目对应

	     <activity
	        android:name="com.tencent.tauth.AuthActivity"
	        android:launchMode="singleTask"
	        android:noHistory="true" >
	        <intent-filter>
	        <action android:name="android.intent.action.VIEW" />
	
	        <category android:name="android.intent.category.DEFAULT" />
	        <category android:name="android.intent.category.BROWSABLE" />
	
	        <data android:scheme="tencent1108022495" />
	        </intent-filter>
	        </activity>
	        <activity
	        android:name="com.tencent.connect.common.AssistActivity"
	        android:theme="@android:style/Theme.Translucent.NoTitleBar"
	        android:configChanges="orientation|keyboardHidden|screenSize"/>




### 权限
	<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
	<uses-permission android:name="android.permission.ACCESS_WIFI_STATE" />
	<uses-permission android:name="android.permission.INTERNET" />
	<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>   
	<uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE"/>


## 友盟的初始化

### Application的onCreate下

        UMConfigure.init(this,"5a12384aa40fa3551f0001d1"
                ,"umeng",UMConfigure.DEVICE_TYPE_PHONE,"");//5a12384aa40fa3551f0001d1是项目在友盟平台上获取的唯一appkey
