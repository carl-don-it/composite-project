package com.don.demo.utils;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * 定时播放音乐休息的工具
 *
 * @author Carl Don
 * @version V1.0
 * @date 2020年01月03日 上午 8:14
 */
public class PlayMusic {

    public void playMusic() throws IOException, InterruptedException {
        //1. 歌曲的文件夹地址
        File musicDir = new File("D:\\CloudMusic");


        //2. 歌曲的集合
        ArrayList<String> music = new ArrayList<String>(3000);

        //3. 把文件夹的歌曲添加到集合中
        addMusic(musicDir, music);
        music.trimToSize();

        //4. 定时，每30分钟播放一次歌曲，然后休息3分钟，下一个循环
        int size = music.size();
        Random random = new Random();
        while (true) {
            TimeUnit.MINUTES.sleep(35);
            playRandomMusic(music, size, random);
            TimeUnit.MINUTES.sleep(5);
            playRandomMusic(music, size, random);

        }

    }

    //随机播放音乐asd
    private void playRandomMusic(ArrayList<String> music, int size, Random random) throws IOException {
        int anInt = random.nextInt(size);
        String s = music.get(anInt);
        Process exec = Runtime.getRuntime().exec("cmd /c " + s);
        exec.destroy();
        System.out.println(new Date() + "播放歌曲： " + s);
    }

    private void addMusic(File musicDir, ArrayList<String> music) {
        //是文件,说明是歌曲，那么就添加到集合
        if (musicDir.isFile()) {
            music.add("\"" + musicDir.getAbsolutePath() + "\"");
        } else {
            //是文件夹,迭代自己
            File[] files = musicDir.listFiles();
            //   System.out.println("-------------------"+fileOrDir.getName()+"--------------------");
            for (int j = 0; j < files.length; j++) {
                addMusic(files[j], music);
            }
        }
    }
}
