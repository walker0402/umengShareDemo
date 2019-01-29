package com.test.walker.umengsharedemo.umengshare;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.test.walker.umengsharedemo.R;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;

/**
 * author 刘鉴钊
 * create at 2018/8/14
 * description
 */
public class ShareUtils {

    private ShareDialog shareDialog;

    //目前不知道有没有必要提供回调重写给调用者，如果不需要提供，单利内只需要实例化一次回调，而不用每次都置空又new一次
    private UMShareListener callBack;

    private Context context;


    public ShareUtils init(Activity context, ShareBean bean) {
        this.context = context;
        UMImage image;

        if (0 != bean.getIcon_res()) {
            //如果资源id不0，取资源图片
            image = new UMImage(context, bean.getIcon_res());
        } else if (null != bean.getIcon_bitmap()) {
            //如果bitmap不为null，取bitmap图片
            image = new UMImage(context, bean.getIcon_bitmap());
        } else if (null != bean.getIcon_file()) {
            //如果本地图片不为null，取本地图片
            image = new UMImage(context, bean.getIcon_file());
        } else if (null != bean.getIcon_byte()) {
            //如果byte不为null，取byte图片
            image = new UMImage(context, bean.getIcon_byte());
        } else if (null != bean.getIcon_url()) {
            //如果图片url不为null，取网络图片
            image = new UMImage(context, bean.getIcon_url());
        } else {
            //如果都没有设置，取本地的ic_launcher
            image = new UMImage(context, R.drawable.ic_launcher);
        }

        shareDialog = new ShareDialog(context, share_media -> {
            if (bean.isBigPic()) {
                //如果设置的是大图分享，需要额外设置一个缩略图
                UMImage thumb = new UMImage(context, R.drawable.ic_launcher);
                image.setThumb(thumb);
                image.compressStyle = UMImage.CompressStyle.QUALITY;//质量压缩，适合长图的分享
                new ShareAction(context)
                        .setPlatform(share_media)
                        .withText(bean.getTitle())
                        .withMedia(image)
                        .setCallback(checkCallBack()).share();
            } else {
                //链接分享
                UMWeb web = new UMWeb(bean.getUrl());
                web.setTitle(bean.getTitle());//标题
                web.setDescription(bean.getSummary());//描述
                web.setThumb(image);

                new ShareAction(context)
                        .setPlatform(share_media)
                        .withMedia(web)
                        .setCallback(checkCallBack()).share();
            }

        });
        return this;
    }


    public ShareUtils setCallBack(UMShareListener callBack) {
        this.callBack = callBack;
        return this;
    }

    public void share() {
        shareDialog.show();
    }

    private UMShareListener checkCallBack() {
        if (callBack != null) {
            return callBack;
        } else {
            return new UMShareListener() {
                @Override
                public void onStart(SHARE_MEDIA share_media) {
                    Log.e("walker", "分享开始");
                }

                @Override
                public void onResult(SHARE_MEDIA share_media) {
                    Toast.makeText(context, "分享成功", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onError(SHARE_MEDIA share_media, Throwable throwable) {
                    Log.e("walker", "分享失败");
                    if (throwable.getMessage().contains("2008")) {
                        if (share_media == SHARE_MEDIA.WEIXIN || share_media == SHARE_MEDIA.WEIXIN_CIRCLE) {
                            Toast.makeText(context, "您尚未安装微信,暂无法分享", Toast.LENGTH_SHORT).show();
                        } else if (share_media == SHARE_MEDIA.QQ || share_media == SHARE_MEDIA.QZONE) {
                            Toast.makeText(context, "您尚未安装QQ,暂无法分享", Toast.LENGTH_SHORT).show();
                        }
                    }
                }

                @Override
                public void onCancel(SHARE_MEDIA share_media) {
                    Log.e("walker", "分享取消");
                }
            };
        }
    }
}
