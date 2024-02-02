package com.atguigu.map_;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * @author yangda
 * @description:
 * @create 2022-11-16-15:49
 */
public class MapExer {
    public static void main(String[] args) {
        HashMap map = new HashMap();
        map.put(1,new Employee("yangda",118000,1));
        map.put(2,new Employee("麻花",4000,2));
        map.put(3,new Employee("华生",36000,3));

        Set set = map.keySet();
        for (Object key : set) {
            Employee employee = (Employee) map.get(key);
            if (employee.getSalary() > 18000){
                System.out.println(employee);
            }
        }

        Set set1 = map.entrySet();
        Iterator iterator = set1.iterator();
        while (iterator.hasNext()){
            Object entry = iterator.next();
            Map.Entry entry1 = (Map.Entry)entry;
            Employee value = (Employee) entry1.getValue();
            if ( value.getSalary() > 18000){
                System.out.println("工资大于18000的员工：" + value);
            }
        }


    }
}
class Employee{
    String name;
    double salary;//工资
    int ID ;

    public Employee(String name, double salary, int ID) {
        this.name = name;
        this.salary = salary;
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", salary=" + salary +
                ", ID=" + ID +
                '}';
    }
}