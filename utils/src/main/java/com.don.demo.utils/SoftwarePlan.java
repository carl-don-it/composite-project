package com.don.demo.utils;

import org.junit.Test;

import java.io.IOException;
import java.util.Calendar;

/**
 * 定时打开某些软件
 *
 * @author Carl Don
 * @version V1.0
 * @date 2020年03月09日 下午 9:14
 */
public class SoftwarePlan {

    @Test
    public void openApp() throws IOException, InterruptedException {

        Thread.sleep(4000);
        //获取时间
        Calendar instance = Calendar.getInstance();
        int DAY_OF_WEEK = instance.get(Calendar.DAY_OF_WEEK);
        int HOUR_OF_DAY = instance.get(Calendar.HOUR_OF_DAY);
        int MINUTE = instance.get(Calendar.MINUTE);

        //判定当前时间是否周一到周五的18.30分前
        if (DAY_OF_WEEK > 1 && DAY_OF_WEEK < 7 && (HOUR_OF_DAY < 18 || (HOUR_OF_DAY == 18 && MINUTE < 30))) {
            String cmd;

            //运行钉钉
            // cmd = "cmd /k start /d \"C:\\Program Files (x86)\\WXWork\" WXWork.exe ";
            // Process exec2 = Runtime.getRuntime().exec(cmd);
            // Thread.sleep(1000);
            // exec2.destroy();

            cmd = "cmd /k start /d \"C:\\WINDOWS\\system32\" mstsc.exe ";
            Process exec3 = Runtime.getRuntime().exec(cmd);
            Thread.sleep(1000);
            // exec3.destroy();


        }

    }
}
