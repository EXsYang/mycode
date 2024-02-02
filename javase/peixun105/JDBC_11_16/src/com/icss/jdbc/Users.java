package com.icss.jdbc;

public class Users {

	Integer id;
	Integer flag;
	String name;
	String password;
	String sex;
	int phone;

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getFlag() {
		return flag;
	}
	public void setFlag(Integer flag) {
		this.flag = flag;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public int getPhone() {
		return phone;
	}
	public void setPhone(int phone) {
		this.phone = phone;
	}
	@Override
	public String toString() {
		return "Users [id=" + id + ", flag=" + flag + ", name=" + name + ", password=" + password + ", sex=" + sex
				+ ", phone=" + phone + "]";
	}
	
}
