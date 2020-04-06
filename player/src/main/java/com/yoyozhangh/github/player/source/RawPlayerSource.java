package com.yoyozhangh.github.player.source;

import java.io.File;

public class RawPlayerSource implements IPlayerSource {

    private String url;


    // "android.resource://" + getPackageName() + File.separator + R.raw.splash2
    @Override
    public void setUrl(String url) {
        this.url = url;
    }

    public RawPlayerSource setPath(String packageName, int rawId) {
        setUrl("android.resource://" + packageName + File.separator + rawId);
        return this;
    }

    @Override
    public String getUrl() {
        return url;
    }
}
