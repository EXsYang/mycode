package com.atguigu.linkedlist;

import java.util.Stack;

public class SingleLinkedListDemo {

	public static void main(String[] args) {
		//进行测试
		//先创建节点
		HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
		HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟");
		HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
		HeroNode hero4 = new HeroNode(4, "林冲", "豹子头");
		
		//创建要给链表
		SingleLinkedList singleLinkedList = new SingleLinkedList();
		
		
		//加入
		singleLinkedList.add(hero1);
		singleLinkedList.add(hero4);
		singleLinkedList.add(hero2);
		singleLinkedList.add(hero3);

		// 测试一下单链表的反转功能
		System.out.println("原来链表的情况~~");
		singleLinkedList.list();
		
//		System.out.println("反转单链表~~");
//		reversetList(singleLinkedList.getHead());
//		singleLinkedList.list();
		
		System.out.println("测试逆序打印单链表, 没有改变链表的结构~~");
		reversePrint(singleLinkedList.getHead());
		
/*		
		//加入按照编号的顺序
		singleLinkedList.addByOrder(hero1);
		singleLinkedList.addByOrder(hero4);
		singleLinkedList.addByOrder(hero2);
		singleLinkedList.addByOrder(hero3);
		
		//显示一把
		singleLinkedList.list();
		
		//测试修改节点的代码
		HeroNode newHeroNode = new HeroNode(2, "小卢", "玉麒麟~~");
		singleLinkedList.update(newHeroNode);
		
		System.out.println("修改后的链表情况~~");
		singleLinkedList.list();
		
		//删除一个节点
		singleLinkedList.del(1);
		singleLinkedList.del(4);
		System.out.println("删除后的链表情况~~");
		singleLinkedList.list();
		
		//测试一下 求单链表中有效节点的个数
		System.out.println("有效的节点个数=" + getLength(singleLinkedList.getHead()));//2
		
		//测试一下看看是否得到了倒数第K个节点
		HeroNode res = findLastIndexNode(singleLinkedList.getHead(), 3);
		System.out.println("res=" + res);
*/

		System.out.println("测试合并两个单链表：mergeTwoLists()");
		// 测试：mergeTwoLists()
		//进行测试
		//先创建节点
		HeroNode h1 = new HeroNode(1, "1", "1");
		HeroNode h3 = new HeroNode(3, "3", "3");
		HeroNode h5 = new HeroNode(5, "5", "5");
		HeroNode h7 = new HeroNode(7, "7", "7");
		HeroNode h9 = new HeroNode(9, "9", "9");


		//先创建节点
		HeroNode h2 = new HeroNode(2, "2", "2");
		HeroNode h4 = new HeroNode(4, "4", "4");
		HeroNode h6 = new HeroNode(6, "6", "6");
		HeroNode h8 = new HeroNode(8, "8", "8");
		HeroNode h10 = new HeroNode(10, "10", "10");


		//创建要给链表
		SingleLinkedList singleLinkedList3 = new SingleLinkedList();
		SingleLinkedList singleLinkedList4 = new SingleLinkedList();


		//加入
		singleLinkedList3.add(h1);
		singleLinkedList3.add(h3);
		singleLinkedList3.add(h5);
		singleLinkedList3.add(h7);
		singleLinkedList3.add(h9);

		//加入
		singleLinkedList4.add(h2);
		singleLinkedList4.add(h4);
		singleLinkedList4.add(h6);
		// singleLinkedList4.add(h6);
		// head -> h2 -> h4 -> h6 -> h6(同一个对象)，这种结构虽然能够创建，但是在进行其他操作（如合并链表）时可能会导致问题
		singleLinkedList4.add(h8);
		// singleLinkedList4.add(h10);

		// 测试
		System.out.println("原来链表3的情况~~");
		singleLinkedList3.list();
		System.out.println("原来链表4的情况~~");
		singleLinkedList4.list();

		// SingleLinkedList singleLinkedList1 = mergeTwoLists(singleLinkedList3, singleLinkedList4);
		SingleLinkedList singleLinkedList1 = mergeTwoLists2(singleLinkedList3, singleLinkedList4);

		// 测试合并之后的链表
		//
		System.out.println("测试合并之后的链表如下：");
		singleLinkedList1.list();



	}

