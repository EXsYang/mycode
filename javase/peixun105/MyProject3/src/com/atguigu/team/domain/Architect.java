package com.atguigu.team.domain;

public class Architect extends Designer{
	
	private int stock;//公司奖励的股票数量
	
	public Architect() {
		super();
	}
	public Architect(int id, String name, int age, double salary,double bonus,int stock) {
		super(id, name, age, salary,bonus);
		this.stock = stock;
	}
	public Architect(int id, String name, int age, double salary,double bonus,Equipment equipment,int stock) {
		super(id, name, age, salary,bonus,equipment);
		this.stock = stock;
	}
	
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	@Override
	public String toString() {
		return  getDetails() + getPosition()+ "	" +  getStatus()+ "	"  + getBonus()+"	" + stock +"	" +getEquipmemt().getDescription() ;
	}
	public String List() {
		return super.List()+"	" + stock;
	}
	
	
}
