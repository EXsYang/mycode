package com.atguigu.exer2;

public class GeometricTest {
public static void main(String[] args) {
	GeometricTest g = new GeometricTest();
	
	g.equalsArea(new Circle(2,"red",0), new MyRectangle(9.0,2.0,"blue",0));
	
	
	g.displayGeometricObject(new Circle(1,"red",0));
	System.out.println(g.displayGeometricObject(new Circle(1,"red",0)));
	
	
}
	

	public void equalsArea(GeometricObject GeometricObject1,GeometricObject GeometricObject2) {
		if(GeometricObject1.findArea() == GeometricObject2.findArea()) {
			System.out.println("相等");
		}else {
			System.out.println("不相等");
		}
		
		
		
	}


	
	
	public double displayGeometricObject(GeometricObject GeometricObject) {
		return GeometricObject.findArea();
		
		
	}
	
	
	
	
	
}
