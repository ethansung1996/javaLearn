package com.ethan.methodReferences;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.junit.Test;

import java.util.Comparator;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * 方法引用的使用
 *	1.方法引用的使用情景：
 *		当发现需要实现Lambda体的操作（即我们要写的lambda表达式），
 *		其他的接口（java8开始接口可以有默认实现的方法了）或者类已经有实现相同功能的方法
 *		即某个方法的参数类型和参数个数，返回值类型以及方法体都跟我们要写的lambda表达式一致（适用于以下情况1和2）
 *		此时就可以使用方法引用！！！其他情况下目前不能使用。
 *
 *	2.方法引用的理解：
 *		本质上就是lambda表达式，而lambda表达式就是作为函数式接口的一个实例对象，所以方法引用，也是函数式接口的实例对象。
 *		可以将方法引用理解为在我们需要实现lambda表达式时候，
 *		将已经存在的其他类的某个方法（注意这个方法的功能与我们要实现的lambda表达式的功能一致）调用过来（拿来主义），替代我们要写的lambda表达式
 *		可以这样认为：在函数（方法）的层面上消除冗余重复的方法体
 *		JDK在Lambda表达式的基础上又提出了方法引用的概念，允许我们复用当前项目（或JDK源码）中已经存在的且逻辑相同的方法。
 *		即，如果已经存在某个方法能完成你的需求，那么你连Lambda表达式都别写了，直接引用这个方法吧。
 *
 *	3.方法引用的格式：类或者对象  :: 方法名
 *		具体分为以下三种情况：
 *  		1）对象 :: 非静态方法
 *  		2）类 :: 静态方法
 *  		3）类 :: 非静态方法
 *
 *  4.使用方法引用的前提限制条件：
 *		1）在能够使用lambda表达式的前提下
 *		2）某个方法的参数类型和参数个数，返回值类型以及方法体都跟我们要写的lambda表达式一致（适用于情况1和2）
 *
 */
public class MethodRefTest {
	/**
	 * 情况一：对象 :: 实例方法
	 * 我们知道Consumer是函数式接口，有唯一一个抽象方法void accept(T t)
	 * 我们又发现PrintStream中的void println(T t)方法，不论是返回值类型，形参类型和形参个数
	 * 都跟Consumer接口的void accept(T t)方法一样，唯一不同的是方法名不一样，并且假如我们要实现void accept(T t)方法
	 * 如果实现的功能（方法体）又跟PrintStream中的void println(T t)方法实现的一样，那么此时我们就可以使用方法引用
	 * 在函数（方法）层面去减少相同功能方法的重复书写，直接调用PrintStream中的void println(T t)方法来代替我们要实现void accept(T t)方法
	 * 对于一个方法的完整定义来说，这两个方法的方法名不一样无关大雅，但是其他的定义都完全一致，实现的功能完全一致。所以可以替换。
	 * 这就是方法引用的意义，从函数方法层面去消除重复代码，实现相同功能。
	 */
	@Test
	public void test1() {
		//lambda表达式
		Consumer<String> con1 = s -> System.out.println(s);
		con1.accept("北京");
		System.out.println("======================");
		//方法引用
		Consumer<String> con2 = System.out::println;
		con2.accept("beijing");
		
	}
	


	/**
	 * 	Supplier中的T get()
	 * 	Employee中的String getName()
	 */
	@Test
	public void test2() {
		Employee emp = new Employee(11,"ETHAN",22,30000);
		Supplier<String> sup1 = ()-> emp.getName();
		System.out.println(sup1.get());
		System.out.println("======================");
		//方法引用
		Supplier<String> sup2 = emp::getName;
		System.out.println(sup2.get());

	}



