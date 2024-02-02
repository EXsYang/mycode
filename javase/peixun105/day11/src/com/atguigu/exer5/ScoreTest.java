package com.atguigu.exer5;

import java.util.Scanner;
import java.util.Vector;

public class ScoreTest {
public static void main(String[] args) {
	Scanner scanner = new Scanner(System.in);
	
	int max = 0;
	Vector v = new Vector();
	int a = 0;
	while(true) {
		 a = scanner.nextInt();//获取学生成绩
		if(a < 0) {
			break;
		}
	
		Integer b = a;
		
		v.addElement(b);//添加学生成绩
		
		for(int i = 0; i<v.size();i++) {
			//Integer inscore = new Integer(a);   jdk5.0之前
			// v.elementAt(inscore);
			Object obj = v.elementAt(i);//取出学生成绩
			int o = (Integer)obj;
			if(o > max) {
				max = o;
			}
		}
	}
	
		for(int i = 0; i<v.size();i++) {
			
			Object obj = v.elementAt(i);//取出学生成绩
			//jdk5.0之前
			//Integer inScore = (Integer)obj;
			//int score inScore.intValue();
			
			//int o = (Integer)obj;
			int o = (int)obj;
		if((max-o)<=10) {
			System.out.println("第"+(i+1)+"个学生的成绩等级是："+"A");
		}else if((max-o)<=20) {
			System.out.println("第"+(i+1)+"个学生的成绩等级是："+"B");
		}else if((max-o)<=30) {
			System.out.println("第"+(i+1)+"个学生的成绩等级是："+"C");
		}else {
			System.out.println("第"+(i+1)+"个学生的成绩等级是："+"D");
		}
		
		System.out.println("学生总人数："+ v.size());
	}

			
		
	}
}
