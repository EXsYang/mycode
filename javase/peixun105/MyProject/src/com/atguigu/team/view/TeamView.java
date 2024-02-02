package com.atguigu.team.view;

import com.atguigu.team.domain.Employee;
import com.atguigu.team.domain.Programmer;
import com.atguigu.team.service.NameListService;
import com.atguigu.team.service.TeamException;
import com.atguigu.team.service.TeamService;

/**
 * 
 * @Description 说明： listSvc和teamSvc属性：供类中的方法使用
 * @author yangda Email:909533571qq.com
 * @version
 * @date 2022年11月4日下午1:36:00
 *
 */
public class TeamView {
	private NameListService listSvc = new NameListService();
	private TeamService teamSvc = new TeamService();

	// 主界面显示及控制方法
	public void enterMainMenu() {

		
		boolean label = true;
		do {

			// 1 马 云 22 3000.0
			// 2 马化腾 32 18000.0 架构师 FREE 15000.0 2000 联想T4(6000.0)
			// 3 李彦宏 23 7000.0 程序员 FREE 戴尔(NEC17寸)
			// 4 刘强东 24 7300.0 程序员 FREE 戴尔(三星 17寸)
			// 5 雷军 28 10000.0 设计师 FREE 5000.0 佳能 2900(激光)
			// ……

			System.out.println(
					"-------------------------------开发团队调度软件--------------------------------");
			System.out.println();
			System.out.println("ID	姓名	年龄	工资	职位	状态	奖金	股票	领用设备");
			
			listAllEmployees();//获取成员列表
			System.out.println("-------------------------------------------------------------------------------");
		
			
			while(label) {
//				System.out.println("-------------------------------------------------------------------------------");
				System.out.println("1-团队列表  2-添加团队成员  3-删除团队成员 4-退出   请选择(1-4)： _");
				char c = TSUtility.readMenuSelection();// 用户选择1~4
//			TSUtility.readReturn();// 该方法提示并等待，直到用户按回车键后返回。
				
				switch(c) {
				case '1' :
					
					getTeam();
					
					break;
				case '2' :
					addMember();
					enterMainMenu();
					break;
				case '3' :
					deleteMember();
					enterMainMenu();
					break;
				case '4' :
					System.out.println("确认是否退出(Y/N)：");
					char yn = TSUtility.readConfirmSelection();
					if(yn == 'Y') {//直接退出
						label = false;
					}else if(yn == 'N') {//返回主界面
						enterMainMenu();
					}else {
						System.out.println("选择错误，请重新输入：");
					}
					break;
					
				}
			}
			

		} while (label);

	}

	// 以下方法仅供enterMainMenu()方法调用：
	// 以表格形式列出公司所有成员
	private void listAllEmployees() {
		
		for(int i = 0 ; i < listSvc.getEmployees().length ; i++) {//
			
			System.out.println(listSvc.getEmployees()[i]);
		}		
	}

	// 显示团队成员列表操作
	private void getTeam() {

//--------------------团队成员列表---------------------
//
//开发团队目前没有成员！
//-----------------------------------------------------
//1-团队列表  2-添加团队成员  3-删除团队成员 4-退出   请选择(1-4)：
			System.out.println("--------------------团队成员列表---------------------");
			System.out.println();
			if(teamSvc.getTotal() == 0) {
				System.out.println("开发团队目前没有成员！");
				System.out.println("-----------------------------------------------------");
			}else {
				System.out.println("TID/ID\t姓名\t年龄\t工资\t职位\t奖金\t股票");
				for(Programmer p : teamSvc.getTeam()) {
					System.out.println(p.tString());
				}
			}
			//在下面遍历数组
				
	}

	// 实现添加成员操作
	private void addMember() {
		
		//先用普通方法实现，再修改成抛异常的方式实现

//		--------------------团队成员列表---------------------
//
//		TID/ID	姓名	年龄	工资	职位	奖金	股票
//		 1/2	马化腾	32	18000.0	架构师	15000.0	2000
//		 2/3	李彦宏	23	7000.0	程序员
//		 3/4	刘强东	24	7300.0	程序员
//		-----------------------------------------------------
//		1-团队列表  2-添加团队成员  3-删除团队成员 4-退出   请选择(1-4)：
		
		System.out.println("---------------------添加成员---------------------");		
		System.out.println("请输入要添加的员工ID：");
		int readint = TSUtility.readInt();
		//根据员工ID 找到 所属的对象的数据类型
		Employee[] e = listSvc.getEmployees();//listSvc的属性，对象数组Employee[]			这里是总表是listSvc对象的一个属性
//		private Programmer[] team = new Programmer[MAX_MEMBER];
			
		//先判断需要添加的员工在不在本团队中
		//第一次添加肯定team数组是空的，只要调用，就给他设定成1
			
//			Programmer[] team = teamSvc.getTeam();//这个是新建的数组，里面是真实的保存的team属性的对象，最大为5的数组
			//根据该成员的Status是BUSY
			//if(teamSvc.getTotal() == teamSvc.),成员已满的操作只能去TeamService类进行操作和提示信息了
//			for (int i = 0; i < team.length; i++) {
//				if(team[i].getId() == readint) {//输入的值和真实团队数组中的ID去比较
//					System.out.println("添加失败，原因：该员工已在本团队中");
//					TSUtility.readReturn();
//					enterMainMenu();
//				}
//		    }
//				if(e[readint - 1].getId() == readint) {
//					if( e[readint - 1] instanceof Programmer) {
						try {
							teamSvc.addMember(e[readint - 1]);//1产生异常对象
							TSUtility.readReturn();
							enterMainMenu();

						} catch (TeamException e1) {//2 抓取异常		目前没有抛出异常，直接进入了finally执行
							System.out.println("添加失败，原因：" + e1.getMessage());//需要自己输出一下
//							e1.printStackTrace();
							TSUtility.readReturn();
							enterMainMenu();
						}
//						finally {
//						TSUtility.readReturn();
//						enterMainMenu();	
//						}
//						System.out.println("添加成功！");
//						TSUtility.readReturn();
//					}else {
//						System.out.println("添加失败，原因：该成员不是开发人员，无法添加");
//						TSUtility.readReturn();
//					}
//				}
//				}else {
//					System.out.println("添加失败，原因：该员工不存在");
//					TSUtility.readReturn();
//				}
		}
//		private Programmer[] team = new Programmer[MAX_MEMBER];往该数组中添加数据
		
		
//		请输入要添加的员工ID：1
//		添加失败，原因：该成员不是开发人员，无法添加
//		按回车键继续...
		//直接返回主界面
		
//		请输入要添加的员工ID：2
//		添加成功
//		按回车键继续...
		
//		teamSvc.getTeam()
		


	
	// 实现删除成员操作
	private void deleteMember() {
		System.out.println("---------------------删除成员---------------------\r\n" + 
				"请输入要删除员工的TID：");
		int readint = TSUtility.readInt();
//		Employee[] e = listSvc.getEmployees();//listSvc的属性，对象数组Employee[]

					try {
						teamSvc.removeMember(readint);
						TSUtility.readReturn();
						enterMainMenu();
			
					} catch (TeamException e1) {//2 抓取异常		目前没有抛出异常，直接进入了finally执行
						System.out.println("删除失败，原因：" + e1.getMessage());//需要自己输出一下
			//			e1.printStackTrace();
						TSUtility.readReturn();
						enterMainMenu();
					}
		
	}

	
	public static void main(String[] args) {
		TeamView t = new TeamView();
		t.enterMainMenu();
	}

}
