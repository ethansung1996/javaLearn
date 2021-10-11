package com.ethan.streamAPI;

import com.ethan.methodReferences.Employee;
import com.ethan.methodReferences.EmployeeData;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamAPITest {

    @Test
    public void test1(){

    }


    /**
     * 创建stream的方式一：通过集合Collection接口的默认方法创建
     *
     * Java8中的 Collection接口被扩展，新提供了两个获取流的默认方法
     *  1) default Stream<E> stream() : 返回一个顺序流
     *  2) default Stream<E> parallelStream() : 返回一个并行流
     *
     *  注意，只是创建了stream流，并没有进行对数据的操作。
     *  因为流的操作必须是在终止后才进行的。
     */
    @Test
    public void test01(){
        //  1) default Stream<E> stream()
        List<Employee> employees = EmployeeData.getEmployees();
        Stream<Employee> stream = employees.stream();
        //2) default Stream<E> parallelStream()
        Stream<Employee> parallelStream = employees.parallelStream();

    }

    /**
     * 创建stream的方式二：通过Arrays的静态方法stream()可以获取数组流
     *  static <T> Stream<T> stream(T[] array): 返回一个流
     *  重载形式，能够处理对应基本类型的数组：
     *      public static IntStream stream(int[] array)
     *      public static LongStream stream(long[] array)
     *      public static DoubleStream stream(double[] array)
     *  如果是自定义的数组或者不是基本数据类型的数组，那么以泛型的形式生成stream
     *
     *
     */
    @Test
    public void test02(){
        //基本类型数组，生成对应基本类型的stream流
        int[] intArr = new int[]{1,2,3,4,5,6,7};
        IntStream intStream = Arrays.stream(intArr);
        //非基本数据类型数组
        String[] strArr = new String[]{"a","b","c"};
        Stream<String> stream = Arrays.stream(strArr);
        //自定义数据类型数组
        Employee e1 = new Employee(101,"streamL");
        Employee e2 = new Employee(102,"streamM");
        Employee[] empArr = new Employee[]{e1,e2};
        Stream<Employee> employeeStream = Arrays.stream(empArr);

    }

    /**
     * 创建Stream方式三：通过调用Stream类静态方法of(),它可以接收任意数量的参数。
     *  public static<T> Stream<T> of(T... values) : 返回一个流
     *
     */
    @Test
    public void test03(){
        //of参数是对象，所以基本类型会成其包装类
        Stream<Integer> integerStream = Stream.of(1, 2, 3, 4, 5, 6);

        Employee e1 = new Employee(101,"streamL");
        Employee e2 = new Employee(102,"streamM");
        Stream<Employee> stream = Stream.of(e1, e2);

    }

    /**
     * 创建 Stream方式四：使用静态方法Stream.iterate()和Stream.generate(),创建无限流
     *  两种方式:
     *  1）迭代:   public static<T> Stream<T> iterate(final T seed, final UnaryOperator<T> f)
     *  2)生成:   public static<T> Stream<T> generate(Supplier<T> s)
     *
     *
     *  注意：这种情况用的不多。
     */
    static int  id = 10;
    @Test
    public void test04(){
        //迭代的方式示例
        Stream<Integer> iterate = Stream.iterate(0, t -> t + 2);
        iterate.limit(6).forEach(t-> System.out.println(t));
        Stream<String> iterate1 = Stream.iterate("abc", t -> t + "a");
        iterate1.limit(6).forEach(System.out::println);

        //生成的方式示例

        Stream<Employee> generate = Stream.generate(() -> {
            return new Employee(11,"ea");
        });
        generate.limit(10).forEach(System.out::println);
    }








}
