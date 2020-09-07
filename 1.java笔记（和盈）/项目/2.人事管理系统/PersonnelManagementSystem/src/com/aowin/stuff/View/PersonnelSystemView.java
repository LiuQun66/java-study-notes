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
 * ���¹���ϵͳ����
 */
public class PersonnelSystemView {
	public static void PersonnelSystemViewFunction() {
		
		JFrame frame2=new JFrame("���¹���ϵͳ");
		Container con2=frame2.getContentPane();
		con2.setLayout(new FlowLayout());
		
		JMenuBar menu1=new JMenuBar();
		JMenu fileMenu=new JMenu("File");
		JMenuItem addMenu=new JMenuItem("���");
		JMenuItem deleteMenu=new JMenuItem("ɾ��");
		JMenuItem searchMenu=new JMenuItem("��ѯ");
		JMenuItem modifyMenu=new JMenuItem("�޸�");
		JMenuItem updateMenu=new JMenuItem("����");
		JMenuItem existMenu=new JMenuItem("�˳�");
		JMenuItem importMenu=new JMenuItem("����");
		JMenuItem exportMenu=new JMenuItem("����");
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

		JLabel label=new JLabel("���¹���ϵͳ");
		con2.add(label ,BorderLayout.NORTH );
		String[] personnelTable= {"���","����","�Ա�","����","���ڲ���","����"};
		DefaultTableModel dTabelModel=new DefaultTableModel(null,personnelTable);
		JTable table2=new JTable(dTabelModel);
		JScrollPane scrollPane1=new JScrollPane(table2);
		con2.add(scrollPane1,BorderLayout.CENTER);
		JPanel panel3=new JPanel(new FlowLayout());
		JButton addButton=new JButton("���");
		panel3.add(addButton);
		JButton modifyButton=new JButton("�޸�");
		panel3.add(modifyButton);
		JButton deleteButton=new JButton("ɾ��");
		panel3.add(deleteButton);
		JButton searchButton=new JButton("��ѯ");
		panel3.add(searchButton);
		JButton updateButton=new JButton("����");
		panel3.add(updateButton);
		con2.add(panel3,BorderLayout.SOUTH);

		//��������ϵͳ���ڴ�������
		PersonnelSystemWindowListener personnelSystemListener=new PersonnelSystemWindowListener(frame2);
		frame2.addWindowListener(personnelSystemListener);
		personnelSystemListener.setTable2(table2);
		
		//�������ɾ���ġ���Ȱ�ť��������
		PersonnelButtonListener pbl=new PersonnelButtonListener(table2);
		addButton.addActionListener(pbl);
		modifyButton.addActionListener(pbl);
		deleteButton.addActionListener(pbl);
		searchButton.addActionListener(pbl);
		updateButton.addActionListener(pbl);
		
		//���˵�����Ӽ����¼�
		addMenu.addActionListener(pbl);
		modifyMenu.addActionListener(pbl);
		deleteMenu.addActionListener(pbl);
		searchMenu.addActionListener(pbl);
		updateMenu.addActionListener(pbl);
		existMenu.addActionListener(pbl);
		importMenu.addActionListener(pbl);
		exportMenu.addActionListener(pbl);
		
		//��ͷ
		JTableHeader tableHead=table2.getTableHeader();
		TableHeaderListener headListener=new TableHeaderListener(table2);
		tableHead.addMouseListener(headListener);

		frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame2.setLocation(400, 75);//�������¹���ϵͳ���ھ���
		frame2.setSize(500, 600);
		frame2.setVisible(true);
	}
}
