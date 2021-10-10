package com.ethan.lambda;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;

/**
 * Lambda表达式的使用
 *
 * 1.语法格式：
 *  ->:lambda表达式的操作符
 *  ->左边：lambda的形参列表（其实就是接口中抽象方法的形参列表）
 *  ->右边：lambda体，（其实就是接口中抽象方法的具体实现）
 * 2.lambda表达式的使用，有六种情况
 *
 * 3.lambda表达式的本质:是作为对应的函数式接口的实例对象!
 * 所以记住：
 *      1）lambda表达式的返回值都是对应接口的实例对象;
 *      2)lambda表达式的语句是对应接口的方法的具体实现；
 * 4.
 *
 *
 */
public class LambdaTest02 {
    //情况1：无参，没有返回值
    @Test
    public void test01(){
        Runnable run1 = new Runnable() {
            public void run() {
                System.out.println("实现Runnable接口的匿名类对象！");
            }
        };
        run1.run();
        System.out.println("================*==============");
        Runnable run2 = () -> System.out.println("Lambda表达式实现！");
        run2.run();
    }

    //情况2：有一个参数，没有返回值
    @Test
    public void test02(){
        Consumer<String> con1 = new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s+"是什么呢？");
            }
        };
        con1.accept("eth");

        System.out.println("************************");
        //Consumer<String> con2 = (String s) -> System.out.println(s+"是什么呢？");
        Consumer<String> con2 = s -> {
            System.out.println("多条语句呢");
            System.out.println(s+"是什么呢？");
        };
        con2.accept("eth和lambda");
    }

    //情况3：有参数，但是参数的数据类型可以省略，因为编译器可以进行"类型推断"
    @Test
    public void test03(){

        System.out.println("************************");
        Consumer<String> con2 = (s) -> System.out.println(s+"是什么呢？");
        con2.accept("eth和lambda");

        System.out.println("************举例说明类型推断************");
        List<String> list = new ArrayList<>();//类型推断ArrayList<这里无需再写数据类型>

        int[] arr1 = new int[]{1,2,3};//标准
        int[] arr2 = {1,2,3};//进行了类型推断，简化

    }

    //情况4：若是只有一个参数，参数的小括号可以省略
    @Test
    public void test04(){

        Consumer<String> con1 = (s) -> System.out.println(s+"是什么呢？");
        con1.accept("此时有形参的小括号");
        System.out.println("***********只有一个参数，可以去掉形参小括号*************");
        Consumer<String> con2 = s -> System.out.println(s+"是什么呢？");
        con2.accept("此时有形参的小括号");
    }

    //情况5：Lambda 需要两个或以上的参数，多条执行语句，并且可以有返回值
    @Test
    public void test05(){
        Comparator<String> com1 = new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                System.out.println(o1);
                System.out.println(o2);
                return o1.compareTo(o2);
            }
        };
        System.out.println(com1.compare("a","c"));
        System.out.println("************************");
        Comparator<String> com2 = (o1, o2) -> {
            System.out.println(o1);
            System.out.println(o2);
            return o1.compareTo(o2);
        };
        System.out.println(com1.compare("abc","abcdf"));
    }

    //情况6：当 Lambda 体只有一条语句时，return 与大括号若有，都可以省略
    @Test
    public void test06(){
        Comparator<String> com = (o1, o2) -> o1.compareTo(o2);
    }
}

























