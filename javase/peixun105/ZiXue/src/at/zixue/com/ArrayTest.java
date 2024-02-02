package at.zixue.com;

public class ArrayTest {
	/**
	 * @Description
	 * @author
	 * @date 2022年10月30日下午4:11:18
	 * @param args
	 */
	public static void main(String[] args) {

		int num;
		num = 88;
		int id = 444;

		int[] ids;
		ids = new int[] { 222, 555, 4, 5, 45 };//静态初始化
		String[] names = new String[5];//动态初始化

		names[0] = "杨达";
		names[1] = "郭志阳";
		names[2] = "王辉";
		names[3] = "秦飞";
		names[4] = "赵新东";
		// names[5] = "张东双";

		int[] classes = new int[4];
		int[] books = new int[2];
		 char[] c2 = new char[5];
		byte[] c = new byte[5];
		c[0] = 'y';
		c[1] = '\n';
		c[2] = '2';
		c[3] = '\t';
		c[4] = 5;
		// c[5] = 'a';
		c2[0] = 'y';
		c2[1] = '\n';
		c2[2] = '2';
		c2[3] = '\t';
		c2[4] = 5;
		System.out.println("数组c:......" + c);
		System.out.println("数组c2:......" + c2);
		System.out.println(c);
		 int i;
		int j;
		for (i = 0; i < names.length; i++) {
			// System.out.println(names);
			System.out.println(names[i]);
		}
	}
}