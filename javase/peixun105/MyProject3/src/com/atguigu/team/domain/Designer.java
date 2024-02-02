package com.atguigu.team.domain;

public class Designer extends Programmer {

	private double bonus;// 奖金

	public Designer() {
		super();
	}

	public Designer(int id, String name, int age, double salary, double bonus) {
		super(id, name, age, salary);
		this.bonus = bonus;
	}

	public Designer(int id, String name, int age, double salary, double bonus, Equipment equipment) {
		super(id, name, age, salary);
		this.bonus = bonus;
	}

	public double getBonus() {
		return bonus;
	}

	public void setBonus(double bonus) {
		this.bonus = bonus;
	}

	@Override
	public String toString() {

		return getDetails()+getPosition()+ "	" +  getStatus() + "	" + bonus + "	"  + "	"
				+ getEquipmemt().getDescription();
	}
	public String List() {
		return super.List() + "	" + bonus;
	}
}
