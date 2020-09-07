package com.aowin.stuff.Listener;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.aowin.stuff.Dao.Implements.ImplementFunctions;
import com.aowin.stuff.Model.Person;

public class PersonnelSystemWindowListener extends WindowAdapter{
	private JFrame frame2;
	private JTable table2;
	public JTable getTable2() {
		return table2;
	}

	public void setTable2(JTable table2) {
		this.table2 = table2;
	}

	public PersonnelSystemWindowListener(JFrame frame2) {
		super();
		this.frame2 = frame2;
	}

	@Override
	public void windowOpened(WindowEvent e) {
		ImplementFunctions imf=new ImplementFunctions();
		imf.importInformationsToSystem(table2);
		
		
	}
	
}