	// 合并两个有序的单链表，合并之后的链表依然有序。
	public static SingleLinkedList mergeTwoLists(SingleLinkedList list1, SingleLinkedList list2) {

		//思路，一对数字一对数字的比较，单链表1上的第一个和单链表2上的第一个元素的值比较大小
		// 将数字小的放在新生单链表后面，如果其中一个比较到了最后则将另一个单链表剩下的所有元素都
		// 放在新生链表reverseHead的前面或者放在新生链表reverseHead3的最后

		HeroNode head1 = list1.getHead();
		HeroNode head2 = list2.getHead();

		if(head1.next == null || head2.next == null) {
			return null;// 有空链表不能合并，不能打印
		}

		//因为是两个有序单链表，所以先区分是从小到大还是从大到小
		//定义一个辅助的指针(变量)，帮助我们遍历原来的链表
		HeroNode cur1 = head1.next;
		HeroNode cur2 = head2.next;
		HeroNode next1 = null;// 指向当前节点[cur]的下一个节点
		HeroNode next2 = null;// 指向当前节点[cur]的下一个节点
		//新建一个新的链表
		HeroNode reverseHead = new HeroNode(0, "", "");

		//

		//对一个链表进行循环，如果该单链表已经走到了最后，则不需要进行比较了
		//用第一个链表作为参考
		// while (cur1 != null || cur2 != null){
		while (cur1 != null && cur2 != null){

			// 首先记录原来的链表1当前节点的下一个节点的地址值,
			// 因为操作完链表1当前节点后，当前节点的下一个节点就变了
			next1 = cur1.next;
			next2 = cur2.next;

			// //比较两个头节点
			// if (cur1.no <= cur2.no){
			// 	// 将数字小的那个放在新链表的首位
			// 	reverseHead.next = cur1;
			// 	// 将数字大的那个放在新链表的首位的next位置
			// 	cur1.next = cur2;
			// 	//指针后移
			// 	cur1 = next1;
			// }

			//比较两个节点
			if (cur1.no <= cur2.no){
				// 将数字小的那个放在新链表的末尾
				cur1.next = reverseHead.next; //将最小的这个cur1置为null
				// 将数字大的那个放在新链表的reverseHead.next位置
				reverseHead.next = cur2;
				// 将数字大的元素指向数字小的那个元素
				cur2.next = cur1;
				//指针后移
				cur1 = next1;
				cur2 = next2;
			}else {
				// 第一个单链表的当前元素的值比第二个单链表当前元素的值大
				// 将数字小的那个放在新链表的末尾
				cur2.next = reverseHead.next; //将最小的这个cur1置为null
				// 将数字大的那个放在新链表的reverseHead.next位置
				reverseHead.next = cur1;
				// 将数字大的元素指向数字小的那个元素
				cur1.next = cur2;
				//指针后移
				cur1 = next1;
				cur2 = next2;
			}

		}

		//将剩下的另外一个单链表挂在reverseHead3的最后
		// 判断是哪一个链表遍历到了最后
		// 分为三种情况
		// 可以通过cur1 和 cur2 来进行判断
		if (cur1 == null){
			// 说明有可能需要将单链表2剩下的值放在新生链表的前面
			// 继续判断
			// 需要将单链表2剩下的值放在新生链表的前面。
			// 另一种逻辑，使用死循环找单链表的最后一个节点，即找.next为空的元素节点，然后将另一个单链表放在最后即可
				while (cur2 != null){
					// 记录下一个元素
					next2 = cur2.next;
					// 第一个单链表的当前元素的值比第二个单链表当前元素的值大
					// 将数字小的那个放在新链表的末尾
					cur2.next = reverseHead.next; //将最小的这个cur1置为null
					// 放在新链表的reverseHead.next位置
					reverseHead.next = cur2;
					//指针后移
					cur2 = next2;
				}
		}else if (cur2 == null){
			// 说明有可能需要将单链表2剩下的值放在新生链表的前面
			// 继续判断
			// 需要将单链表2剩下的值放在新生链表的前面
			while (cur1 != null){
				// 记录下一个元素
				next1 = cur1.next;
				// 第一个单链表的当前元素的值比第二个单链表当前元素的值大
				// 将数字小的那个放在新链表的末尾
				cur1.next = reverseHead.next; //将最小的这个cur1置为null
				// 放在新链表的reverseHead.next位置
				reverseHead.next = cur1;
				//指针后移
				cur1 = next1;
			}
		}else if (cur1 == null && cur2 == null){
			//都为空，不需要进行操作
		}


		// 反转新生成的单向链表
		HeroNode cur3 = reverseHead.next;
		HeroNode next3 = null;// 指向当前节点[cur]的下一个节点
		//新建一个新的链表
		HeroNode reverseHead3 = new HeroNode(0, "", "");

		while (cur3 != null){
			//保存当前节点的下一个节点
			next3 = cur3.next;
			//将当前节点的next指向新生链表的reverseHead3.next位置
			cur3.next = reverseHead3.next;
			//将当前节点挂在新生节点的首位
			reverseHead3.next = cur3;
			//指针后移
			cur3 = next3;
		}


		//返回新生单链表
		SingleLinkedList singleLinkedList = new SingleLinkedList();
		singleLinkedList.setHead(reverseHead3);
		return singleLinkedList;

	}

