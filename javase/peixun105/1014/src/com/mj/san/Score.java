package com.mj.san;


	import java.util.Scanner;

	/**FileName:	cn.etc.homework 	Score.java
	 * TODO:		
	 * Copyright:	Copyright (c) 2015-2016 All Rights Reserved. Company: 01skill-soft.com Inc.
	 * @author: 	����s
	 * @Date:		2020��10��14��:����10:20:58
	 * @version: 	1.0
	 * 
	 *           Modification History: Date			Author 		Version 	Description
	 *           ----------------------------------------------------------------------
	 *           					   2020��10��14�� 	����	 		1.0 		1.0 Version
	 * 
	 */
	public class Score {
		public static void main(String[] args) {
			Scanner sc = new Scanner(System.in);
			System.out.println("������ѧ������");
			int num = sc.nextInt();
			int[] arr = new int[num];
			for(int i= 0;i<num;i++){
				System.out.println("�������"+(i+1)+"��ѧ���ĳɼ�");
				int score = sc.nextInt();
				arr[i] = score;
			}
			
			int max = arr[0];
			for(int i = 1;i<arr.length;i++){
				if(max<arr[i]){
					max=arr[i];
				}
			}
			for(int i = 0;i<arr.length;i++){
				if(arr[i]>=max-10){
					System.out.println("��"+(i+1)+"��ѧ���ĵȼ�ΪA");
				}else if(arr[i]>=max-20){
					System.out.println("��"+(i+1)+"��ѧ���ĵȼ�ΪB");
				}else if(arr[i]>=max-30){
					System.out.println("��"+(i+1)+"��ѧ���ĵȼ�ΪC");
				}else{
					System.out.println("��"+(i+1)+"��ѧ���ĵȼ�ΪD");
				}
			}
			
		}
	}

