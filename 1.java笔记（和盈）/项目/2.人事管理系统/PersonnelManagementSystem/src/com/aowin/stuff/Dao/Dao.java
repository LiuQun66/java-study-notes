package com.aowin.stuff.Dao;

import java.util.ArrayList;

import com.aowin.stuff.Model.Person;
import com.aowin.stuff.Model.UserAdmin;

public interface Dao {
	UserAdmin searchUserAdmin(String name,String password);
	ArrayList getPersonInformations();
	void addPersonInformationsToDatabases(Person p);
	void deleteManySystemAndDatabasesInformations(int[] deleteIds);
	ArrayList<Person> searchInformationsFromPersonnelSystem(ArrayList al);
	ArrayList getIdsFromDatabases();
}
