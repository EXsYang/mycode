package com.atguigu.team.service;

import java.io.PrintWriter;

import org.junit.Test;

import com.atguigu.team.domain.Architect;
import com.atguigu.team.domain.Designer;
import com.atguigu.team.domain.Employee;
import com.atguigu.team.domain.Equipment;
import com.atguigu.team.domain.NoteBook;
import com.atguigu.team.domain.PC;
import com.atguigu.team.domain.Printer;
import com.atguigu.team.domain.Programmer;

public class NameListService {
	// 在NameListService类中临时添加一个main方法中，作为单元测试方法。
	// 在方法中创建NameListService对象，然后分别用模拟数据调用该对象的各个方法，以测试是否正确。
	// 注：测试应细化到包含了所有非正常的情况，以确保方法完全正确。
	// 重复1-3步，完成TeamService类的开发
	public static void main(String[] args) {
		
		NameListService namelist = new NameListService();//同一个类中可以直接调用
//		System.out.println(namelist.employees);
		Employee[] emp = namelist.getAllEmployees();
		for (int i = 0; i < emp.length; i++) { 	
			System.out.println(emp[i]);
		}
//		
		
//		String[][] s = Data.EQUIPMENTS;
//		
//		for(int i = 0;i < s.length;i++) {
//			for(int j = 0;j < s[i].length;j++) {
//				System.out.print(s[i][j] + " ");
//			}
//			System.out.println();
//		}
		
		
//			Equipment[] e = namelist.getAllEquipment();
//			if(e[0] == null) {
//				System.out.println();
//			}
//			for (int j = 1; j < e.length; j++) {
//				System.out.println(e[j].getDescription());
//				
//			}
			
	}
	@Test
	public void main() {
	}

	private Employee[] employees;// 员工们的数组
	
//	用id去找对应的设备
//	将所有的设备放在一个数组，id 对应，去点employee对象对应的设备

	// public static final String[][] EMPLOYEES = {
	// {"10", "1", "马云", "22", "3000"},
	// {"13", "2", "马化腾", "32", "18000", "15000", "2000"},
	// {"11", "3", "李彦宏", "23", "7000"},
	// {"11", "4", "刘强东", "24", "7300"},
	// {"12", "5", "雷军", "28", "10000", "5000"},
	// {"11", "6", "任志强", "22", "6800"},
	// {"12", "7", "柳传志", "29", "10800","5200"},
	// {"13", "8", "杨元庆", "30", "19800", "15000", "2500"},
	// {"12", "9", "史玉柱", "26", "9800", "5500"},
	// {"11", "10", "丁磊", "21", "6600"},
	// {"11", "11", "张朝阳", "25", "7100"},
	// {"12", "12", "杨致远", "27", "9600", "4800"}
	// };

	public NameListService() {
		this.employees = getAllEmployees();//创建listSvc对象时，在构造器中对employees属性进行初始化，此时拿到所有的对象数据
	}
	public NameListService(Employee[] employees) {// 构造器，创建姓名列表对象，只能有一份
		this.employees = employees;
	}

public Employee[] getAllEmployees() {
	
	//将Employee对象与Equipment对象变成一个新的employee对象
	//其中Equipment对象可以当作Employee对象的属性
	//想办法用Employee对象.equipment = Equipment对象
	
//	先将Employee对象向下转型成为Programmer对象，因为只有Programmer类及其子类才有Equipment属性
	
	
	String[][] employees = Data.EMPLOYEES;
	Equipment[] equ = getAllEquipment();
	
//	Employee[] emps = this.employees;// java.lang.NullPointerException
	//这里的属性值为null
	
	Employee[] emps = new Employee[employees.length];
	for(int i = 0;i < employees.length;i++) {//遍历数组，按条件将String[]数组转换成Employee[]
			//如果第0个元素是    
//			public static final int EMPLOYEE = 10;
//		    public static final int PROGRAMMER = 11;
//		    public static final int DESIGNER = 12;
//		    public static final int ARCHITECT = 13;
			//这里拿到的是一组String型数组,将其转换为对应类型的数组，根据该数组第一个元素去做匹配，返回一个对应类型的数组，
			//拿到对应类型的数组后将其放入整体的对象数组employee[]中
		
			
				int s = Integer.parseInt(employees[i][0]);
				if(s == Data.EMPLOYEE) {//这里可以改成switch-case
					emps[i] = changeEmployee(employees[i]);
					//这里是null的第i号位置
//					Null pointer access: The variable emps can only be null at this location
					//空指针访问:变量emps在这个位置只能为空 
				}
					//创建对应的数组，存放数据
				if(s == Data.PROGRAMMER) {
					emps[i] = changeProgrammer(employees[i]);
				}
				if(s == Data.DESIGNER) {
					emps[i] = changeDesigner(employees[i]);
				}
				if(s ==Data.ARCHITECT) {
					emps[i] = changeArchitect(employees[i]);
				}
				
			}
//	
		for(int i = 0;i < emps.length;i++) {
			if(emps[i] instanceof Programmer) {//因为emps[0]不是Programmer，所以需要判断一下，这样一来直接跳过0
				
				//编译类型：Programmer		运行类型：Programmer  运行时直接走Programmer类中的结构setEquipment()
				Programmer p = (Programmer)emps[i];//向下转型，只能强转父类的引用，不能强转父类的对象，它该是什么就还是什么，这里p还是各自
				//(PC、NoteBook、Printer)的类型
				p.setEquipment(equ[i]);//给所有的对象设置设备信息
			}
		}
	
	return emps;
}

