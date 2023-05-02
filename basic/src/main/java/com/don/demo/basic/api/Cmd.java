package com.don.demo.basic.api;

import org.junit.Test;

import java.io.IOException;

/**
 * todo
 *
 * @author Carl Don
 * @version V1.0
 * @date 2020年02月20日 下午 9:55
 */
public class Cmd {


    @Test
    public void cmd() throws IOException {
        //打开记算器
        String[] arstringCommand = new String[]{"cmd ", "/k", "start", "calc"};// cmd Shell命令
        Process exec1 = Runtime.getRuntime().exec(arstringCommand);
        //关闭
        exec1.destroy();

        //打开记事本
        String cmd = "cmd /k start notepad";
        Process exec2 = Runtime.getRuntime().exec(cmd);
        exec2.destroy();

        //Runtime.getRuntime().exec("cmd /c E:\\zookeeper\\apache-zookeeper-3.5.7-bin - 1\\bin\\zkCli.cmd");//有些cmd命令无法执行
        Process exec = Runtime.getRuntime().exec("cmd /k start  D:\\project\\demo_code\\composite-project\\utils\\target\\classes\\music.bat");
        exec.destroy();
    }

    @Test
    public void cmd2() throws IOException {

        //打开记事本
        String cmd = "cmd  /c rundll32.exe user32.dll,LockWorkStation";
        Process exec2 = Runtime.getRuntime().exec(cmd);
//        exec2.destroy();

    }


}