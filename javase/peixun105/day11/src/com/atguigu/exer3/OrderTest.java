package com.atguigu.exer3;

public class OrderTest {
	public static void main(String[] args) {
		
		Order o = new Order(23,"yangda");
		Order o1 = new Order(23,"yangda");
		System.out.println(o.equals(o1));
		
	}
	
	
}

class Order {
	int orderId;
	String orderName;

	public Order(int orderId,String orderName) {
		this.orderId = orderId;
		this.orderName = orderName;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public String getOrderName() {
		return orderName;
	}

	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}

//	public boolean equals(Object obj) {
//		if(this == obj) {
//			return true;
//		}
//		 if(obj instanceof Order){	
//			 Order o = (Order)obj;
////			 if(this.orderId == o.orderId && this.orderName.equals(o.orderName){
////				 return true;
////			 }else {
////				 return false;
//			 return this.orderId == o.orderId && this.orderName.equals(o.orderName);
//			 }else {
//				 return false;
//			 
//		}
//	}

}