package com.yoyozhangh.github.player.source;

import java.io.File;

public class RawPlayerSource implements IPlayerSource {

    private String url;
    private int rawId;


    // "android.resource://" + getPackageName() + File.separator + R.raw.splash2
    @Override
    public void setUrl(String url) {
        this.url = url;
    }

    public RawPlayerSource setPath(String packageName, int rawId) {
        setUrl("android.resource://" + packageName + File.separator + rawId);
        setResId(rawId);
        return this;
    }

    private void setResId(int rawId) {
        this.rawId = rawId;
    }

    @Override
    public String getUrl() {
        return url;
    }

    @Override
    public int getResId() {
        return rawId;
    }
}
