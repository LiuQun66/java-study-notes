package com.aowin.stuff.Model;

public class Person {
	private int id;
	private String name;
	private int sex;
	private int age;
	private String department;
	private int salary;
	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public Person(int id, String name, int sex,int age, String department, int salary) {
		super();
		this.id = id;
		this.name = name;
		this.sex = sex;
		this.age=age;
		this.department = department;
		this.salary = salary;
	}
}
