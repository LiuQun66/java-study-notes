package com.aowin.stuff.View;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import com.aowin.stuff.Listener.PersonnelButtonListener;
import com.aowin.stuff.Listener.PersonnelSystemWindowListener;
import com.aowin.stuff.Listener.TableHeaderListener;
/**
 * 人事管理系统窗口
 */
public class PersonnelSystemView {
	public static void PersonnelSystemViewFunction() {
		
		JFrame frame2=new JFrame("人事管理系统");
		Container con2=frame2.getContentPane();
		con2.setLayout(new FlowLayout());
		
		JMenuBar menu1=new JMenuBar();
		JMenu fileMenu=new JMenu("File");
		JMenuItem addMenu=new JMenuItem("添加");
		JMenuItem deleteMenu=new JMenuItem("删除");
		JMenuItem searchMenu=new JMenuItem("查询");
		JMenuItem modifyMenu=new JMenuItem("修改");
		JMenuItem updateMenu=new JMenuItem("更新");
		JMenuItem existMenu=new JMenuItem("退出");
		JMenuItem importMenu=new JMenuItem("导入");
		JMenuItem exportMenu=new JMenuItem("导出");
		fileMenu.add(addMenu);
		fileMenu.add(deleteMenu);
		fileMenu.add(searchMenu);
		fileMenu.add(modifyMenu);
		fileMenu.add(updateMenu);
		fileMenu.add(existMenu);
		fileMenu.add(importMenu);
		fileMenu.add(exportMenu);
		menu1.add(fileMenu);
		frame2.setJMenuBar(menu1);
		JMenu helpMenu=new JMenu("help");
		menu1.add(helpMenu);
		frame2.setJMenuBar(menu1);

		JLabel label=new JLabel("人事管理系统");
		con2.add(label ,BorderLayout.NORTH );
		String[] personnelTable= {"编号","姓名","性别","年龄","所在部门","工资"};
		DefaultTableModel dTabelModel=new DefaultTableModel(null,personnelTable);
		JTable table2=new JTable(dTabelModel);
		JScrollPane scrollPane1=new JScrollPane(table2);
		con2.add(scrollPane1,BorderLayout.CENTER);
		JPanel panel3=new JPanel(new FlowLayout());
		JButton addButton=new JButton("添加");
		panel3.add(addButton);
		JButton modifyButton=new JButton("修改");
		panel3.add(modifyButton);
		JButton deleteButton=new JButton("删除");
		panel3.add(deleteButton);
		JButton searchButton=new JButton("查询");
		panel3.add(searchButton);
		JButton updateButton=new JButton("更新");
		panel3.add(updateButton);
		con2.add(panel3,BorderLayout.SOUTH);

		//进入人事系统窗口触发监听
		PersonnelSystemWindowListener personnelSystemListener=new PersonnelSystemWindowListener(frame2);
		frame2.addWindowListener(personnelSystemListener);
		personnelSystemListener.setTable2(table2);
		
		//点击增、删、改、查等按钮触发监听
		PersonnelButtonListener pbl=new PersonnelButtonListener(table2);
		addButton.addActionListener(pbl);
		modifyButton.addActionListener(pbl);
		deleteButton.addActionListener(pbl);
		searchButton.addActionListener(pbl);
		updateButton.addActionListener(pbl);
		
		//给菜单栏添加监听事件
		addMenu.addActionListener(pbl);
		modifyMenu.addActionListener(pbl);
		deleteMenu.addActionListener(pbl);
		searchMenu.addActionListener(pbl);
		updateMenu.addActionListener(pbl);
		existMenu.addActionListener(pbl);
		importMenu.addActionListener(pbl);
		exportMenu.addActionListener(pbl);
		
		//表头
		JTableHeader tableHead=table2.getTableHeader();
		TableHeaderListener headListener=new TableHeaderListener(table2);
		tableHead.addMouseListener(headListener);

		frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame2.setLocation(400, 75);//设置人事管理系统窗口居中
		frame2.setSize(500, 600);
		frame2.setVisible(true);
	}
}
