package com.don.demo.basic.grammar;

/**
 * 没有方法时的field
 * <p>
 * 说明feild 的 继承 、 重写、 多态 行为 （field 没有重写，更没有多态，只有隐藏）
 */
public class InheritFiled {

    static class Father {
        // face没有多态行为
        private String face = "father_face";

        // 子类有访问权限，可以继承直接使用 head ; foot 为 private ，无法继承 就需要super指向父类 ; compound 被重复声明，需要 super指向父类
        private String foot = "father_hand";
        String head = "father_head";
        public String compound = "father_compound";
    }

    static class Son extends Father {
        private String face = "son_face";
        public String compound = head + super.foot + super.compound;
    }

    public static void main(String[] args) {
        Son son = new Son();
        Father father = new Father();
        Father _son = new Son();

        // instance field 没有多态，按照赋值类型取值，与实际类型无关
        System.out.println(son.face);
        System.out.println(father.face);
        System.out.println(_son.face);

        System.out.println();

        // 继承和不继承field
        System.out.println(son.compound);
    }
}