	// gpt改进后的
	// 合并两个有序单链表的优化方法
// 假设两个输入的链表都是按照编号（no）从小到大排序的
	public static SingleLinkedList mergeTwoLists2(SingleLinkedList list1, SingleLinkedList list2) {
		// 获取两个链表的头节点（这些是不存储数据的虚拟头节点）
		HeroNode head1 = list1.getHead();
		HeroNode head2 = list2.getHead();

		// 创建新的链表用于存储合并后的结果
		SingleLinkedList mergedList = new SingleLinkedList();
		HeroNode mergedHead = mergedList.getHead();

		// 获取两个链表的第一个实际数据节点
		// 因为头节点是虚拟的，所以实际数据从head.next开始
		HeroNode cur1 = head1.next;
		HeroNode cur2 = head2.next;

		// 处理特殊情况：如果其中一个链表为空
		if (cur1 == null) {
			// 如果链表1为空，直接返回链表2
			mergedHead.next = cur2;
			return mergedList;
		}
		if (cur2 == null) {
			// 如果链表2为空，直接返回链表1
			mergedHead.next = cur1;
			return mergedList;
		}

		// 用于在新链表中追加节点的指针
		// 初始指向合并后链表的虚拟头节点
		HeroNode current = mergedHead;

		// 当两个链表都还有节点时，比较它们的编号，将较小的节点添加到新链表
		while (cur1 != null && cur2 != null) {
			if (cur1.no <= cur2.no) {
				// 如果链表1当前节点编号更小，将其添加到新链表
				current.next = cur1;
				// 链表1指针向后移动
				cur1 = cur1.next;
			} else {
				// 如果链表2当前节点编号更小，将其添加到新链表
				current.next = cur2;
				// 链表2指针向后移动
				cur2 = cur2.next;
			}
			// 新链表的指针向后移动，准备添加下一个节点
			current = current.next;
		}

		// 处理剩余节点：将未处理完的链表直接接到新链表末尾
		if (cur1 != null) {
			// 如果链表1还有剩余节点
			current.next = cur1;
		}
		if (cur2 != null) {
			// 如果链表2还有剩余节点
			current.next = cur2;
		}

		// 返回合并后的新链表
		// current 和 mergedHead 指向堆内存中的同一个对象
		// 当我们通过 current.next 修改链表结构时,因为 current 和 mergedHead 指向同一个对象,所以对 current 的修改也会反映在 mergedHead 上
		return mergedList;
	}


	//方式2：
	//可以利用栈这个数据结构，将各个节点压入到栈中，然后利用栈的先进后出的特点，就实现了逆序打印的效果
	public static void reversePrint(HeroNode head) {
		if(head.next == null) {
			return;//空链表，不能打印
		}
		//创建要给一个栈，将各个节点压入栈
		Stack<HeroNode> stack = new Stack<HeroNode>();
		HeroNode cur = head.next;
		//将链表的所有节点压入栈
		while(cur != null) {
			stack.push(cur);
			cur = cur.next; //cur后移，这样就可以压入下一个节点
		}
		//将栈中的节点进行打印,pop 出栈
		while (stack.size() > 0) {
			System.out.println(stack.pop()); //stack的特点是先进后出
		}
	}
	
