package com.atguigu.queue;

import java.util.Scanner;

public class CircleArrayQueueDemo {

	public static void main(String[] args) {

		//测试一把
		System.out.println("测试数组模拟环形队列的案例~~~");

		// 创建一个环形队列
		CircleArray queue = new CircleArray(4); //说明设置4, 其队列的有效数据最大是3
		char key = ' '; // 接收用户输入
		Scanner scanner = new Scanner(System.in);//
		boolean loop = true;
		// 输出一个菜单
		while (loop) {
			System.out.println("s(show): 显示队列");
			System.out.println("e(exit): 退出程序");
			System.out.println("a(add): 添加数据到队列");
			System.out.println("g(get): 从队列取出数据");
			System.out.println("h(head): 查看队列头的数据");
			key = scanner.next().charAt(0);// 接收一个字符
			switch (key) {
				case 's':
					queue.showQueue();
					break;
				case 'a':
					System.out.println("输出一个数");
					int value = scanner.nextInt();
					queue.addQueue(value);
					break;
				case 'g': // 取出数据
					try {
						int res = queue.getQueue();
						System.out.printf("取出的数据是%d\n", res);
					} catch (Exception e) {
						// TODO: handle exception
						System.out.println(e.getMessage());
					}
					break;
				case 'h': // 查看队列头的数据
					try {
						int res = queue.headQueue();
						System.out.printf("队列头的数据是%d\n", res);
					} catch (Exception e) {
						// TODO: handle exception
						System.out.println(e.getMessage());
					}
					break;
				case 'e': // 退出
					scanner.close();
					loop = false;
					break;
				default:
					break;
			}
		}
		System.out.println("程序退出~~");
	}

}


class CircleArray {

	// 环形队列初始时预留数组的最后一个位置为空，以便在队列满时可以区分队列的状态。
	// 当队列发生环绕时，预留的空位置会随着 rear 指针的移动而改变位置。
	// 具体来说，当 rear 到达数组末尾时，它会回到数组的起始位置。
	// 第一次环绕时，即当 rear 重新指向数组起始位置时，
	// 新元素会被添加到数组的末尾位置（即上一次 rear 所指向的位置）。
	// 而 rear 指向的起始位置将在下一次添加新元素时被使用。
	// 这种设计确保了队列可以循环使用数组的空间，但始终会有一个位置为空，以区分队列满和队列空的状态。

	private int maxSize; // 表示数组的最大容量
	//front 变量的含义做一个调整： front 就指向队列的第一个元素, 也就是说 arr[front] 就是队列的第一个元素
	//front 的初始值 = 0
	private int front;
	//rear 变量的含义做一个调整：rear 指向队列的最后一个元素的后一个位置. 因为希望空出一个空间做为约定.
	//rear 的初始值 = 0
	private int rear; // 队列尾
	private int[] arr; // 该数据用于存放数据, 模拟队列

	public CircleArray(int arrMaxSize) {
		maxSize = arrMaxSize;
		arr = new int[maxSize];
	}

	// 判断队列是否满
	public boolean isFull() {
		return (rear  + 1) % maxSize == front;
	}

	// 判断队列是否为空
	public boolean isEmpty() {
		return rear == front;
	}

	// 添加数据到队列
	public void addQueue(int n) {

		/*
		 *  maxSize = 4

			// 添加数据10,20,30：
			[10][20][30][空]  
			↑           ↑
			front       rear  // rear = 3

			// 取出10，添加40：
			[空][20][30][40]  
			    ↑        ↑
			    front    rear  // rear = 0, front = 1

			// 取出20，添加50：
			[50][空][30][40]  
			↑       ↑
			rear    front   // rear = 1, front = 2

			添加数据时，先在 rear 位置存数据，然后 rear = (rear + 1) % maxSize
			所以添加40时是在角标3的位置，然后 rear = (3 + 1) % 4 = 0
			添加50时是在角标0的位置，然后 rear = (0 + 1) % 4 = 1
		 */


		// 判断队列是否满
		if (isFull()) {
			System.out.println("队列满，不能加入数据~");
			return;
		}
		//直接将数据加入
		arr[rear] = n;
		//将 rear 后移, 这里必须考虑取模
		rear = (rear + 1) % maxSize;

		// 等价于：
		// rear = ++rear % maxSize;

		/**
		 * 两种写法的区别仅在于：
			方式1更清晰易懂
			方式2代码更简洁
			个人建议使用方式1，因为：
			1. 更直观地表达了"移动到下一个位置"的意图
			2. 更容易理解和维护
			3. 避免使用自增运算符可能带来的混淆
		 */





	}

	// 获取队列的数据, 出队列
	public int getQueue() {
		// 判断队列是否空
		if (isEmpty()) {
			// 通过抛出异常
			throw new RuntimeException("队列空，不能取数据");
		}
		// 这里需要分析出 front是指向队列的第一个元素
		// 1. 先把 front 对应的值保留到一个临时变量
		// 2. 将 front 后移, 考虑取模
		// 3. 将临时保存的变量返回
		int value = arr[front];
		front = (front + 1) % maxSize;
		return value;

	}

	// 显示队列的所有数据
	public void showQueue() {
		// 遍历
		if (isEmpty()) {
			System.out.println("队列空的，没有数据~~");
			return;
		}
		// 思路：从front开始遍历，遍历多少个元素
		// 动脑筋
		for (int i = front; i < front + size() ; i++) {
			

			System.out.printf("arr[%d]=%d\n", i % maxSize, arr[i % maxSize]);

			/**
			  	当不是第一次循环时，数据可能是这样的：
				假设：maxSize = 4

				// 第一轮：
				[10][20][30][空]  
				↑           ↑
				front       rear  // front = 0, rear = 3

				// 取出10，添加40：
				[空][20][30][40]  
				    ↑        ↑
				    front    rear  // rear = 0, front = 1

				// 取出20，添加50：
				[50][空][30][40]  
				↑       ↑
				rear    front   // rear = 1, front = 2

				此时遍历：
				for (i = front; i < front + size(); i++)
				// i = 2, 3, 0
				// 需要访问：arr[2], arr[3], arr[0]
			 */

			// System.out.printf("arr[%d]=%d\n", i, arr[i]);
		}

		/**
		 * 在这个实现中，i 不可能大于数组的最大下标，因为：
			front 最大为 maxSize-2
			size() 最大为 maxSize-1
			所以 front + size() 最大为 maxSize-1
		 * 
		 *  maxSize = 4
			front = 0, size = 3  // [10][20][30][空]
			i: 0,1,2  // 不会超过数组下标

			front = 1, size = 2  // [20][30][40][空]
			i: 1,2    // 不会超过数组下标

			front = 2, size = 1  // [空][空][30][40]
			i: 2      // 不会超过数组下标
		 */
	}

	// 求出当前队列有效数据的个数
	public int size() {
		// rear = 2
		// front = 1
		// maxSize = 3
		return (rear + maxSize - front) % maxSize;
	}

	// 显示队列的头数据， 注意不是取出数据
	public int headQueue() {
		// 判断
		if (isEmpty()) {
			throw new RuntimeException("队列空的，没有数据~~");
		}
		return arr[front];
	}
}