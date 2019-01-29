package com.test.walker.umengsharedemo.umengshare;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.test.walker.umengsharedemo.R;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.yanzhenjie.permission.AndPermission;

/**
 * author 刘鉴钊
 * create at 2018/8/13
 * description
 */
public class  ShareDialog extends Dialog implements View.OnClickListener {

    private OnShareClickListener onShareClickListener;

    private Context mContext;

    public ShareDialog(@NonNull Context context, @NonNull OnShareClickListener onShareClickListener) {
        super(context, R.style.AppDialog_Bottom);
        this.onShareClickListener = onShareClickListener;
        this.mContext = context;
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_share, null);
        setContentView(view);

        Window window = getWindow();
        if (window != null) {
            WindowManager.LayoutParams params = window.getAttributes();
            params.width = WindowManager.LayoutParams.MATCH_PARENT;
            params.height = WindowManager.LayoutParams.WRAP_CONTENT;
            params.gravity = Gravity.BOTTOM;
            window.setAttributes(params);
        }
        setCanceledOnTouchOutside(false);
        setClickListener();
    }

    private void setClickListener() {
        findViewById(R.id.iv_wechat).setOnClickListener(this);
        findViewById(R.id.iv_wxcircle).setOnClickListener(this);
        findViewById(R.id.iv_sina).setOnClickListener(this);
        findViewById(R.id.iv_qq).setOnClickListener(this);
        findViewById(R.id.iv_zone).setOnClickListener(this);
        findViewById(R.id.tv_share_cancel).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_wechat:
                onShareClickListener.onClick(SHARE_MEDIA.WEIXIN);
                break;
            case R.id.iv_wxcircle:
                onShareClickListener.onClick(SHARE_MEDIA.WEIXIN_CIRCLE);
                break;
            case R.id.iv_sina:
                onShareClickListener.onClick(SHARE_MEDIA.SINA);
                break;
            case R.id.iv_qq:

                AndPermission.with(mContext).runtime()
                        .permission(com.yanzhenjie.permission.Permission.READ_EXTERNAL_STORAGE,com.yanzhenjie.permission.Permission.WRITE_EXTERNAL_STORAGE)
                        .onGranted(data -> onShareClickListener.onClick(SHARE_MEDIA.QQ))
                        .onDenied(data -> Toast.makeText(mContext,"读写手机@存储权限未开启，请前往系统设置中打开读写手机存储权限开关",Toast.LENGTH_SHORT).show())
                        .start();
//                onShareClickListener.onClick(SHARE_MEDIA.QQ);
                break;
            case R.id.iv_zone:
                AndPermission.with(mContext).runtime()
                        .permission(com.yanzhenjie.permission.Permission.READ_EXTERNAL_STORAGE,com.yanzhenjie.permission.Permission.WRITE_EXTERNAL_STORAGE)
                        .onGranted(data -> onShareClickListener.onClick(SHARE_MEDIA.QZONE))
                        .onDenied(data -> Toast.makeText(mContext,"读写手机@存储权限未开启，请前往系统设置中打开读写手机存储权限开关",Toast.LENGTH_SHORT).show())
                        .start();
//                onShareClickListener.onClick(SHARE_MEDIA.QZONE);
                break;
            case R.id.tv_share_cancel:
                break;
        }
        dismiss();
    }

    public interface OnShareClickListener {
        void onClick(SHARE_MEDIA share_media);
    }
}
