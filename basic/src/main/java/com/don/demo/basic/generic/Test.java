package com.don.demo.basic.generic;

import com.sun.org.apache.bcel.internal.classfile.LocalVariableTable;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * todo
 *
 * @author Carl Don
 * @Date 2023/4/15  11:39
 * @Version 1.0
 */
public class Test {
    private static List<String> stringList = new ArrayList<>();
    private static List<Integer> integerList = new ArrayList<>();
    public static void main(String[] args) throws Exception {
        //Field它编译过后是还能访问到泛型类型的
        Field stringListField = Test.class.getDeclaredField("stringList");
        ParameterizedType stringListType = (ParameterizedType) stringListField.getGenericType();
        Class<?> stringListClass = (Class<?>) stringListType.getActualTypeArguments()[0];
        System.out.println(stringListClass); // class java.lang.String.
        Field integerListField = Test.class.getDeclaredField("integerList");
        ParameterizedType integerListType = (ParameterizedType) integerListField.getGenericType();
        Class<?> integerListClass = (Class<?>) integerListType.getActualTypeArguments()[0];
        System.out.println(integerListClass); // class java.lang.Integer.


        List<String> asd = new ArrayList<>();
        
        // =====泛型擦除===== 直接从该实例本身去去访问就访问不到喽
        // ===============因为方法入参上的泛型，在运行期都是不会起作用的。这就是为何List<Integer>里面能装Long值的原因===============
        Class<? extends List> stringClazz = stringList.getClass();
        Class<? extends List> integerClazz = integerList.getClass();
        System.out.println(stringClazz); // class java.util.ArrayList
        System.out.println(integerClazz); // class java.util.ArrayList
        System.out.println(stringClazz == integerClazz); // true
        // 获得带有泛型的父类
        ParameterizedType parametclass = (ParameterizedType) stringList.getClass().getGenericSuperclass();
        Type[] actualTypeArguments = parametclass.getActualTypeArguments();
        System.out.println(actualTypeArguments[0]); // E
        System.out.println(actualTypeArguments[0].getTypeName()); // E
        System.out.println(actualTypeArguments[0].getClass()); // class sun.reflect.generics.reflectiveObjects.TypeVariableImpl
    }
    // 继承，获取泛型的实际类型
    // class Parent<S,T> {}
    // class Child extends Parent<String,Integer> {}
    // public static void main(String[] args) {
    //     // 获取 Child的父类 --- Parent
    //     Type superClass = Child.class.getGenericSuperclass();
    //     if( superClass instanceof ParameterizedType ){
    //         ParameterizedType p = (ParameterizedType)superClass;
    //         Type rawType = p.getRawType(); // 父类实际类型
    //         Type[] actualTypes = p.getActualTypeArguments(); //父类泛型实际类型
    //         // 父类类型： class com.youtbatman.java.Test$Parent 父类泛型实际类型：[class java.lang.String, class java.lang.Integer]
    //         System.out.println("父类类型： "+ rawType.toString() + " 父类泛型实际类型："+ Arrays.toString(actualTypes));
    //     }
    // }
}
