package com.DaoPackage;
import java.util.List;

import com.ModelPackage.Student;

public interface Dao {
	void insertFunction(Student st);
	List findAllFunction(String ssex);
}
