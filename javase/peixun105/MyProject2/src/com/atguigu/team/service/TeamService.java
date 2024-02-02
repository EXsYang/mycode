package com.atguigu.team.service;

import static org.hamcrest.CoreMatchers.instanceOf;

import com.atguigu.team.domain.Employee;
import com.atguigu.team.domain.NoteBook;
import com.atguigu.team.domain.PC;
import com.atguigu.team.domain.Printer;
import com.atguigu.team.domain.Programmer;
import com.atguigu.team.view.TSUtility;
/**
 * 
 *@Description功能：关于开发团队成员的管理：添加、删除等。
					说明：
					counter为静态变量，用来为开发团队新增成员自动生成团队中的唯一ID，即memberId。（提示：应使用增1的方式）
					MAX_MEMBER：表示开发团队最大成员数
					team数组：用来保存当前团队中的各成员对象 
					total：记录团队成员的实际人数

					说明：
					getTeam()方法：返回当前团队的所有对象
					返回：包含所有成员对象的数组，数组大小与成员人数一致
					addMember(e: Employee)方法：向团队中添加成员
					参数：待添加成员的对象
					异常：添加失败， TeamException中包含了失败原因
					removeMember(memberId: int)方法：从团队中删除成员
					参数：待删除成员的memberId
					异常：找不到指定memberId的员工，删除失败
					另外，可根据需要自行添加其他方法或重载构造器

 *@author yangda Email:909533571qq.com
 *@version
 *@date 2022年11月4日下午1:25:31
 *
 */
public class TeamService {
	private static int counter = 1;
//	用来为开发团队新增成员自动生成团队中的唯一ID，即memberId。（提示：应使用增1的方式）
	
	private final int MAX_MEMBER = 5;
//	表示开发团队最大成员数

	private Programmer[] team = new Programmer[MAX_MEMBER];//角标最大是4
//	用来保存当前团队中的各成员对象 
	
	private int total = 0;
//	记录团队成员的实际人数
	
	/**
	 * 
	 * @Description
					getTeam()方法：返回当前团队的所有对象
					返回：包含所有成员对象的数组，数组大小与成员人数一致
	 * @author
	 * @date 2022年11月4日下午1:34:08
	 * @return
	 */
	
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
	
	public Programmer[] getTeam() {//返回当前团队的所有对象
		//包含所有成员对象的数组，数组大小与成员人数一致
		Programmer[] p = new Programmer[total];
		
//		for(int i = 0;i < team.length;i++) {//将存放在team对象数组中的成员对象复制到新数组p中
//			//一开始数组的长度是0，数组角标越界异常
//			p[i] = team[i];
//		}
		
		int i = 0;
		while( i < MAX_MEMBER && team[i] != null ) {//ArrayIndexOutOfBoundsException
			p[i] = team[i];//当数组p的长度为零时，这里到底越界还是不越界？？？？？？？？？？？？,只有当total不等于0时才进的来，不会越界
			++i;
		}
		
		
		
		return p;
		
	}
	

