package com.MainTestPackage;

import java.util.List;

import com.DaoPackage.ImplementsPackage.ImplementsOfDao;
import com.ModelPackage.Student;

public class MainTest {
	public static void main(String[] args) {
		ImplementsOfDao iod=new ImplementsOfDao();
		iod.insertFunction(new Student(107,"С��","��","68kg"));
		List ls=iod.findAllFunction("Ů");
		for(Object o:ls) {
			System.out.println(o);
		}}}
