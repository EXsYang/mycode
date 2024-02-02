package at.zixue.com;

public class day2 {

	public static void main(String[] args) {
		boolean isFlag = true;
		for (int i = 2; i <= 100; i++) {// i的取值范围2~99
			for (int j = 2; j < i; j++) {
				if (i % j == 0) {
					isFlag = false;
				}

			}
			if (isFlag == true) {
				System.out.println(i + "\t" + i);
			} 
			isFlag = true;
		}

	}

}
