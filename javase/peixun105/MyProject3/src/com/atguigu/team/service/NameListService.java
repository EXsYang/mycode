package com.atguigu.team.service;


import com.atguigu.team.domain.Architect;
import com.atguigu.team.domain.Designer;
import com.atguigu.team.domain.Employee;
import com.atguigu.team.domain.Equipment;
import com.atguigu.team.domain.NoteBook;
import com.atguigu.team.domain.PC;
import com.atguigu.team.domain.Printer;
import com.atguigu.team.domain.Programmer;

public class NameListService {
	private Status status;
	private Employee[] employee;

	public NameListService() {
		employee = new Employee[Data.EMPLOYEES.length];
		employee = getAllEmployees();
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Employee[] getEmployee() {
		return employee;
	}

	public void setEmployee(Employee[] employee) {
		this.employee = employee;
	}

	public Employee[] getAllEmployees() {// 获取当前所有员工 返回：包含所有员工对象的数组

		// 创建对象数组，存放员工信息
		int type;// 员工类型

		for (int i = 0; i < Data.EMPLOYEES.length; i++) {// 遍历Data中存放的信息，
			// 按照对应的类型，创建对应类型的对象； 在相应类型的对象中，依次填入数组对象中的属性。 Data中保存的数据类型是String；
			// 其中对应位置属性类型不一致的需要进行数据类型转换
			// 将生成的对象放入对象数组中

			type = Integer.parseInt(Data.EMPLOYEES[i][0]);
			// type EMPLOYEE 无职位
			// type = PROGRAMMER 程序员 type = DESIGNER 设计师 ARCHITECT 架构师

			int id = Integer.parseInt(Data.EMPLOYEES[i][1]);

			int age = Integer.parseInt(Data.EMPLOYEES[i][3]);
			double salary = Double.parseDouble(Data.EMPLOYEES[i][4]);
			// Scanner scanner = new Scanner(System.in);
			// int index = scanner.nextInt();
			Equipment equipment;
			double bonus;
			switch (type) {// 按照对应的类型，创建对应类型的对象,用swich-case进行匹配

			case Data.EMPLOYEE:
				employee[i] = new Employee(id, Data.EMPLOYEES[i][2], age, salary);
				//employee[i].setPosition("");
				employee[i].setPosition(getP(employee[i]));//设置职位
				break;
			case Data.PROGRAMMER:
				equipment = getEquipments(i);
				employee[i] = new Programmer(id, Data.EMPLOYEES[i][2], age, salary);
				employee[i].setPosition(getP(employee[i]));//设置职位
				 Programmer pr = (Programmer)employee[i];
				 pr.setEquipmemt(getEquipments(i));//设置员工所使用的设备信息
			
				 
				break;
			case Data.DESIGNER:
				bonus = Double.parseDouble(Data.EMPLOYEES[i][5]);
				employee[i] = new Designer(id, Data.EMPLOYEES[i][2], age, salary, bonus);
				employee[i].setPosition(getP(employee[i]));//设置职位
				Designer de = (Designer) employee[i];
				de.setEquipmemt(getEquipments(i));//设置员工所使用的设备信息
				
				
				break;
			case Data.ARCHITECT:
				bonus = Double.parseDouble(Data.EMPLOYEES[i][5]);
				int stock = Integer.parseInt(Data.EMPLOYEES[i][6]);
				employee[i] = new Architect(id, Data.EMPLOYEES[i][2], age, salary, bonus, stock);
				employee[i].setPosition(getP(employee[i]));//设置职位
				Architect ar = (Architect) employee[i];
				ar.setEquipmemt(getEquipments(i));//设置员工所使用的设备信息
				
				
				break;
			}

		}
		return employee;

	}

	public Equipment getEquipments(int index) {// 获取员工所用的设备
		// 使用for循环遍历设备信息，并添加数据，使用switch-case进行类型匹配，
		// 生成对应的子类对象，将Data中的数据封装进对象的属性中
		// for (int i = 1; i < Data.EQUIPMENTS.length; i++) {
		if (index == 0) {
			return null;
		}
		int tapy = Integer.parseInt(Data.EQUIPMENTS[index][0]);
		switch (tapy) {
		case Data.PC:

			return new PC(Data.EQUIPMENTS[index][1], Data.EQUIPMENTS[index][2]);

		case Data.NOTEBOOK:
			double price = Double.parseDouble(Data.EQUIPMENTS[index][2]);
			return new NoteBook(Data.EQUIPMENTS[index][1], price);
		case Data.PRINTER:
			return new Printer(Data.EQUIPMENTS[index][1], Data.EQUIPMENTS[index][2]);
		default:
			return null;
		}
		// }

	}

	public Employee getEmployee(int id) throws TeamException {
		if (id < 13 && id > 0) {
			
			
			return employee[id - 1];
			
			
		} else {
			throw new TeamException("找不到指定的员工");
		}

	}

	public String getP(Employee e) {
		int type = Integer.parseInt(Data.EMPLOYEES[e.getId() - 1][0]);
		if (type == Data.EMPLOYEE) {
			return "";
		} else if (type == Data.PROGRAMMER) {
			return "程序员";
		} else if (type == Data.DESIGNER) {
			return "设计师";
		} else {
			return "架构师";
		}
	}

	
	public static void main(String[] args) {
		NameListService n = new NameListService();

	}
}
