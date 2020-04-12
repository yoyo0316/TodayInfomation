package com.yoyozhangh.github.player.player;

import android.content.Context;

import com.yoyozhangh.github.player.tool.DataSourceUtil;

public class PlayerFactory {
    /**
     * 工厂设计模式
     * 可以创建用户想要的播放器
     *
     * @return
     */
    public IPlayer createPlayer(Context context) {
        // 读取配置

        int playType = DataSourceUtil.getMetaDataFromApp(context);
        switch (playType) {
            case IPlayType.MEDIAPLAYERTYPE:
                return new GoogleMediaPlayer();
            case IPlayType.MEDIAPLAYERTYPE_2:
                return new ExoMediaPlayer(context);
            default:
                break;
        }
        return null;
    }
}
