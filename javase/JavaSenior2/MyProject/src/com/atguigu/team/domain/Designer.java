package com.atguigu.team.domain;

public class Designer extends Programmer {

	private  double bonus;//奖金

	 //Employee  :  10, id, name, age, salary
    //Programmer:  11, id, name, age, salary
    //Designer  :  12, id, name, age, salary, bonus
    //Architect :  13, id, name, age, salary, bonus, stock
	public Designer() {
		super();
	}
	
	public Designer(double bonus) {
		super();
		this.bonus = bonus;
	}


	public Designer(int id, String name, int age, double salary, double bonus) {
		super(id, name, age, salary);
		this.bonus = bonus;
		// TODO Auto-generated constructor stub
	}
	public Designer(int id, String name, int age, double salary, Equipment equipment,double bonus) {
		super(id, name, age, salary, equipment);
		this.bonus = bonus;
		// TODO Auto-generated constructor stub
	}

	public Designer(int id, String name, int age, double salary) {
		super(id, name, age, salary);
		// TODO Auto-generated constructor stub		
	}

	public double getBonus() {
		return bonus;
	}

	public void setBonus(double bonus) {
		this.bonus = bonus;
	}

	@Override
	public String toString() {
		return super.getDescription() +  "\t" + "设计师" + "\t" + getStatus() + "\t"  + bonus + "\t\t" + "\t"+ getEquipment().getDescription();
	}
	
	public String getDesc() {
		return super.getDescription();
	}

	public String tString() {
		return getMemberId()+ "/" + getId() + "\t" + getName() + "\t" + getAge() + "\t"+ getSalary()+ "\t" + "设计师" 
				+ "\t" + getBonus();
	}
	

}
