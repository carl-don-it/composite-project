package com.don.demo.basic.api;

import org.junit.Test;

import java.io.IOException;

/**
 * todo
 *
 * @author Carl Don
 * @version V1.0
 * @date 2020年02月19日 下午 5:49
 */
public class ArgsTest {
    //传入参数
    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println(args[0]);
        System.out.println(args[1]);
        System.out.println(args[2]);
    }

    @Test
    public void test1() throws IOException, InterruptedException {

        Thread.sleep(3000);

    }
}
