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
 *管理员登录界面：
 * 
 * */
public class EnterView {
	public static void enterViewFunction() {
		JFrame frame=new JFrame("登录窗口");
		Container con=frame.getContentPane();
		con.setLayout(new BorderLayout());
		JPanel panel1=new JPanel(new FlowLayout());
		JLabel label1=new JLabel("用户：");
		panel1.add(label1);
		JTextField text1=new JTextField(15);
		panel1.add(text1);
		JLabel label2=new JLabel("密码：");
		panel1.add(label2);
		JPasswordField psw=new JPasswordField(15);
		panel1.add(psw);
		JTextField text2=new JTextField(19);
		text2.setHorizontalAlignment(JTextField.CENTER);//设置JTextField中的文本居中
		text2.setEditable(false);
		text2.setBorder(null);
		panel1.add(text2);
		con.add(panel1,BorderLayout.CENTER);
		JPanel panel2=new JPanel(new FlowLayout());
		JButton button1=new JButton("登录");
		panel2.add(button1);
		JButton button2=new JButton("重置");
		panel2.add(button2);
		MyMouseListener myMouse=new MyMouseListener(text1,psw,text2);
		myMouse.setFrame(frame);
		button1.addMouseListener(myMouse);
		button2.addMouseListener(myMouse);
		con.add(panel2,BorderLayout.SOUTH);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocation(600, 200);//设置登录界面居中
		frame.setSize(230, 200);
		frame.setVisible(true);
	}
}
