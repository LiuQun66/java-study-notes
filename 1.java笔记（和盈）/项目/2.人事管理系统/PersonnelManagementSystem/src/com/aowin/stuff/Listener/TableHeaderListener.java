package com.aowin.stuff.Listener;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.aowin.stuff.Model.Person;
import com.aowin.stuff.Utils.Utils;

public class TableHeaderListener extends MouseAdapter {
	private JTable table2;

	public TableHeaderListener(JTable table2) {
		super();
		this.table2 = table2;
	}
	/**
	 * �������ϵͳ�ı�ͷ�Ա���е����ݽ�����������
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		DefaultTableModel dtm = (DefaultTableModel) table2.getModel();
		Point po = e.getPoint();
		int columnIndex = table2.columnAtPoint(po);
		String columnName = table2.getColumnName(columnIndex);
		ArrayList<Person> alist = new ArrayList<Person>();
		Person per = null;
		for (int i = 0; i < dtm.getRowCount(); i++) {
			int id=Integer.parseInt((String)dtm.getValueAt(i, 0));
			String name = (String) dtm.getValueAt(i, 1);
			int sex = 0;
			if ("��".equals(dtm.getValueAt(i, 2))) {
				sex = 1;
			}
			int age = Integer.parseInt((String)dtm.getValueAt(i, 3));
			String department = (String) dtm.getValueAt(i, 4);
			int salary = Integer.parseInt((String) dtm.getValueAt(i, 5));
			per = new Person(id, name, sex, age, department, salary);
			alist.add(per);
		}
		int numClick=e.getClickCount();
		Comparator<Person> com = new Comparator <Person> () {
			@Override
			public int compare(Person o1, Person o2) {
				if("���".equals(columnName)) {
					if(numClick%2==1) {
						return o1.getId()-o2.getId();
					}else {
						return o2.getId()-o1.getId();
					}
				}else if("����".equals(columnName)) {
					if(numClick%2==1) {
						return o1.getName().compareTo(o2.getName());
					}else {
						return o2.getName().compareTo(o1.getName());
					}	
				}else if("�Ա�".equals(columnName)) {
					if(numClick%2==1) {
						return o1.getSex()-o2.getSex();
					}else {
						return o2.getSex()-o1.getSex();
					}
				}else if("����".equals(columnName)) {
					if(numClick%2==1) {
						return o1.getAge()-o2.getAge();
					}else {
						return o2.getAge()-o1.getAge();
					}
				}else if("���ڲ���".equals(columnName)) {
					if(numClick%2==1) {
						return o1.getDepartment().compareTo(o2.getDepartment());
					}else {
						return o2.getDepartment().compareTo(o1.getDepartment());
					}	
				} else{
					if(numClick%2==1) {
						return o1.getSalary()-o2.getSalary();
					}else {
						return o2.getSalary()-o1.getSalary();
					}
				}
				
			}
		};
		Collections.sort(alist, com);
		dtm.setRowCount(0);
		for(Person p:alist) {
			Utils.addSoloInformationsToSystem(p, dtm);
		}
	}
	

}


