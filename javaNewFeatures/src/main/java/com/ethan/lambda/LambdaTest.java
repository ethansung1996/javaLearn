package com.ethan.lambda;

import org.junit.Test;

import java.util.Comparator;

public class LambdaTest {

    /**
     * 初步了解没有lambda表达式跟有了lambda表达式的区别
     */
    @Test
    public void test01(){
        Runnable r1 = new Runnable() {
            public void run() {
                System.out.println("实现Runnable接口的匿名类对象！");
            }
        };
        r1.run();
        System.out.println("==============================");
        Runnable r2 = () -> System.out.println("Lambda表达式实现！");
        r2.run();
    }

    @Test
    public void test02(){
        Comparator<Integer> comparator1 = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1,o2);
            }
        };
        int compare1 = comparator1.compare(12,22);
        System.out.println(compare1);
        System.out.println("==============================");

        //Lambda表达式的实现方式1:看到->
        Comparator<Integer> comparator2 = (o1,o2) -> Integer.compare(o1,o2);
        int compare2 = comparator1.compare(12,22);
        System.out.println(compare2);
        System.out.println("==============================");

        //方法引用的写法:看到 ::
        Comparator<Integer> comparator3 = Integer :: compare;
        int compare3 = comparator1.compare(12,22);
        System.out.println(compare3);
    }


    public static void main(String[] args) {

    }
}
