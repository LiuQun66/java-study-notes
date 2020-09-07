package com.aowin.stuff.Utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import com.aowin.stuff.Dao.Implements.ImplementFunctions;
import com.aowin.stuff.Model.Person;
import com.aowin.stuff.View.AddOrModifyOrSearchView;

public class Utils {
	/**
	 * 获得jdbc的连接
	 * */
	public static Connection getConnectionFunction() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		return DriverManager.getConnection("jdbc:mysql://localhost:3306/school", "root", "LQ3217103676");
	}
	/**
	 * 关闭jdbc的资源
	 * */
	public static void closeResources(Connection con, ResultSet rs, PreparedStatement ps) throws SQLException {
		if (con != null) {
			con.close();
		}
		if (rs != null) {
			rs.close();
		}
		if (ps != null) {
			ps.close();
		}
	}
	/**
	 * 向人事管理系统中单个添加数据，封装成方法
	 */
	public static void addSoloInformationsToSystem(Person p,DefaultTableModel dtm) {
		String[] st=new String[6];
		st[0]=String.valueOf(p.getId());
		st[1]=p.getName();
		if(p.getSex()==0) {
			st[2]="女";
		}else {
			st[2]="男";
		}
		st[3]=String.valueOf(p.getAge());
		st[4]=p.getDepartment();
		st[5]=String.valueOf(p.getSalary());
		dtm.addRow(st);
	}
	/**
	 * 从人事系统的增加窗口中获得窗口的内容，并将将信息传入人事系统表格和数据库中。
	 */
	public static Person getInformationsAddToSystemAndDatabases(AddOrModifyOrSearchView siv,DefaultTableModel dtm) {
		Person p=null;
		ImplementFunctions imm = new ImplementFunctions();
		ArrayList idsList=imm.getIdsFromDatabases();
		String reg="^[1-9]{1,9}$";
		if(siv.getNumberText1().getText().trim().equals("")
				||siv.getNameText2().getText().trim().equals("")
				||((String) siv.getSexComBox().getSelectedItem()).trim().equals("")
				||((String) siv.getDepartComBox2().getSelectedItem()).trim().equals("")
				||siv.getAgeText2().getText().trim().equals("")
				||siv.getSalaryText5().getText().trim().equals("")) {
			JOptionPane.showMessageDialog(null, "添加的内容不能为空");
			
		}else if(siv.getNumberText1().getText().matches(reg)
				&&siv.getAgeText2().getText().matches(reg)
				&&siv.getSalaryText5().getText().matches(reg)) {
			int result=0;
			for(int i=0;i<idsList.size();i++) {
				if(Integer.parseInt(siv.getNumberText1().getText())==(int)idsList.get(i)) {
					result=1;
					JOptionPane.showMessageDialog(null, "编号重复，请重新输入");
				}
			}
			if(result==0) {
				int id = Integer.parseInt(siv.getNumberText1().getText());
				String name = siv.getNameText2().getText();
				int sex = 0;
				String sexs = (String) siv.getSexComBox().getSelectedItem();
				if ("男".equals(sexs)) {
					sex = 1;
				}
				String department = (String) siv.getDepartComBox2().getSelectedItem();
				int age = Integer.parseInt(siv.getAgeText2().getText());
				int salary = Integer.parseInt(siv.getSalaryText5().getText());
				 p = new Person(id, name, sex, age, department, salary);	
			}
		}else {
			JOptionPane.showMessageDialog(null, "编号、年龄和工资必须为整数且不能为0");
		}
		return p;
		
	}
	/**
	 * 从人事系统中以xml的形式导出数据：
	 * @throws IOException 
	 */
	public static void exportDatasByXML(ArrayList<Person> alp,File exportFile) throws IOException {
		FileWriter fw=null;
		XMLWriter xw=null;
		try {
			Document doc=DocumentHelper.createDocument();
			Element school=doc.addElement("school");
			doc.setRootElement(school);
			for(int i=0;i<alp.size();i++) {
				Element person=school.addElement("Person");
				Element id=person.addElement("id");
				Element name=person.addElement("name");
				Element sex=person.addElement("sex");
				Element age=person.addElement("age");
				Element department=person.addElement("department");
				Element salary=person.addElement("salary");
				id.setText(String.valueOf(alp.get(i).getId()));
				name.setText(alp.get(i).getName());
				String sexs="女";
				if(alp.get(i).getSex()==1) {
					sexs="男";
				}
				sex.setText(sexs);
				age.setText(String.valueOf(alp.get(i).getAge()));
				department.setText(alp.get(i).getDepartment());
				salary.setText(String.valueOf(alp.get(i).getSalary()));
				fw=new FileWriter(exportFile);
				OutputFormat opf=OutputFormat.createPrettyPrint();
				opf.setEncoding("GBK");
				xw=new XMLWriter(fw,opf);
				xw.write(doc);
			}
		}finally {
			fw.close();
			xw.close();
		}
	}
	/**
	 * 将xml中的数据导入人事系统中：
	 * 
	 */
	public static ArrayList<Person> importDatasToSystem(File importFile){
		String reg="^[1-9]{1,9}$";
		String reg2="^\\w+$";
		ArrayList<Person> importList=new ArrayList<Person>();
		SAXReader sr=new SAXReader();
		Document doc=null;
		try {
			doc = sr.read(importFile);
			Element shool=doc.getRootElement();
			if(shool.getName().equals("school")) {
				List<Element> l=shool.elements();
				for(Element Persons:l) {
					if(Persons.getName().equals("Person")){
						List<Element>list=Persons.elements();
						if(list.get(0).getText().matches(reg)
								&&list.get(3).getText().matches(reg)
								&&list.get(5).getText().matches(reg)
								&&list.get(1).getText().matches(reg2)
								&&list.get(4).getText().matches(reg2)
								&&("男".equals(list.get(2).getText())||"女".equals(list.get(2).getText()))) {
							int id=Integer.parseInt(list.get(0).getText());
							String name=list.get(1).getText();
							int sex=0;
							if("男".equals(list.get(2).getText())) {
									sex=1;
							}
							int age=Integer.parseInt(list.get(3).getText());
							String department=list.get(4).getText();
							int salary=Integer.parseInt(list.get(5).getText());
							Person p=new Person(id,name,sex,age,department,salary);
							importList.add(p);
						}else {
							JOptionPane.showMessageDialog(null, "出现一条错误的数据，该条数据将不会被导入");
						}	
					}else {
						JOptionPane.showMessageDialog(null, "数据格式不匹配，该数据将不会被导入");
					}
				}
			}else {
				JOptionPane.showMessageDialog(null, "数据格式不匹配，该数据将不会被导入");
			}	
	} catch (DocumentException e) {
		JOptionPane.showMessageDialog(null, "导入的xml文件格式错误","x",JOptionPane.ERROR_MESSAGE);
	}
	return importList;
	}	
}
