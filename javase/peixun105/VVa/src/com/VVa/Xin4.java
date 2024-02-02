package com.VVa;

import java.util.ArrayList;
import java.util.List;

public class Xin4 {
public static void main(String[] args) {
	List<Integer> list = new ArrayList<>();
	while(list.size()<10) {
		int num = (int)(Math.random()*10);
		if(!list.contains(num));
		list.add(num);	
	}
	System.out.println(list);
}
}
