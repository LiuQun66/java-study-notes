package com.aowin.stuff.Listener;

import java.awt.Component;
import java.awt.Container;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.aowin.stuff.Dao.Implements.ImplementFunctions;
import com.aowin.stuff.Model.UserAdmin;
import com.aowin.stuff.View.PersonnelSystemView;
/**
 * 管理员登录系统的鼠标点击事件
 * 
 *
 */
public class MyMouseListener extends MouseAdapter{
	private JTextField text1;
	private JPasswordField psw;
	private JTextField text2;
	private JFrame frame;
	public MyMouseListener(JTextField text1,JPasswordField psw,JTextField text2) {
		this.text1=text1;
		this.psw=psw;
		this.text2=text2;
		
	}
	
	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		String buttonName=((JButton)e.getComponent()).getText();
		String reg="^\\w{4,12}$";
		String textContent1=text1.getText();
		String pswContent2=psw.getText();
		if("登录".equals(buttonName)) {
			if(textContent1.matches(reg)&&pswContent2.matches(reg)){
				ImplementFunctions imf=new ImplementFunctions();
				UserAdmin user=imf.searchUserAdmin(textContent1, pswContent2);
				if(user!=null) {
					text2.setText("");
					PersonnelSystemView.PersonnelSystemViewFunction();
					frame.setVisible(false);
					System.out.println("成功进入管理员界面");
				}else {
					text2.setText("账号或密码错误");
				}
			}else {
				text2.setText("请输入4~12位的数字、字母或下划线");
			}
		}else if("重置".equals(buttonName)) {
			text1.setText("");
			psw.setText("");
			text2.setText("");
		}
	}
	
}
