package com.don.demo.concurrent.jut;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * CompletableFutureTest
 *
 * @author Carl Don
 * @Date 2023/3/15  19:47
 * @Version 1.0
 */
public class CompletableFutureTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        long start = System.currentTimeMillis();
        CompletableFuture<String> f1 = CompletableFuture.supplyAsync(() -> {
            try {
                // 模拟耗时操作
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "hello";
        });
        CompletableFuture<String> f2 = CompletableFuture.supplyAsync(() -> {
            try {
                // 模拟耗时操作
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "world";
        });

        CompletableFuture<String> result = f1.thenCombine(f2, (v1, v2) -> v1 + v2);

        result.thenAcceptAsync(s -> {
            try {
                // 模拟耗时操作
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(s +  " cost:"+ (System.currentTimeMillis() - start));
        });

        String s = result.get();
        System.out.println("result:" + s + " cost:"+ (System.currentTimeMillis() - start));

        Thread.sleep(10000);
    }

}
