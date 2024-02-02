package com.atguigu.exer3;

public class MyDate {

	String year;
	int month;
	int day;

	public MyDate(String year,int month,int day) {
		this.year = year;
		this.month = month;
		this.day = day;

	}

	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}

		if (o instanceof MyDate) {
			MyDate m = (MyDate) o;
			if (this.year.equals(m.year) && this.month == m.month && this.day == m.day) {
				return true;
		}
		}
		return false;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

}
