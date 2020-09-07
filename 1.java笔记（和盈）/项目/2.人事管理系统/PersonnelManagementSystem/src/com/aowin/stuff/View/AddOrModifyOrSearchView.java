package com.aowin.stuff.View;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class AddOrModifyOrSearchView {
	private JFrame frame;
	private JTextField numberText1;
	private JTextField nameText2;
	private JComboBox sexComBox;
	private JComboBox departComBox2;
	private JTextField ageText2;
	private JTextField salaryText5;
	private JButton confirmButton1;
	private JButton cancelButton2;
	private JLabel searchlable6;
	private JComboBox andComBox3;
	
	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	public JTextField getNumberText1() {
		return numberText1;
	}

	public void setNumberText1(JTextField numberText1) {
		this.numberText1 = numberText1;
	}

	public JTextField getNameText2() {
		return nameText2;
	}

	public void setNameText2(JTextField nameText2) {
		this.nameText2 = nameText2;
	}

	public JComboBox getSexComBox() {
		return sexComBox;
	}

	public void setSexComBox(JComboBox sexComBox) {
		this.sexComBox = sexComBox;
	}

	public JComboBox getDepartComBox2() {
		return departComBox2;
	}

	public void setDepartComBox2(JComboBox departComBox2) {
		this.departComBox2 = departComBox2;
	}
	
	public JTextField getAgeText2() {
		return ageText2;
	}

	public void setAgeText2(JTextField ageText2) {
		this.ageText2 = ageText2;
	}

	public JTextField getSalaryText5() {
		return salaryText5;
	}

	public void setSalaryText5(JTextField salaryText5) {
		this.salaryText5 = salaryText5;
	}

	public JButton getConfirmButton1() {
		return confirmButton1;
	}

	public void setConfirmButton1(JButton confirmButton1) {
		this.confirmButton1 = confirmButton1;
	}

	public JButton getCancelButton2() {
		return cancelButton2;
	}

	public void setCancelButton2(JButton cancelButton2) {
		this.cancelButton2 = cancelButton2;
	}
	
	public JLabel getSearchlable6() {
		return searchlable6;
	}

	public void setSearchlable6(JLabel searchlable6) {
		this.searchlable6 = searchlable6;
	}

	public JComboBox getAndComBox3() {
		return andComBox3;
	}

	public void setAndComBox3(JComboBox andComBox3) {
		this.andComBox3 = andComBox3;
	}

	public  void addOrModifyOrSearch(String frameName) {
		 frame=new JFrame(frameName);
		Container con=frame.getContentPane();
		con.setLayout(new BorderLayout());
		JPanel panel1=new JPanel(new FlowLayout());
		JLabel label1=new JLabel("编号：");
		panel1.add(label1);
		numberText1=new JTextField(15);
		panel1.add(numberText1);
		JLabel label2=new JLabel("名字：");
		panel1.add(label2);
		nameText2=new JTextField(15);
		panel1.add(nameText2);
		
		JLabel label3=new JLabel("性别：");
		panel1.add(label3);
		String[] boyOrGirl= {"","男","女"};
		sexComBox=new JComboBox(boyOrGirl);
		panel1.add(sexComBox);
		JLabel label4=new JLabel("部门：");
		panel1.add(label4);
		String[] department= {"","vendition","lead","secretary"};
		departComBox2=new JComboBox(department);
		panel1.add(departComBox2);
		
		JLabel ageLabel=new JLabel("年龄：");
		panel1.add(ageLabel);
		ageText2=new JTextField(15);
		panel1.add(ageText2);

		JLabel wagelabel5=new JLabel("工资：");
		panel1.add(wagelabel5);
		salaryText5=new JTextField(15);
		panel1.add(salaryText5);
		 searchlable6=new JLabel("条件查询方式：");
		panel1.add(searchlable6);
		searchlable6.setVisible(false);
		String[] andOr= {"and"};
		 andComBox3=new JComboBox(andOr);
		panel1.add(andComBox3);
		andComBox3.setVisible(false);
		con.add(panel1,BorderLayout.CENTER);

		JPanel panel2=new JPanel(new FlowLayout());
		 confirmButton1=new JButton("确定");
		panel2.add(confirmButton1);
		 cancelButton2=new JButton("取消");
		panel2.add(cancelButton2);
		con.add(panel2 ,BorderLayout.SOUTH );

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocation(900, 200);//设置登录界面居中
		frame.setSize(260, 300);
		frame.setVisible(true);
	}
}
