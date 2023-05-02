package com.don.demo.basic.numeric;

import org.junit.Test;

import java.math.BigDecimal;

/**
 * todo
 *
 * @author Carl Don
 * @version V1.0
 * @date 2020年02月16日 下午 5:42
 */
public class BigDecimalTest {

    //四则运算
    @Test
    public void test1() {
        BigDecimal a = new BigDecimal("123");
        BigDecimal b = new BigDecimal("456");

        BigDecimal c = a.add(b);// 加
        BigDecimal d = a.subtract(b);// 减
        BigDecimal e = a.multiply(b);// 乘

        // 除
        // 参数2:指定精度,保留6位小数 ; 参数3: 选择舍入模式,此处为 四舍五入
        BigDecimal f = a.divide(b, 6, BigDecimal.ROUND_HALF_UP);

        // jdk 1.9中第三个参数 被RoundingMode取代
        //        BigDecimal f = a.divide(b, RoundingMode.DOWN);//舍弃小数位
        //        BigDecimal f = a.divide(b, 2, RoundingMode.HALF_DOWN);//2位小数;舍入模式为大于0.5进1，否则舍弃。


    }

    //比较大小
    @Test
    public void test2() {
        // 结果 : 1 表示 大于; 0 表示 等于; -1 表示 小于 .
        BigDecimal a1 = new BigDecimal(0.5);
        BigDecimal b1 = new BigDecimal(0.2);
        int c1 = a1.compareTo(b1); // 结果 C = 1
    }

    //正负
    @Test
    public void sign() {
        //返回 1 表示值 为正值 ;  0 表示 为 0 ;  -1 表示 负数 。
        BigDecimal a = new BigDecimal("123");
        int b = a.signum(); // 结果是 1
    }

    //构造器
    @Test
    public void constructTest() {
        //当我们用double类型的数据作为参数时，构造出的BigDecimal 对象value1并不能保证数据的准确性。
        //而用String作为参数时构造对象时，数据的准确性是有保证的。
        BigDecimal value1 = new BigDecimal(10.511);
        System.out.println("value1: " + value1);
        BigDecimal value2 = new BigDecimal("10.511");
        System.out.println("value2: " + value2);

    }


    //不可变
    @Test
    public void immutableTest() {
        BigDecimal count = new BigDecimal("1.3");
        BigDecimal add = count.add(new BigDecimal("9.2"));
        System.out.println("count:" + count);
        System.out.println("add:" + add);

    }
}
