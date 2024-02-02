package com.atguigu.team.domain;

import com.atguigu.team.service.Status;
import com.atguigu.team.service.TeamService;
import com.atguigu.team.view.TeamView;

public class Programmer extends Employee{

	private int memberId;//用来记录成员加入开发团队后在团队中的ID
	private Status status = Status.FREE;//静态对象可以通过类.对象初始化
	private Equipment equipment;//设备
	
	//代理类钩子方法，使用多态
	public Programmer() {
		
	}
	public Programmer(int id, String name, int age, double salary) {
		super(id, name, age, salary);
	}
	public Programmer(int id, String name, int age, double salary, Equipment equipmemt) {
		super(id, name, age, salary);
		//this.equipmemt = equipmemt;
	}
	
	
	public  String getDescription() {
		
		
		return	getEquipmemt().getDescription();
		
		
		}
		
		
	public int getMemberId() {
		return memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}

	

	public Equipment getEquipmemt() {
		return equipment;
	}

	public void setEquipmemt(Equipment equipment) {
		this.equipment = equipment;
	}
	
	//设置状态的方法
	public void setStatus(Status status) {
		this.status = status;
	}

	public Status getStatus() {
		return status;
	}
	
	@Override
	public String toString() {
		//return super.toString()  + "	" + status.FREE.getNAME() + "	" + "	" + "	"  + equipmemt.getDescription();
		return getDetails() +getPosition()+ "	" +  getStatus()  + "	" + "	" +"	" +equipment.getDescription() ;
	}
	public String List() {
		return  getMemberId()+ "/"+getDetails()+ "	" + getPosition();
	}
	
}


