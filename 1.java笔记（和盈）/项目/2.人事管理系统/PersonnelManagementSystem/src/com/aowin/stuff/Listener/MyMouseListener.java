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
 * ����Ա��¼ϵͳ��������¼�
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
		if("��¼".equals(buttonName)) {
			if(textContent1.matches(reg)&&pswContent2.matches(reg)){
				ImplementFunctions imf=new ImplementFunctions();
				UserAdmin user=imf.searchUserAdmin(textContent1, pswContent2);
				if(user!=null) {
					text2.setText("");
					PersonnelSystemView.PersonnelSystemViewFunction();
					frame.setVisible(false);
					System.out.println("�ɹ��������Ա����");
				}else {
					text2.setText("�˺Ż��������");
				}
			}else {
				text2.setText("������4~12λ�����֡���ĸ���»���");
			}
		}else if("����".equals(buttonName)) {
			text1.setText("");
			psw.setText("");
			text2.setText("");
		}
	}
	
}
