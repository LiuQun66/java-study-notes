package com.ModelPackage;

public class Student {
	private int id;
	private String name;
	private String sex;
	private String weight;
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
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getWeight() {
		return weight;
	}
	public void setWeight(String weight) {
		this.weight = weight;
	}
	public Student(int id, String name, String sex, String weight) {
		super();
		this.id = id;
		this.name = name;
		this.sex = sex;
		this.weight = weight;
	}
	public Student() {
	}
	@Override
	public String toString() {
		return "Student[id:"+id+" name:"+name+" sex:"+sex+" weight:"+weight+"]";
	}
}
