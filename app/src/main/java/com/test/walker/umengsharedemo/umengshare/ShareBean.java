package com.test.walker.umengsharedemo.umengshare;

import android.graphics.Bitmap;

import java.io.File;

/**
 * author 刘鉴钊
 * create at 2018/8/14
 * description
 */
public class ShareBean {
    private String title;

    private String summary;

    private String url;

    /**
     * 资源icon
     */
    private int icon_res;

    /**
     * 网络icon
     */
    private String icon_url;

    /**
     * 本地文件icon
     */
    private File icon_file;

    /**
     * bitmap icon
     */
    private Bitmap icon_bitmap;

    /**
     * bitmap byte[]
     */
    private byte[] icon_byte;

    /**
     * 是否大图分享
     */
    private boolean isBigPic;

    public String getIcon_url() {
        return icon_url;
    }

    public void setIcon_url(String icon_url) {
        this.icon_url = icon_url;
    }

    public File getIcon_file() {
        return icon_file;
    }

    public void setIcon_file(File icon_file) {
        this.icon_file = icon_file;
    }

    public Bitmap getIcon_bitmap() {
        return icon_bitmap;
    }

    public void setIcon_bitmap(Bitmap icon_bitmap) {
        this.icon_bitmap = icon_bitmap;
    }

    public byte[] getIcon_byte() {
        return icon_byte;
    }

    public void setIcon_byte(byte[] icon_byte) {
        this.icon_byte = icon_byte;
    }

    public boolean isBigPic() {
        return isBigPic;
    }

    public void setBigPic(boolean bigPic) {
        isBigPic = bigPic;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getIcon_res() {
        return icon_res;
    }

    public void setIcon_res(int icon_res) {
        this.icon_res = icon_res;
    }
}
