package com.atguigu.team.service;

//public class Status {
//
//	private final String NAME;
//	private Status(String name) {// 私有化类的构造器
//		this.NAME = name;
//
//	}
//
//	public static final Status FREE = new Status("FREE");// 空闲
//	public static final Status BUSY = new Status("BUSY");// 已加入开发团队
//	public static final Status VOCATION = new Status("VOCATION");// 正在休假
//
//	public String getName() {
//		return NAME;
//	}
//
//	@Override
//	public String toString() {
//		return NAME;
//	}
//
//}
public enum Status{
	FREE,BUSY,VOCATION;
}
