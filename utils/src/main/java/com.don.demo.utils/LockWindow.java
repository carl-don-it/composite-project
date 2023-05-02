package com.don.demo.utils;

import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * 定时锁屏，每隔30分钟
 *
 * @author Carl Don
 * @version V1.0
 * @date 2020年03月09日 下午 9:04
 */
public class LockWindow<T> {

    public static void main(String[] args) throws IOException, InterruptedException {
        new LockWindow().lockWindow();
    }

    @Test
    public void lockWindow() throws InterruptedException, IOException {
        String cmd = "cmd start /k rundll32.exe user32.dll,LockWorkStation";
        //主动输入数字
        Scanner myObj = new Scanner(System.in);
        double min;
        while (true) {
            while (true) {
                try {
                    System.out.println("请输入分钟");
                    min = myObj.nextDouble();
                    if (min != 0.0) {
                        break;
                    }
                } catch (Exception e) {
                    System.out.println("输入有误，请重新输入");
                    String next = myObj.next();
                }
            }
            System.out.println("输入成功，" + min + "分钟后将锁屏");
            TimeUnit.SECONDS.sleep((long) (min * 60));
            Process exec = Runtime.getRuntime().exec(cmd);
            TimeUnit.SECONDS.sleep(30);
            exec.destroy();
        }

    }

    @Test
    public void testGeneric() throws RuntimeException {

        ArrayList<String> strings = new ArrayList<>();
        strings.add("sdfs");
        ArrayList<Integer> sdf = (ArrayList<Integer>) (ArrayList) strings;
        sdf.add(123);
        Integer integer = sdf.get(0);
        System.out.println();
        ArrayList<String>[] arrayLists = (ArrayList<String>[]) new ArrayList<?>[11];
        Object[] qweq = new Integer[11];
        qweq[1] = "sad";
        System.out.println(Arrays.toString(qweq));
    }

    class sdf {

        private boolean ddf;
    }

}
