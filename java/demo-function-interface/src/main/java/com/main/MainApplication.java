package com.main;

import java.util.ArrayList;
import java.util.function.Predicate;

public class MainApplication {
	public static ArrayList<String> list = new ArrayList();

	static {
		list.add("Jack");
		list.add("Jackson");
		list.add("Rick");
		list.add("Mark");
		list.add("Mcrooge");
		list.add("Peter");
		list.add("Jeff");
	}

	public void tudoTask(MyTask task){
		task.doSome();
	}

	public static void main(String[] args){
		//lambda使用
		new MainApplication().tudoTask(()-> System.out.println("Hello"));


		//根据条件删除元素
		list.removeIf(e->e.indexOf("R") !=-1);
		System.out.println(list);

		//使用Foreach
		list.forEach((name)->System.out.println("Your Name : " + name));
		//使用双冒号,等价上面的
		list.forEach(System.out::println);


		//使用lambda来赋值
		Runnable run = () ->System.out.println("Im Runnable");

		testStreamAndLambda();

	}



	/**
	 * lambda和stream的使用
	 */
	public static void testStreamAndLambda(){
		Predicate<String> predicate = p->p.indexOf("J")!=-1;
		//过滤(开头有J的名字)
		list.stream().filter(predicate).forEach(System.out::println);
		//输出过滤后的总数
		System.out.println(list.stream().filter(p->p.indexOf("M")!=-1).count());
		//分页
		list.stream().filter(predicate).limit(1).forEach(System.out::println);

	}
}