	/**
	 * 
	 * @Description 添加成员方法
	 * 	参数：待添加成员的对象
		异常：添加失败， TeamException中包含了失败原因
	 * @author
	 * @date 2022年11月4日下午1:29:40
	 * @param e
	 * @throws TeamException
	 */
	public void addMember(Employee e) throws TeamException{//这里可以手动抛出异常
		//将一个Programmer对象添加到team数组中
		
//		成员已满，无法添加
//		该员工已在本开发团队中
		
//		该员工已是某团队成员 
//		该员正在休假，无法添加
//		团队中至多只能有一名架构师
//		团队中至多只能有两名设计师
//		团队中至多只能有三名程序员
		
		if(getTotal() == MAX_MEMBER) {
			throw new TeamException("成员已满，无法添加");
		}
		if(!(e instanceof Programmer)) {
			throw new TeamException("该成员不是开发人员，无法添加");
		}
		
			for(int i = 0;i < getTeam().length;i++) {
				if(e.getId() == getTeam()[i].getId()) {
					throw new TeamException("该员工已在本开发团队中");
				}
			}
		
			
			Programmer p = (Programmer)e;
			
			if(p.getStatus() == Status.BUSY) {
				throw new TeamException("该员工已是某团队成员 ");
			}
			if(p.getStatus() == Status.VOCATION) {
				throw new TeamException("该员正在休假，无法添加 ");
			}
		//if(e instanceof Programmer &&((!(e instanceof Designer))&&(!(e instanceof Architect)))) {
			//判断出e是程序员类型，同时确认team中程序员数量为3
			//根据team中成员的设备类型，确定职位是什么，有几个
//		System.out.println(p.getEquipment());
			
			//设备并不能当作判断员工职业的标准，只有Date中的职位数字可以判断
			String[][] data = Data.EMPLOYEES;
			for(int i = 1;i < data.length;i++) {
				int id = Integer.parseInt(data[i][1]);//提取出来的data数组中的id
				if(p.getId() == id) {//找到data数组中与添加的对象的id相等的那一项，再根据它的type,判断该对象的职位
					int type = Integer.parseInt(data[i][0]);
					if(type == Data.PROGRAMMER) {
						//正在添加的对象是程序员
						//与team数组中的数据进行比较，看看team数组中现在有几个程序员，如果程序员数量>=3 抛出一个异常
						//写一个方法判断team数组中各个职业，在team数组中的数量
						if(getTeamProgrammerNumber() >= 3) {
							throw new TeamException("团队中至多只能有三名程序员");
						}
					}
					if(type == Data.DESIGNER) {
						//正在添加的对象是设计师
						if(getTeamDesignerNumber() >= 2) {
							throw new TeamException("团队中至多只能有两名设计师");
						}
						
					}
					if(type == Data.ARCHITECT) {
						//正在添加的对象是架构师
						if(getTeamArchitectNumber() >= 1) {
							throw new TeamException("团队中至多只能有一名架构师");
						}
					}
//					System.out.println(type);
					
				}
				
				
				
			}
			
			
			
			
			
			
//			if(p.getEquipment() instanceof PC) {//equipment属性 instanceof PC
//				int total = 0;
//				for(int i = 0;i < getTeam().length;i++) {//遍历team数组
//					if(getTeam()[i].getEquipment() instanceof PC) {//判断对象的设备属性的运行类型是否为PC
//						
////						PC pc = (PC)getTeam()[i].getEquipment();//接口类型 变量名 = new 实现类对象;可以将接口引用转换成实现类的引用
////						System.out.println(pc.getClass());//运行类型
//						
//						total++;
//					}
//				}
//					if(total >= 1) {
//						throw new TeamException("团队中至多只能有一名架构师 ");
//					}
//			}
//			if(p.getEquipment() instanceof NoteBook) {
//				int total = 0;
//				for(int i = 0;i < getTeam().length;i++) {//遍历team数组
//					if(getTeam()[i].getEquipment() instanceof NoteBook) {//判断对象的设备属性的运行类型是否为PC
//						total++;
//					}
//				}
//				if(total >= 2) {
//					throw new TeamException("团队中至多只能有两名设计师");
//				}
//			}
//			if(p.getEquipment() instanceof Printer) {
//				int total = 0;
//				for(int i = 0;i < getTeam().length;i++) {//遍历team数组
//					if(getTeam()[i].getEquipment() instanceof Printer) {//判断对象的设备属性的运行类型是否为PC
//						total++;
//					}
//				}
//				if(total >= 3) {
//					throw new TeamException("团队中至多只能有三名程序员 ");
//				}
//			}
			
			System.out.println("正在添加哦");
			if(e instanceof Programmer) {
				Programmer p1 = (Programmer)e;
				team[total++] = p1;//这里是地址传递，总表中的对象和team中的对象指向的是同一个地址
				p.setStatus(Status.BUSY);
				p.setMemberId(counter++); //设置独有的TID、、、这里看一下到底是多少:第一次打印是1，后++，先运算后加1
			}
			
	}
		
		
	public int getTeamProgrammerNumber() {
		String[][] data = Data.EMPLOYEES;
		int total = 0;// 记录数量
		
		Programmer[] team = getTeam();
		
		for (int i = 0; i < team.length; i++) {// 外层循环拿到id后去data数组中一个一个去找
			for (int j = 1; j < data.length; j++) {
				int id = Integer.parseInt(data[j][1]);
				if (team[i].getId() == id) {
					// 找到了，继续比较该type和静态职业变量
					int type = Integer.parseInt(data[j][0]);//找到的team成员的type
					if(type == Data.PROGRAMMER) {// 
						total++;
					}
				}
			}
		}
		return total;
	}
		
	
	public int getTeamDesignerNumber() {
		String[][] data = Data.EMPLOYEES;
		int total = 0;// 记录数量
		
		Programmer[] team = getTeam();
		
		for (int i = 0; i < team.length; i++) {// 外层循环拿到id后去data数组中一个一个去找
			for (int j = 1; j < data.length; j++) {
				int id = Integer.parseInt(data[j][1]);
				if (team[i].getId() == id) {
					// 找到了，继续比较该type和静态职业变量
					int type = Integer.parseInt(data[j][0]);//找到的team成员的type
					if(type == Data.DESIGNER) {// 
						total++;
					}
				}
			}
		}
		return total;
	}
	
	
	public int getTeamArchitectNumber() {
		String[][] data = Data.EMPLOYEES;
		int total = 0;// 记录数量
		
		Programmer[] team = getTeam();
		
		for (int i = 0; i < team.length; i++) {// 外层循环拿到id后去data数组中一个一个去找
			for (int j = 1; j < data.length; j++) {
				int id = Integer.parseInt(data[j][1]);
				if (team[i].getId() == id) {
					// 找到了，继续比较该type和静态职业变量
					int type = Integer.parseInt(data[j][0]);//找到的team成员的type
					if(type == Data.ARCHITECT) {// 
						total++;
					}
				}
			}
		}
		return total;
		
		
		
	}
	
		
		
		
		
		
		
	 
	/**
	 * 
	 * @Description 参数：待删除成员的memberId
					异常：找不到指定memberId的员工，删除失败
					另外，可根据需要自行添加其他方法或重载构造器
	 * @author
	 * @date 2022年11月4日下午1:30:17
	 * @param memberId
	 * @throws TeamException
	 */
	public void removeMember(int memberId) throws TeamException{
		
//		确认是否删除(Y/N)：y
//		删除成功
//		按回车键继续...
		
		System.out.println("确认是否删除(Y/N)");
		char yn = TSUtility.readConfirmSelection();
		
		boolean search = false;//设置一个变量判断有没有找到
		
		int find = 0 ;
		
		if(yn == 'y' || yn == 'Y') {
		for(int i = 0;i < total;i++) {//先进去找，找到，进行删除操作
			if(team[i].getMemberId() == memberId) {//判断team中有没有选中的TID//	NullPointerException//属性没有值的位置上是null
				// 确定它在队伍中是第几个	i
				find = i;
				
				search = true;
			
			//新建一个数组，保存删除一个成员后的数据，
			//当只有一个成员时
				//当有多个成员时
				//后面的元素往前移一个位置
//				把最后的元素置成null
//			直接修改属性team数组的元素
			}
		}
		if(search) {
			
						if(getTeam().length == 1) {//
							team[0].setStatus(Status.FREE);
							team[0] = null;
							total--;
							//设置状态信息，需要设置总表里的状态信息，NameListService在构造器中初始化后的的属性employees 的Status
//							根据team表要删除的员工的id,找到总表里的status,判断team中的对象，指向的是不是总表中的对象
							
							System.out.println("删除成功");
						}else {
							team[find].setStatus(Status.FREE);
							
							for(int i = find;i <= total-2;i++) {//先进去找，找到，进行删除操作,要从memberId，这一项开始删除，前面的不能动
								team[i] = team[i+1];
							}
							team[total - 1] = null;
							total--;
							
							System.out.println("删除成功");
						}
				   }else {
						throw new TeamException("删除失败，原因：找不到该成员，无法删除");
					}
		}
		
		if(yn == 'n' || yn == 'N') {
			return;
		}
		
//		确认是否删除(Y/N)：
//		n	
//		返回主界面
		
//		请输入要删除员工的TID：
//		5
//		确认是否删除(Y/N)：Y
//		删除失败，原因：找不到该成员，无法删除
//		按回车键继续...
		
	}
	
	
	
	
	
	
	
	
	
	

}
