package com.atguigu.team.domain;

public class Printer extends Programmer implements Equipment {
	String name;
	String type;

	
	
	public Printer() {
		super();
	}

	public Printer(String name, String type) {
		this.name = name;
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescription() {

		return name +"("+ type+")";

	}
}