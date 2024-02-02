package com.atguigu.team.service;

import com.atguigu.team.domain.Employee;
import com.atguigu.team.domain.Programmer;
import com.atguigu.team.view.TSUtility;

public class TeamService {

	private static int counter = 1;
	private final int MAX_MEMBER = 5;
	private Programmer[] team = new Programmer[MAX_MEMBER];// 新建了一个长度为5的数组
	private int total = 0;
	static int a = 0;
	static int b = 0;
	static int c = 0;
	static int d = 0;

	public Programmer[] getTeam() {

		return team;
	}

	public static int getCounter() {
		return counter;
	}

	public static void setCounter(int counter) {
		TeamService.counter = counter;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public void setTeam(Programmer[] team) {
		this.team = team;
	}


	public void addMember(Employee e) throws TeamException {// 传进来的是一个listSvc的对象数组元素的地址值,

		// 该员工已在本开发团队中,遍历对象数组，比较this.ID与对象数组中元素.ID是否相等
		// 还得给状态赋值为忙碌状态BUSY
		if (total == MAX_MEMBER) {
			throw new TeamException("成员已满，添加失败");
		}
		if (e.getId() == 1) {
			throw new TeamException("添加失败，该成员不是开发人员，无法添加");
		}
		// if (total > 0) {// 进来之后就跳出，if-else结构了
		for (int i = 0; i < total; i++) {
			//if (e.getId() == team[i].getId()) {//这里使用memberId去比较也可以，能走到这的一定是一个programmer
			Programmer p = (Programmer)e;
				if (p.getMemberId() == team[i].getMemberId()) {//这里memberId没加入过团队，初始值是0，就不抛异常，如果加入过团队，memberId赋过值，进行判断，能走到这的一定是一个programmer
				
					throw new TeamException("该员工已在本开发团队中");
			}
		}	
		// }
		if (e.getStatus() == Status.BUSY) {
			throw new TeamException("该员工已是某团队成员 ");
		}
		if (e.getStatus() == Status.VOCATION) {
			throw new TeamException("该员工正在休假无法添加");

		}

		
			int type = Integer.parseInt(Data.EMPLOYEES[e.getId() - 1][0]);
			if (type == Data.PROGRAMMER) {
				++b;
				if(b>3) {
					--b;
				throw new TeamException("团队中至多只能有三名程序员");
				}
			}
			if (type == Data.DESIGNER) {
				++c;
				if(c>2) {
					--c;
				throw new TeamException("团队中至多只能有两名设计师");
			}
			}
			if(type == Data.ARCHITECT){
				++d;
				if(d>1) {
					--d;
				throw new TeamException("团队中至多只能有一名架构师");
				}
			}

		if (e instanceof Employee) {
			Programmer programmer = (Programmer) e;//这里地址值还是指向原来的对象中的对象，只不过是进行了强转
			programmer.setStatus(Status.BUSY);
			// programmer.setAge(99);//这里设置的是Programmer[]数组中的信息
			// e.setAge(99);//这里设置的也是Programmer[]数组中的信息
			team[total++] = programmer;// 这里team对象数组中存放的是对象list的属性Employee[]中的对象元素！！！！！！！！
			// 由此可见引用类型之间参数传递的是地址值，最后指向的还是原来对象数组中的元素！！！
			programmer.setMemberId(counter++);
			// TeamView t = new TeamView();
			// t.getListSvc().getEmployee(id)
			System.out.println("添加成功");

		}

	}

	public void removeMember(int memberId) throws TeamException {
		// 删除指定位置上的对象，还得给状态重新赋值为空闲状态FREE
		// 索引形参和团队中的唯一memberId进行配对
		boolean isFlag = false;
		boolean Flag = false;

		for (int i = 0; i < total; i++) {// 上来先跑一遍对象数组，找数组里是否有索引角标元素
			// i = 0时isFlag为true,接着走i=1时isFlag又变为false
			if (memberId == team[i].getMemberId()) {

				isFlag = true;
				break;
			}
		}
		if (isFlag == false) {
			throw new TeamException("找不到指定memberId的员工，删除失败");
		}

		if (isFlag) {
			System.out.println("确定删除吗？Y/N");
			char key = TSUtility.readConfirmSelection();
			Flag = (key == 'Y' || key == 'y') ? true : false;
		}
		if (Flag) {
			for (int i = 0; i < total; i++) {
				if (memberId == team[i].getMemberId()) {
					//删除对象的同时，将对应的计数减一
					int type = Integer.parseInt(Data.EMPLOYEES[team[i].getId() - 1][0]);
					if (type == Data.PROGRAMMER) {
							--b;
					}
					if (type == Data.DESIGNER) {
							--c;
					}
					if(type == Data.ARCHITECT){
							--d;
					}
					
					//删除对象的同时将其状态改为FREE
					team[i].setStatus(Status.FREE);
					
					break;//跳出循环
				}
			}
			
			
			
			
			
			
			
			int j;//方式三：
			for ( j = 0; j < total - 1; j++) {// 方式三：
				// 对象属性memberId存放在对象数组team中的位置和memberId没有关系
				// 需要获取memberId属性对应对象在数组team中的角标
				//	for (int j = 0; j < total - 1; j++) {// 原来的，total是团队中实际人数，total-1是对应数组最后一位的角标
					if (memberId == team[j].getMemberId()) {// 原来的，

					//方式三：
					//for(j ; j < total - 1 ; j++) {//方式三：获取不到j的值
						//方式4：正确的，写成开的，这样可以，不获取j,直接用j,在内层循环中自己j++,同时添加终止条件
						for( ;; ) {
							team[j]= team[j + 1];
								j++;
							if(j >= total - 1) {
								break;
							}
							
						
					}
					
					
					
//					1/2	马化腾	32	18000.0		架构师	15000.0	2000
//					2/3	李彦宏	23	7000.0		程序员
//					3/4	刘强东	24	7300.0		程序员
//					4/5	雷军	28	10000.0		设计师	5000.0
//					5/6	任志强	22	6800.0		程序员
					
					
//					Programmer temp;
//					temp = team[j];//team[2]刘强东
//				team[j] = team[j + 1];//team[2](雷军) = team[3]雷军
//				team[j + 1] = temp ;//team[3]雷军 =team[2]刘强东，这种方式可以，是正确的
				//把刘强东换到雷军所在位置，此时刘强东的位置是列表中的第四位，即team[3],当team[2]时for循环找到
				//满足if语句条件的memberId == team[j].getMemberId()，此时在if中将满足条件的换到了下一位，
				//for循环+1接着走，结果又满足进入if语句的条件，继续往下换，直到换到倒数第二位，即把需要删除的换到数组的最后一位
				//之后通过下面的语句，将其置为null,完成删除操作
				
//				--------------------团队成员列表--------------------
//				TDI/ID  姓名    年龄      工资       职位      奖金        股票 
//				1/2	马化腾	32	18000.0		架构师	15000.0	2000
//				2/3	李彦宏	23	7000.0		程序员
//				4/5	雷军	28	10000.0		设计师	5000.0
//				5/6	任志强	22	6800.0		程序员
//				-------------------------------------------------
				
				
					//team[j] = team[j + 1];//错误的，只打开这个，关闭上面的交换元素位置，会出现：
				//把后一位赋给当前位置跳出，后一位没有变化导致当前位置和后一个位置元素地址值相同
				}
			}
			// 从删除的那一位memberId-1开始，依次向前移一个位置，到最后一个元素total-1移到倒数第二个total-2位置为止
			// i的取值范围是：memberId-1到total-2
			team[--total] = null;

			// 令最后一个元素total-1为null,并且团队中的实际人数减一
			System.out.println("删除成功");
		}
		
		
		
	}
}
