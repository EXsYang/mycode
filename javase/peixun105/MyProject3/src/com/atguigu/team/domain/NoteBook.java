package com.atguigu.team.domain;

public class NoteBook extends Programmer implements Equipment {

	String model;
	double price;

	
	
	public NoteBook() {
		super();
	}

	public NoteBook(String model, double price) {
		this.model = model;
		this.price = price;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getDescription() {

		return model +"("+ price+")";

	}
}
