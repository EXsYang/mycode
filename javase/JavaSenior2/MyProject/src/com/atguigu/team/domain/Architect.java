package com.atguigu.team.domain;

public class Architect extends Designer {
	 	private int stock;//表示公司奖励的股票数量


	 	 //Employee  :  10, id, name, age, salary
	    //Programmer:  11, id, name, age, salary
	    //Designer  :  12, id, name, age, salary, bonus
	    //Architect :  13, id, name, age, salary, bonus, stock
		public Architect() {
			super();
		}

		public Architect(double bonus) {
			super(bonus);
			// TODO Auto-generated constructor stub
		}
		public Architect(int id, String name, int age, double salary, Equipment equipment, double bonus,int stock) {
			super(id, name, age, salary, equipment, bonus);
			this.stock = stock;
			// TODO Auto-generated constructor stub
		}
		public Architect(int id, String name, int age, double salary, double bonus,int stock) {
			super(id, name, age, salary, bonus);
			this.stock = stock;
			// TODO Auto-generated constructor stub
		}

		public Architect(int id, String name, int age, double salary) {
			super(id, name, age, salary);
			// TODO Auto-generated constructor stub
		}

		public int getStock() {
			return stock;
		}

		public void setStock(int stock) {
			this.stock = stock;
		}

		@Override
		public String toString() {
			return getDesc() +  "\t" + "架构师" + "\t" + getStatus() + "\t" + getBonus()  + "\t" + stock +"\t" + getEquipment().getDescription() ;
			//这里的super.getBonus(),是父类对象的属性，还是子类对象中的属性，只是借用了父类的名而已？
			//private ,体现了封装性，这里是子类继承了父类的所有结构和方法，子父类之间有继承性时，get访问的到底是谁的？
			
			//这里需要针对某一个对象进行讨论，光从子父类的关系上来看，容易晕
			//讨论：已知，在没有继承性时，private的属性等，是不想往外暴露，通过get、set方法可以访问到对象私有的属性
			//这里的Architect对象，拥有父类所有的信息，此时的父类中的private属性，默认是多少，该对象的该项属性就是多少，
			//和其他对象无关，就算是重新设置（set),被改变的也只是自己的对象而已
		}

		public String tString() {
			return getMemberId()+ "/" + getId() + "\t" + getName() + "\t" + getAge() + "\t"+ getSalary()+ "\t" + "架构师" 
					+ "\t" + getBonus()+ "\t" + getStock();
		}
	
	
	
	
	
	
	
	
	
}
