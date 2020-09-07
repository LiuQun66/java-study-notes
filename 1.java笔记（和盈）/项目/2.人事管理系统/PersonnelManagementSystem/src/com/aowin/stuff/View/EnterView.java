package com.aowin.stuff.View;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.aowin.stuff.Listener.MyMouseListener;
/**
 *����Ա��¼���棺
 * 
 * */
public class EnterView {
	public static void enterViewFunction() {
		JFrame frame=new JFrame("��¼����");
		Container con=frame.getContentPane();
		con.setLayout(new BorderLayout());
		JPanel panel1=new JPanel(new FlowLayout());
		JLabel label1=new JLabel("�û���");
		panel1.add(label1);
		JTextField text1=new JTextField(15);
		panel1.add(text1);
		JLabel label2=new JLabel("���룺");
		panel1.add(label2);
		JPasswordField psw=new JPasswordField(15);
		panel1.add(psw);
		JTextField text2=new JTextField(19);
		text2.setHorizontalAlignment(JTextField.CENTER);//����JTextField�е��ı�����
		text2.setEditable(false);
		text2.setBorder(null);
		panel1.add(text2);
		con.add(panel1,BorderLayout.CENTER);
		JPanel panel2=new JPanel(new FlowLayout());
		JButton button1=new JButton("��¼");
		panel2.add(button1);
		JButton button2=new JButton("����");
		panel2.add(button2);
		MyMouseListener myMouse=new MyMouseListener(text1,psw,text2);
		myMouse.setFrame(frame);
		button1.addMouseListener(myMouse);
		button2.addMouseListener(myMouse);
		con.add(panel2,BorderLayout.SOUTH);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocation(600, 200);//���õ�¼�������
		frame.setSize(230, 200);
		frame.setVisible(true);
	}
}
