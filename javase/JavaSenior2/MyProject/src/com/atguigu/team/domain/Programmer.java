package com.atguigu.team.domain;

import com.atguigu.team.service.Status;

public class Programmer extends Employee implements Equipment{
	 private int memberId;
	private Status status = Status.FREE;
	private Equipment equipment;
	
	
	 
	public Programmer() {
	}

	public Programmer(int id, String name, int age, double salary) {
		super(id, name, age, salary);
		// TODO Auto-generated constructor stub
	}
	 //Employee  :  10, id, name, age, salary
    //Programmer:  11, id, name, age, salary
    //Designer  :  12, id, name, age, salary, bonus
    //Architect :  13, id, name, age, salary, bonus, stock

	public Programmer(int id, String name, int age, double salary, Equipment equipment) {
		super(id, name, age, salary);
		
		this.equipment = equipment;
	}


	public int getMemberId() {
		return memberId;
	}
	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public Equipment getEquipment() {
		return equipment;
	}
	public void setEquipment(Equipment equipment) {
		this.equipment = equipment;
	}
	@Override
	public String toString() {
		return getDescription() + "\t" + "程序员" + "\t" + status + "\t"+ "\t\t\t"+ "\t" + equipment.getDescription();
//		return  id+ "\t" + name + "\t" + age + "\t" + salary memberId=" + memberId + ", equipment=" + equipment + "]";
//		3 李彦宏 23 7000.0 程序员 FREE 戴尔(NEC17寸)
	}
	@Override
	public String getDescription() {
		return super.toString();
	}

	public String tString() {
		return getMemberId()+ "/" + getId() + "\t" + getName() + "\t" + getAge() + "\t"+ getSalary()+ "\t" + "程序员";
	}
}
