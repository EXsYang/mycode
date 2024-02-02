package com.atguigu.exer;

public class CircleTest {
public static void main(String[] args) {
	Circle p = new Circle();
	p.radius = 3;
	double area = p.findArea();
	System.out.print("圆的面积是："+area);
	System.out.println();
	
	Circle c = new Circle();
	PassObject test = new PassObject();
	test.printAreas(c, 6);
	System.out.println("not radius is "+c.radius);
	//或者直接使用匿名对象
	//test.printAreas(new Circle(), 6);
	//System.out.println(c.radius+"   "+c);
	
}
}

class Circle {
	double radius;

	public double findArea() {

		return Math.PI * radius * radius;
	}
}

class PassObject {

	public void printAreas(Circle c, int time) {//引用类型赋值过来的是上面new
		//出来的地址值，所以这里的c调用半径直接将上面新new出来的对象里的半径改了
		System.out.println("Radius\t\tArea");
		
		for(int i = 1;i<=time;i++) {
			//设置圆的半径
			c.radius = i;
			double area = c.findArea();
			System.out.println(c.radius+"\t\t"+area);
			//或者直接System.out.println(c.radius+"\t\t"+c.findArea());
			
		}
		c.radius = time + 1;
		//System.out.println(c);
	}
}
