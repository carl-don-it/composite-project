package com.don.demo.basic.mbean;

/**
 * todo
 *
 * @author Carl Don
 * @Date 2021/1/15  10:33
 * @Version 1.0
 */
public interface GameMBean {

    void playFootball(String clubName);

    String getPlayerName();

    void setPlayerName(String playerName);
}