	// 写方法，将不同类型的数据，存入对应的数组中
	public Employee changeEmployee(String[] employee) {
		// {"10", "1", "马云", "22", "3000"},
		// EMPLOYEE = 10;
		// public Employee(int id,String name,int age,double salary) {
		int id = 0;
		String name = null;
		int age = 0;
		double salary = 0;
		for (int i = 0; i < employee.length; i++) {
			if (i == 1) {
				id = Integer.parseInt(employee[i]);
			}
			if (i == 2) {
				name = employee[i];
			}
			if (i == 3) {
				age = Integer.parseInt(employee[i]);
			}
			if (i == 4) {
				salary = Double.parseDouble(employee[i]);
			}
		}
		Employee e = new Employee(id, name, age, salary);
		return e;
	}

	public Programmer changeProgrammer(String[] programmer) {
		int id = 0;
		String name = null;
		int age = 0;
		double salary = 0;

		for (int i = 0; i < programmer.length; i++) {
			if (i == 1) {
				id = Integer.parseInt(programmer[i]);
			}
			if (i == 2) {
				name = programmer[i];
			}
			if (i == 3) {
				age = Integer.parseInt(programmer[i]);
			}
			if (i == 4) {
				salary = Double.parseDouble(programmer[i]);
			}
		}

		Programmer p = new Programmer(id, name, age, salary);
		return p;
	}

	// Designer : 12, id, name, age, salary, bonus
	public Designer changeDesigner(String[] designer) {
		int id = 0;
		String name = null;
		int age = 0;
		double salary = 0;
		double bonus = 0.0;//奖金

		for (int i = 0; i < designer.length; i++) {
			if (i == 1) {
				id = Integer.parseInt(designer[i]);
			}
			if (i == 2) {
				name = designer[i];
			}
			if (i == 3) {
				age = Integer.parseInt(designer[i]);
			}
			if (i == 4) {
				salary = Double.parseDouble(designer[i]);
			}
			if (i == 5) {
				bonus = Double.parseDouble(designer[i]);
			}
		}

		Designer d = new Designer(id, name, age, salary, bonus);
		return d;
	}

	public Architect changeArchitect(String[] architect) {

		int id = 0;
		String name = null;
		int age = 0;
		double salary = 0;
		double bonus = 0.0;
		int stock = 0;
		for (int i = 0; i < architect.length; i++) {
			if (i == 1) {
				id = Integer.parseInt(architect[i]);
			}
			if (i == 2) {
				name = architect[i];
			}
			if (i == 3) {
				age = Integer.parseInt(architect[i]);
			}
			if (i == 4) {
				salary = Double.parseDouble(architect[i]);
			}
			if (i == 5) {
				bonus = Double.parseDouble(architect[i]);
			}
			if (i == 6) {
				stock = Integer.parseInt(architect[i]);
			}
		}

		Architect a = new Architect(id, name, age, salary, bonus, stock);
		return a;
	}

	
//		public static final int PC = 21;
//	    public static final int NOTEBOOK = 22;
//	    public static final int PRINTER = 23;
//	    
	  //PC      :21, model, display
	    //NoteBook:22, model, price
	    //Printer :23, name, type 
		
//	    public static final String[][] EQUIPMENTS = {
//	            {},
//	            {"22", "联想T4", "6000"},
//	            {"21", "戴尔", "NEC17寸"},
//	            {"21", "戴尔", "三星 17寸"},
//	            {"23", "佳能 2900", "激光"},
//	            {"21", "华硕", "三星 17寸"},
//	            {"21", "华硕", "三星 17寸"},
//	            {"23", "爱普生20K", "针式"},
//	            {"22", "惠普m6", "5800"},
//	            {"21", "戴尔", "NEC 17寸"},
//	            {"21", "华硕","三星 17寸"},
//	            {"22", "惠普m6", "5800"}
//	        };
	public Equipment[] getAllEquipment() {//对象.  此时对象的属性已经初始化了，应该有值才对,获取所有的设备信息
		Equipment[] e = new Equipment[Data.EQUIPMENTS.length];
		String[][] s = Data.EQUIPMENTS;
		
//		for(int i = 0;i < s.length;i++) {
//		//	System.out.println(s[i]);
//		}
//			e[i] = s[i];
			e[0] = null;//写不写都行，本身创建时就是null
		for (int i = 1; i < s.length; i++) {
//			if(s[i] == null) {//String型数组中 	{} 也有地址值，不为空
//				e[i] = null;
//				continue;
//			}
			
			int eid = Integer.parseInt(s[i][0]);
			if(eid == Data.PC) {
				 e[i] = new PC(s[i][1],s[i][2]);
			}
			if(eid == Data.NOTEBOOK) {
//				public NoteBook(String model,double price) {
				double price = Double.parseDouble(s[i][2]);
				e[i] = new NoteBook(s[i][1],price);
				
			}
			if(eid == Data.PRINTER) {
				e[i] = new Printer(s[i][1],s[i][2]);
				
			}
		}
		return e;
		
		
		
		
		
		
		
		
		
		
		
	}
	
	public Employee[] getEmployees() {
		return employees;
	}
	public void setEmployees(Employee[] employees) {
		this.employees = employees;
	}
	
	public Employee getEmployee(int id) throws TeamException {
		//通过指定id 返回对应employee对象
//		int i ;
//		for( i = 0;i < employees.length;i++) {
//			//遍历对象数组，找到特定位置上的id属性，进行比较，若相同则返回该对象
//			if(employees[i].getId() == id) {
//				
//			}else {
//				throw new TeamException("没找到成员");
//			}
//		}
//		return employees[i];
		for( int i = 0;i < employees.length;i++) {
			if(employees[i].getId() == id) {
				return employees[i];
			}
		}
		throw new TeamException("没找到成员");
	}
}
