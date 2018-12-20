package com.main;

import java.util.ArrayList;

public class MainApplication {
	public static void main(String[] args){
		ArrayList<Person> list = new ArrayList<>();

		list.add(new Person("jack","123",11));
		list.add(new Person("demo","789",3));
		list.add(new Person("wait","965",145));
		list.add(new Person("ack","665",11));
		list.add(new Person("success","11",5));

		/** ArrayList排序 **/
//		System.out.println("ArrayList 未排序 : " + list);
//		list.sort((o1,o2)->{
//			return o1.getAge() - o2.getAge();
//		});
//		System.out.println("ArrayList 已排序 : " + list);

		/** Array排序 **/
//		Person[] ps = new Person[list.size()];
//		for(int i=0;i<list.size();i++){
//			ps[i] = list.get(i);
//		}
//		System.out.println("Array 未排序 :" + Arrays.toString(ps));
//		Arrays.sort(ps);
//		System.out.println("Array 已排序 : " + Arrays.toString(ps));


	}
}
