package com.mj.san;

public class TextMianjichoongzai {

	public static void main(String[] args) {
		Mianjichoongzai s = new Mianjichoongzai();
//		�������
		double d = s.square(5.5,3.62);
		System.out.println("���������"+d);
		
//		Բ�����
		s.square(3.2f);
		
		//���������
		double sj = s.square(5.5, 3.62f);
		System.out.println("�����������"+sj);
	}


}
