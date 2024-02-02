package com.atguigu.team.domain;

public class PC extends Programmer implements Equipment {
	String model;
	String display;

	
	
	
	public PC() {
		super();
	}

	public PC(String model, String display) {
		this.model = model;
		this.display = display;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getDisplay() {
		return display;
	}

	public void setDisplay(String display) {
		this.display = display;
	}

	public String getDescription() {

		return model +"("+ display+")";
	}
}
