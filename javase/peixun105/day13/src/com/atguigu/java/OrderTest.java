package com.atguigu.java;

public class OrderTest {

	public static void main(String[] args) {
		
		OrderTest O = new OrderTest();
		OrderTest.Order1 o= new OrderTest.Order1();//static内部类实例化
		OrderTest.Order o1 = O.new Order(22,"AA");//非static内部类实例化
		OrderTest.Order o2 = O.new Order(22,"AA");
		OrderTest.Order o3 = O.new Order(22,new String("AA"));
		System.out.println(o1.equals(o2));//这里想要的是比较实体内容，想要的是true,在==的情况下，String是引用类型
		//==时比较的是地址值，按常理这里应该是false,结果这里是true,原因是String类型的存放在方法区的常量池当中，与上一个定义的
		//内容相同时，直接复用，地址值相同
		
		System.out.println(o1.equals(o3));//这里是新new出来的，地址值不同，是false
		
	}
	
	static class Order1{
		
	}
		  
	class Order{
		int age;
		String name;
		public Order(int age,String name){
			this.age = age;
			this.name = name;
		}
		  public boolean equals(Object obj) {
			  if(this == obj) {
				  return true;
			  }
			  if(obj instanceof Order) {
				  Order o = (Order)obj;
//				  if(this.age == o.age && this.name == o.name) {
//					  return true;
//				  }
				  if(this.age == o.age && this.name.equals(o.name)) {//String类重写过equals,所以这里是比较实体内容
					  return true;
				  }
			  }
			  return false;
	}
	
	}
	
	
	
	
	
	
	
	
	
	
}