	//将单链表反转
	public static void reversetList(HeroNode head) {
		//如果当前链表为空，或者只有一个节点，无需反转，直接返回
		if(head.next == null || head.next.next == null) {
			return ;
		}
		
		//定义一个辅助的指针(变量)，帮助我们遍历原来的链表
		HeroNode cur = head.next;
		HeroNode next = null;// 指向当前节点[cur]的下一个节点
		HeroNode reverseHead = new HeroNode(0, "", "");
		//遍历原来的链表，每遍历一个节点，就将其取出，并放在新的链表reverseHead 的最前端
		//动脑筋
		while(cur != null) { 
			next = cur.next;//先暂时保存当前节点的下一个节点，因为后面需要使用
			//将cur的下一个节点指向新的链表的最前端。
			// 这里需要注意：cur.next是属性赋值，循环时，
			// 是给当前节点的next属性赋值，而不是给当前节点的下一个节点的next属性赋值。
			// 这行代码的目的是将当前节点的 next 指针指向新链表的第一个节点。这样做是为了在新链表的头部插入当前节点。
			// reverseHead.next = cur;: 这行代码将当前节点 cur 连接到新链表的头部。reverseHead 是新链表的头节点，它的 next 指针现在指向 cur，因此 cur 成为新链表的第一个节点。
			cur.next = reverseHead.next;
			reverseHead.next = cur; //将cur 连接到新的链表上
			cur = next;//让cur后移
		}
		//将head.next 指向 reverseHead.next , 实现单链表的反转
		head.next = reverseHead.next;
	}
	
	//查找单链表中的倒数第k个结点 【新浪面试题】
	//思路
	//1. 编写一个方法，接收head节点，同时接收一个index 
	//2. index 表示是倒数第index个节点
	//3. 先把链表从头到尾遍历，得到链表的总的长度 getLength
	//4. 得到size 后，我们从链表的第一个开始遍历 (size-index)个，就可以得到
	//5. 如果找到了，则返回该节点，否则返回nulll
	public static HeroNode findLastIndexNode(HeroNode head, int index) {
		//判断如果链表为空，返回null
		if(head.next == null) {
			return null;//没有找到
		}
		//第一个遍历得到链表的长度(节点个数)
		int size = getLength(head);
		//第二次遍历  size-index 位置，就是我们倒数的第K个节点
		//先做一个index的校验
		if(index <=0 || index > size) {
			return null; 
		}
		//定义给辅助变量， for 循环定位到倒数的index
		HeroNode cur = head.next; //3 // 3 - 1 = 2
		for(int i =0; i< size - index; i++) {
			cur = cur.next;
		}
		return cur;
		
	}
	
	//方法：获取到单链表的节点的个数(如果是带头结点的链表，需求不统计头节点)
	/**
	 * 
	 * @param head 链表的头节点
	 * @return 返回的就是有效节点的个数
	 */
	public static int getLength(HeroNode head) {
		if(head.next == null) { //空链表
			return 0;
		}
		int length = 0;
		//定义一个辅助的变量, 这里我们没有统计头节点
		HeroNode cur = head.next;
		while(cur != null) {
			length++;
			cur = cur.next; //遍历
		}
		return length;
	}

}


//定义SingleLinkedList 管理我们的英雄
class SingleLinkedList {
	//先初始化一个头节点, 头节点不要动, 不存放具体的数据
	private HeroNode head = new HeroNode(0, "", "");
	
	
	//返回头节点
	public HeroNode getHead() {
		return head;
	}

	public void setHead(HeroNode head) {
		this.head = head;
	}

	//添加节点到单向链表
	//思路，当不考虑编号顺序时
	//1. 找到当前链表的最后节点
	//2. 将最后这个节点的next 指向 新的节点
	public void add(HeroNode heroNode) {
		
		//因为head节点不能动，因此我们需要一个辅助遍历 temp
		HeroNode temp = head;

		// 重复添加同一个节点的检查
		if (temp.next == heroNode) {
			System.out.println("警告：节点已存在，请创建新的节点");
			return;
		}

		//遍历链表，找到最后
		while(true) {

			//找到链表的最后
			if(temp.next == null) {//
				break;
			}
			//如果没有找到最后, 将将temp后移
			temp = temp.next;
		}

		//当退出while循环时，temp就指向了链表的最后
		//将最后这个节点的next 指向 新的节点
		temp.next = heroNode;
	}
	
