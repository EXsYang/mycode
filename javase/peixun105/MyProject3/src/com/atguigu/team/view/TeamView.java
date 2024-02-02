package com.atguigu.team.view;

import com.atguigu.team.domain.Employee;
import com.atguigu.team.domain.Programmer;
import com.atguigu.team.service.NameListService;
import com.atguigu.team.service.Status;
import com.atguigu.team.service.TeamException;
import com.atguigu.team.service.TeamService;

public class TeamView {

	
	private NameListService listSvc = new NameListService();
	private TeamService teamSvc = new TeamService();
	
	
	
	
	public NameListService getListSvc() {
		return listSvc;
	}

	public void setListSvc(NameListService listSvc) {
		this.listSvc = listSvc;
	}

	public TeamService getTeamSvc() {
		return teamSvc;
	}

	public void setTeamSvc(TeamService teamSvc) {
		this.teamSvc = teamSvc;
	}

	static char a = 0;
	public void enterMainMenu() {
		boolean isFlag = true;
		while(isFlag){
			if(a != '1') {//注意这里是字符型的1，需要加''
				System.out.println("-------------------------------开发团队调度软件--------------------------------");
				System.out.println();
				System.out.println("ID	姓名	年龄	工资	职位	状态	奖金	股票	领用设备");
			listAllEmployees();
			
			System.out.println("-------------------------------------------------------------------------------");
		}
			System.out.println("1-团队列表  2-添加团队成员  3-删除团队成员 4-退出   请选择(1-4)：");
			a = TSUtility.readMenuSelection();
			
//			-------------------------------开发团队调度软件--------------------------------
//
//			ID	姓名	年龄	工资	职位	状态	奖金	股票	领用设备
//			 1	马云	22	3000.0
//			 2	马化腾	32	18000.0	架构师	FREE	15000.0	2000	联想T4(6000.0)
//			 3	李彦宏	23	7000.0	程序员	FREE			戴尔(NEC17寸)
//			 4	刘强东	24	7300.0	程序员	FREE			戴尔(三星 17寸)
//			 5	雷军	28	10000.0	设计师	FREE	5000.0		激光(佳能 2900)
//			 6	任志强	22	6800.0	程序员	FREE			华硕(三星 17寸)
//			 7	柳传志	29	10800.0	设计师	FREE	5200.0		华硕(三星 17寸)
//			 8	杨元庆	30	19800.0	架构师	FREE	15000.0	2500	针式(爱普生20K)
//			 9	史玉柱	26	9800.0	设计师	FREE	5500.0		惠普m6(5800.0)
//			 10	丁磊	21	6600.0	程序员	FREE			戴尔(NEC 17寸)
//			 11	张朝阳	25	7100.0	程序员	FREE			华硕(三星 17寸)
//			 12	杨致远	27	9600.0	设计师	FREE	4800.0		惠普m6(5800.0)
//			-------------------------------------------------------------------------------
//			1-团队列表  2-添加团队成员  3-删除团队成员 4-退出   请选择(1-4)：
			
			
			
		switch(a) {
		case '1':
			getTeam();
			
			break;
		case '2':
			addMember();
			break;
		case '3':
			deleteMember();
			break;
		case '4':
			System.out.println("你确定退出吗？Y/N");
			char c = TSUtility.readConfirmSelection();
			if(c == 'Y') {
				isFlag = false;
			}else {
				isFlag = true;
			}
			break;
		}
		}
		
		
		
		
		
		
		
	}
	
	private void listAllEmployees() {//以表格形式列出公司所有成员
		
		
//		for(int i = 0 ; i < listSvc.getAllEmployees().length ; i++) {//这么写每调用一次就把之前的
//			
//			System.out.println(listSvc.getAllEmployees()[i]);
//		}
		for(int i = 0 ; i < listSvc.getEmployee().length ; i++) {
			
			System.out.println(listSvc.getEmployee()[i]);
		}
	}
	
	private void getTeam() {//显示团队成员列表操作
		System.out.println("--------------------团队成员列表--------------------");
		System.out.println("TDI/ID  姓名    年龄      工资       职位      奖金        股票 ");
		//获取team对象数组,遍历数组
		if(teamSvc.getTotal() == 0) {
			System.out.println("开发团队目前没有成员！");
		}
		Programmer[] team = new Programmer[teamSvc.getTotal()];//总是只添加一条数据，数组长度是5，这里是赋值操作，并没有新建数组去接收
//		System.out.println("total:"+teamSvc.getTotal());
//		System.out.println("teamSvc属性地址值:"+teamSvc);
		//teamSvc属性默认地址值:com.atguigu.team.service.TeamService@28d93b30
		for(int i = 0;i<team.length;i++) {
			team[i] = teamSvc.getTeam()[i];
		}
		for(int i = 0;i<team.length;i++) {
			System.out.println(team[i].List());
		}
		System.out.println("-------------------------------------------------");
	}
	
	private void addMember() {//实现添加成员操作
		System.out.println("--------------------添加成员--------------------");
		System.out.println("请输入要添加的员工ID:");//获取索引员工信息
		//根据员工ID找到对象数组中的员工对象
		try {
			int index = TSUtility.readInt();//输入员工ID
			//创建一个长度为team数组员工个数的对象数组，接收添加到team数组的员工信息
			//Programmer[] teamCopy = new Programmer[t.getCounter()];
			//将该员工添加到team对象数组中
			teamSvc.addMember(listSvc.getEmployee(index));//这里是TeamService类的teamSvc对象调用的，方法里传进去的是listSvc的对象数组元素
			System.out.println("-------------------------------------------------------------------------------");
			System.out.println("1-团队列表  2-添加团队成员  3-删除团队成员 4-退出   请选择(1-4)：");
//			TSUtility.readReturn();
			
			
		} catch (TeamException e) {
			System.out.println(e.getMessage());
			TSUtility.readReturn();
		}
		
		
		
		
	}
	
	private  void deleteMember() {//实现删除成员操作
		System.out.println("--------------------删除成员--------------------");
		System.out.println("请输入要删除的员工ID:");//获取索引员工信息
		try {
			int memberId = TSUtility.readInt();//输入员工ID
			teamSvc.removeMember(memberId);
			
			TSUtility.readReturn();
			
			
		} catch (TeamException e) {
			System.out.println(e.getMessage());
			TSUtility.readReturn();
		}
		
	}
	
	public static void main(String[] args) {
		TeamView t = new TeamView();
		t.enterMainMenu();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
