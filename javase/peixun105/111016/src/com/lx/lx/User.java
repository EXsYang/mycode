package com.lx.lx;

public class User{
	public static boolean login(String userid,String password){
		boolean flag=false;
		for(User user:users){
			if(user.getUserid().equals(userid)&&user.getPassword().equals(password)){
				flag=true;
				break;
			}
		}
		return flag;
	}
}
