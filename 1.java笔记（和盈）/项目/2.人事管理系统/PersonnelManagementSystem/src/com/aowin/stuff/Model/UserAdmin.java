package com.aowin.stuff.Model;

public class UserAdmin {
	private int id;
	private String name;
	private String password;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public UserAdmin(String name,String password) {
		this.name=name;
		this.password=password;
	}
	
}