	/**
	 *  情况二：类 :: 静态方法
	 * 	Comparator中的int compare(T t1,T t2)
	 * 	Integer中的int compare(T t1,T t2)
	 */
	@Test
	public void test3() {

		Comparator<Integer> com1 = (t1, t2)-> Integer.compare(t1,t2);
		System.out.println(com1.compare(12, 22));
		System.out.println("========================");

		/**
		 * 这里有个疑惑，我们说方法引用的条件是接口的抽象方法的返回值跟形参类型和个数都要一致，
		 * 但是尝试Comparator接口的int compare(T t1,T t2)方法，引用Integer的compareTo方法仍然成功
		 * 发现Integer的compareTo调用的仍然是Integer的compare方法
		 * 我这里猜测保持接口的抽象方法的返回值跟形参类型和个数都要一致的条件不是完全限制死的，毕竟本质上是lambda表达式，更看重方法体实现。
		 */

		//Comparator<Integer> com2 = Integer ::compareTo;
		Comparator<Integer> com2 = Integer ::compare;
		System.out.println(com2.compare(13,111));
	}
	


	/**
	 * 	Function中的R apply(T t)
	 * 	Math中的Long round(Double d)
	 */
	@Test
	public void test4() {
		Function<Double,Long> fun1 = aDouble -> Math.round(aDouble);
		System.out.println(fun1.apply(12.3));
		System.out.println("========================");
		Function<Double,Long> fun2 = Math::round;
		System.out.println(fun2.apply(12.6));
	}



	/**
	 *  情况三：类 :: 实例方法
	 *  这种情况比较少见，一般其实自己写lambda表达式就挺好的。
	 *
	 * 	 Comparator中的int compare(T t1,T t2)
	 * 	 String中的int t1.compareTo(t2)
	 * 	 上面跟我们一开始理解的方法引用的适用条件：
	 * 	 即某个方法的参数类型和参数个数，返回值类型以及方法体都跟我们要写的lambda表达式一致（适用于以下情况1和2）
	 * 	 不一致！！！如何理解：
	 * 	 首先我们要有一个概念，非静态方法的形参其实是隐含了一个形参变量，那就是我们这个非静态方法的实例对象this
	 * 	 所以Comparator中的int comapre(T t1,T t2)可以将String中的int t1.compareTo(t2)进行方法引用，就是因为
	 * 	 String中的int t1.compareTo(t2)方法其实隐含了一个形参this（其实就是t1）,
	 * 	 那么加上的this就符合方法引用的条件，
	 * 	 Comparator中的int compare(T t1,T t2)
	 * 	 String中的int t1.compareTo(this(t1),t2)(this其实也就是Comparator中的int comapre(T t1,T t2)的t1形参。
	 *
	 *
	 * 	 这种情况下，可以这样理解：
	 * 	 方法引用的方法的调用者t1，其实就是函数式接口的抽象方法中的第一个形参。
	 */
	@Test
	public void test5() {


		Comparator<String> c1 = (s1,s2)-> s1.compareTo(s2);
		System.out.println(c1.compare("abr","abc"));

		System.out.println("-==============================");
		Comparator<String> c2 = String::compareTo;
		System.out.println(c2.compare("abr","abc"));
	}



	/**
	 *
	 * 	BiPredicate中的boolean test(T t1, T t2);
	 * 	String中的boolean t1.equals(t2)
	 */
	@Test
	public void test6() {
		BiPredicate<String,String> bp1 = (s1,s2)->s1.equals(s2);
		System.out.println(bp1.test("aka","aba"));
		System.out.println("==============================");
		BiPredicate<String,String> bp2 = String::equals;
		System.out.println(bp2.test("aka","aka"));
	}
	


	/**
	 * 	 Function中的R apply(T t)
	 * 	 Employee中的String getName();
	 *
	 * 	 变形理解：
	 * 	 Function中的R apply(T t)	======> 							Function中的R apply(T t);
	 * 	 Employee中的emp.getName() 	===getName隐藏了一个形参this(emp)==> 	Employee中的String getName(Employee emp);
	 */
	@Test
	public void test7() {
		Employee emp = new Employee(11,"ETHAN",22,30000);
		Function<Employee,String> fun1 = employee -> employee.getName();
		System.out.println(fun1.apply(emp));
		System.out.println("==============================");
		Function<Employee,String> fun2 = Employee::getName;
		System.out.println(fun2.apply(emp));
	}

}
