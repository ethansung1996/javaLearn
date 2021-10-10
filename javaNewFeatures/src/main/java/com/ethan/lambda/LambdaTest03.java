package com.ethan.lambda;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 *
 * java内置的四大核心函数式接口
 *      接口名                 核心（唯一抽象）方法的作用
 *  1.Consumer<T>       对类型为T的对象进行相关操作，包含方法:void accept(T t)
 *  2.Supplier<T>       返回类型为T的对象，包含方法: T get()
 *  3.Function<T,R>     对类型为T的对象进行操作，并返回结果。结果是R类型的对象。包含方法:R apply(T t)
 *  4.Predicate<T>      确定类型为T的对象是否满足某约束条件或者说某种判断规则，并返回boolean值。包含方法: boolean test(T t)
 *
 */
public class LambdaTest03 {

    /**
     * 传统的方式和lambda表达式的对比，传递接口的实例并重写其方法
     */
    @Test
    public void test01(){
        //1.方式1：实例一个接口
        Consumer<Double> consumer = new Consumer<Double>() {
            @Override
            public void accept(Double aDouble) {
                System.out.println("方式1：花了" + aDouble + "块钱！");
            }
        };
        happyTime(500,consumer);

        System.out.println("============================");
        //2.方式2：匿名的形式实例化一个接口
        happyTime(500, new Consumer<Double>() {
            @Override
            public void accept(Double aDouble) {
                System.out.println("方式2：花了" + aDouble + "块钱！");
            }
        });

        System.out.println("============================");
        //3.方式3：lambda表达式的形式实例化一个接口
        happyTime(300,aDouble -> System.out.println("方式3：花了" + aDouble + "块钱！"));
    }

    /**
     * 定义一个方法，其中第二个参数需要传递为一个接口的实例
     * @param money
     * @param consumer
     */
    public void happyTime(double money, Consumer<Double> consumer){
        consumer.accept(money);
    }
    /**
     * 传统的方式和lambda表达式的对比，传递接口的实例并重写其方法
     *
     */
    @Test
    public void test02(){

        List<String> destList = Arrays.asList("北京","天津","南京","西京","东京","普京","河南","河北","湖南","广东","湖北");
        //方式一：仍然以传统的方式实现，不赘述
        List<String> strList = filterString(destList, new Predicate<String>() {
            @Override
            public boolean test(String s) {
                return s.contains("京");
            }
        });
        System.out.println(strList);

        System.out.println("====================");
        //方式二：以lambda表达式实现
        List<String> strings = filterString(destList, s -> s.contains("河"));
        System.out.println(strings);
    }

    /**
     * 传递一个字符串集合，根据某种规则过滤字符串集合，此规则由Predicate的test方法决定
     * @param strList
     * @param pre
     * @return
     */
    public List<String> filterString(List<String> strList, Predicate<String> pre){
        List<String> targetList = new ArrayList<>();
        for (String s : strList) {
            if(pre.test(s)) {
                targetList.add(s);
            }
        }

        return targetList;
    }



}
