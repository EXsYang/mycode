package com.atguigu.exer4;

public class Girl {

	private String name;
	private int age;
	
	public Girl() {
		
	}
	public Girl(String name,int age) {
		this.name = name;
		this.age = age;
		
	}
	
	
	
	public void setName(String i) {
		name =i;
	}
	public String getName() {
		return name;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public int getAge() {
		return age;
	}
	public void marry(Boy boy) {//判断年龄合法性
		System.out.println("我想嫁给："+boy.getName());
		boy.marry(this);
	}
	/**
	 * 
	 * @Description	比较两个对象的大小
	 * @author
	 * @date 2021年11月1日下午8:55:59
	 * @param girl
	 * @return 正数当前对象大 ，负数，当前对象小
	 */
	public int compare(Girl girl) {//比较对象的大小,可以是两女孩
//		if(this.age >girl.age) {
//			return 1;
//		}else if(this.age<girl.age) {
//			return -1;
//		}else {
//			return 0;
//		}
//		
		
		return this.age - girl.age;
	}
	
	
	
	
	
}
