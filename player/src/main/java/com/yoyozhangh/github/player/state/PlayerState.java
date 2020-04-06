package com.yoyozhangh.github.player.state;

public enum  PlayerState {
    /**
     * 播放器处于未初始化状态
     */
    IDLE,
    /**
     * 正在准备
     */
    PREPARING,

    /**
     * 准备完成，可以开始播放了
     */
    PREPARED,
    /**
     * 播放中
     */
    STARTED,

    /**
     * 暂停状态
     */
    PAUSED,
    /**
     * 停止状态
     */
    STOPPED,

    /**
     * 完成状态
     */
    COMPLETED,
    /**
     * 停止状态
     */
    END,
    /**
     * 错误状态
     */
    ERROR

}
