package com.atguigu.exer1;

public class StaticTest {
	
	
	
	
	public static void main(String[] args) {
		
		StaticTest st = new StaticTest();
		
		Man  m = new Man();
		m.setWidth(20);
		System.out.println(m.getWidth());
		Person p = new Person();
		System.out.println(p.getWidth());//由此可见子类也继承了父类的static修饰的属性
	
		m.print();
		st.order(m);//static方法是属于类的，子类不能覆盖父类的static方法，这里不是重写
		
		
		
		
		
		
		
		
		
	}
	public void order(Person person) {
		person.print();
		
		
		
	}
	
	
}