	//第二种方式在添加英雄时，根据排名将英雄插入到指定位置
	//(如果有这个排名，则添加失败，并给出提示)
	public void addByOrder(HeroNode heroNode) {
		//因为头节点不能动，因此我们仍然通过一个辅助指针(变量)来帮助找到添加的位置
		//因为单链表，因为我们找的temp 是位于 添加位置的前一个节点，否则插入不了
		HeroNode temp = head;
		boolean flag = false; // flag标志添加的编号是否存在，默认为false
		while(true) {
			if(temp.next == null) {//说明temp已经在链表的最后
				break; //
			} 
			if(temp.next.no > heroNode.no) { //位置找到，就在temp的后面插入
				break;
			} else if (temp.next.no == heroNode.no) {//说明希望添加的heroNode的编号已然存在
				
				flag = true; //说明编号存在
				break;
			}
			temp = temp.next; //后移，遍历当前链表
		}
		//判断flag 的值
		if(flag) { //不能添加，说明编号存在
			System.out.printf("准备插入的英雄的编号 %d 已经存在了, 不能加入\n", heroNode.no);
		} else {
			//插入到链表中, temp的后面
			heroNode.next = temp.next;
			temp.next = heroNode;
		}
	}

	//修改节点的信息, 根据no编号来修改，即no编号不能改.
	//说明
	//1. 根据 newHeroNode 的 no 来修改即可
	public void update(HeroNode newHeroNode) {
		//判断是否空
		if(head.next == null) {
			System.out.println("链表为空~");
			return;
		}
		//找到需要修改的节点, 根据no编号
		//定义一个辅助变量
		HeroNode temp = head.next;
		boolean flag = false; //表示是否找到该节点
		while(true) {
			if (temp == null) {
				break; //已经遍历完链表
			}
			if(temp.no == newHeroNode.no) {
				//找到
				flag = true;
				break;
			}
			temp = temp.next;
		}
		//根据flag 判断是否找到要修改的节点
		if(flag) {
			temp.name = newHeroNode.name;
			temp.nickname = newHeroNode.nickname;
		} else { //没有找到
			System.out.printf("没有找到 编号 %d 的节点，不能修改\n", newHeroNode.no);
		}
	}
	
	//删除节点
	//思路
	//1. head 不能动，因此我们需要一个temp辅助节点找到待删除节点的前一个节点
	//2. 说明我们在比较时，是temp.next.no 和  需要删除的节点的no比较
	public void del(int no) {
		HeroNode temp = head;
		boolean flag = false; // 标志是否找到待删除节点的
		while(true) {
			if(temp.next == null) { //已经到链表的最后
				break;
			}
			if(temp.next.no == no) {
				//找到的待删除节点的前一个节点temp
				flag = true;
				break;
			}
			temp = temp.next; //temp后移，遍历
		}
		//判断flag
		if(flag) { //找到
			//可以删除
			temp.next = temp.next.next;
		}else {
			System.out.printf("要删除的 %d 节点不存在\n", no);
		}
	}
	
	//显示链表[遍历]
	public void list() {
		//判断链表是否为空
		if(head.next == null) {
			System.out.println("链表为空");
			return;
		}
		//因为头节点，不能动，因此我们需要一个辅助变量来遍历
		HeroNode temp = head.next;
		while(true) {
			//判断是否到链表最后
			if(temp == null) {
				break;
			}
			//输出节点的信息
			// 这里打印的第一个节点是head.next，而不是head,
			// 因为temp初始值为head.next
			// 所以这里打印时，打印的第一个节点是head.next，而不是head
			// 
			System.out.println(temp);
			//将temp后移， 一定小心
			temp = temp.next;
		}
	}
}

//定义HeroNode ， 每个HeroNode 对象就是一个节点
class HeroNode {
	public int no;
	public String name;
	public String nickname;
	public HeroNode next; //指向下一个节点
	//构造器
	public HeroNode(int no, String name, String nickname) {
		this.no = no;
		this.name = name;
		this.nickname = nickname;
	}
	//为了显示方法，我们重新toString
	@Override
	public String toString() {
		return "HeroNode [no=" + no + ", name=" + name + ", nickname=" + nickname + "]";
	}
	
}
