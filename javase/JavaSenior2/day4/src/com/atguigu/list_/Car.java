package com.atguigu.list_;

import java.util.List;

/**
 * @author yangda
 * @create 2024-04-04-15:02
 * @description:
 */
public class Car {

    private Integer carId;

    private String color;

    private List<Person> persons;

    public Car() {
    }

    public Car(Integer carId, String color, List<Person> persons) {
        this.carId = carId;
        this.color = color;
        this.persons = persons;
    }

    @Override
    public String toString() {
        return "Car{" +
                "carId=" + carId +
                ", color='" + color + '\'' +
                ", persons=" + persons +
                '}';
    }

    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }
}

class Person{
    private Integer personId;

    private String name;

    public Person() {
    }

    public Person(Integer personId, String name) {
        this.personId = personId;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "personId=" + personId +
                ", name='" + name + '\'' +
                '}';
    }

    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}