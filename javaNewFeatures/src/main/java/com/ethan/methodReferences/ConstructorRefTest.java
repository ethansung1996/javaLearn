package com.ethan.methodReferences;

import org.junit.Test;

import java.util.Arrays;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * 一、构造器引用
 *      和方法引用类似，函数式接口的抽象方法的形参列表和构造器的形参列表一致。即对应抽象方法形参列表的构造器必须存在。
 *      抽象方法的返回值类型即为构造器所属类的类型
 *
 * 二、数组引用
 *      将数组看作一个特殊的类，即与构造器引用类型了。
 *
 */
public class ConstructorRefTest {


    /**
     * 构造器引用
     * Supplier中的T get()
     * Employee中的 new Employee();
     */
    @Test
    public void test1(){
        //最基础的方式
        Supplier<Employee> sup1 = new Supplier<Employee>() {
            @Override
            public Employee get() {
                return new Employee();
            }
        };
        System.out.println(sup1.get());

        System.out.println("========================");
        //lambda表达式的方式
        Supplier<Employee> sup2 = ()-> new Employee();
        System.out.println(sup2.get());

        System.out.println("========================");

        //构造器引用的方式
        Supplier<Employee> sup3 = Employee::new;
        System.out.println(sup3.get());

	}

	//Function中的R apply(T t)
    @Test
    public void test2(){
        Function<Integer,Employee> fun1 = id-> new Employee(id);
        System.out.println(fun1.apply(1001));
        System.out.println("========================");
        Function<Integer,Employee> fun2 = Employee::new;
        System.out.println(fun1.apply(1002));

	}

	//BiFunction中的R apply(T t,U u)
    @Test
    public void test3(){
        BiFunction<Integer,String,Employee> bf1 = (id,name)->new Employee(id,name);
        System.out.println(bf1.apply(1001,"ethan"));

        System.out.println("=====================");
        BiFunction<Integer,String,Employee> bf2 = Employee::new;
        System.out.println(bf2.apply(1002,"ethal"));
	}

	//数组引用
    //Function中的R apply(T t)
    @Test
    public void test4(){
        Function<Integer,String[]> fun1 = len -> new String[len];
        String[] strArr1 = fun1.apply(5);
        System.out.println(Arrays.toString(strArr1));
        System.out.println("=====================");
        Function<Integer,String[]> fun2 = String[] ::new;
        String[] strArr2 = fun2.apply(10);
        System.out.println(Arrays.toString(strArr2));

	}
}
