package com.zixue.at;

public class BreakTest {
	public static void main(String[] args) {
		int i = 5;
		int j;
		for (i = 0; i < 5; i++) {
			// System.out.print(i+" 外层第"+i+"次进入 ");

			for (j = 0; j <= i; j++) {
				if (j == 3) {
					break;
				}
				System.out.println("j:" + j + "  ");
				// System.out.print("i:"+i+" ");
				// System.out.println("j:"+j+" ");
				// }
			}
			System.out.println();
		}
	}
}
