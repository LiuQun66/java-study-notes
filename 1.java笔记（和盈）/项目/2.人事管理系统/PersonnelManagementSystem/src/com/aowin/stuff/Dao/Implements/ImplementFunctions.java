package com.aowin.stuff.Dao.Implements;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.aowin.stuff.Dao.Dao;
import com.aowin.stuff.Model.Person;
import com.aowin.stuff.Model.UserAdmin;
import com.aowin.stuff.Utils.Utils;

public class ImplementFunctions implements Dao{
	/**
	 * �����û����������ѯ���ݿ⣺
	 */
	@Override
	public UserAdmin searchUserAdmin(String userName,String userPassword) {
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		UserAdmin user=null;
		try {
			con=Utils.getConnectionFunction();
			System.out.println("���ݿ����ӳɹ�");
			String sql="select * from useradmin where name=? and password=?";
			ps=con.prepareStatement(sql);
			ps.setString(1, userName);
			ps.setString(2, userPassword);
			rs=ps.executeQuery();
			if(rs.next()) {
				String name=rs.getString("name");
				String password=rs.getString("password");
				user=new UserAdmin(name,password);
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				Utils.closeResources(con, rs, ps);
				System.out.println("��Դ�ѹر�");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return user;	
	}
	/**
	 * ������ݿ��е�������ϵͳ�ĸ�����Ϣ
	 */

	@Override
	public ArrayList<Person> getPersonInformations() {
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		ArrayList<Person> aList=new ArrayList<Person>();
		try {
			con=Utils.getConnectionFunction();
			System.out.println("���ݿ����ӳɹ�");
			String sql="select * from person";
			ps=con.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()) {
				int id=rs.getInt("id");
				String name=rs.getString("name");
				int sex=rs.getInt("sex");
				int age=rs.getInt("age");
				String department=rs.getString("department");
				int salary=rs.getInt("salary");
				Person p=new Person(id,name,sex,age,department,salary);
				aList.add(p);
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				Utils.closeResources(con, rs, ps);
				System.out.println("��Դ�ѹر�");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return aList;
		
	}
	/**
	 * ����õĸ�����Ϣ��������ϵͳ��
	 */
	public void importInformationsToSystem(JTable table2) {
		DefaultTableModel dtm=(DefaultTableModel)table2.getModel();
		ArrayList<Person> aList = getPersonInformations();
		for(Person p:aList) {
			Utils.addSoloInformationsToSystem(p, dtm);
		}
	}
	/**
	 * ������ϵͳ�����ӵ���Ϣ�������ݿ���
	 */
	@Override
	public void addPersonInformationsToDatabases(Person p) {
		Connection con=null;
		PreparedStatement ps=null;
		try {
			con=Utils.getConnectionFunction();
			System.out.println("���ݿ����ӳɹ�");
			String sql="insert into person values(?,?,?,?,?,?)";
			ps=con.prepareStatement(sql);
			ps.setInt(1, p.getId());
			ps.setString(2, p.getName());
			ps.setInt(3, p.getSex());
			ps.setInt(4, p.getAge());
			ps.setString(5, p.getDepartment());
			ps.setInt(6, p.getSalary());
			int row=ps.executeUpdate();
			if(row>0) {
				System.out.println("����ɹ�");
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				Utils.closeResources(con, null, ps);
				System.out.println("��Դ�ѹر�");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	/**
	 * (ѡ�ж������ɾ��)����ϵͳ������ɾ��֮�󣬽����ݿ��е����ݽ���ɾ��
	 */
	@Override
	public void deleteManySystemAndDatabasesInformations(int[] deleteIds) {
		Connection con=null;
		PreparedStatement ps=null;
		try {
			con=Utils.getConnectionFunction();
			con.setAutoCommit(false);//ȡ���Զ��ύ
			System.out.println("���ݿ����ӳɹ�");
			String sql="delete from person where id=? ";
			ps=con.prepareStatement(sql);
			for(int i=0;i<deleteIds.length;i++) {
				ps.setInt(1, deleteIds[i]);
				ps.addBatch();//������
			}
			ps.executeBatch();//ִ��������
			con.commit();//�ֶ��ύ
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			try {
				con.rollback();//������ʧ����ع�
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}finally {
			try {
				Utils.closeResources(con, null, ps);
				System.out.println("��Դ�ѹر�");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
	/**
	 * ��ѯ���¹���ϵͳ�е����ݣ�
	 */
	@Override
	public ArrayList<Person> searchInformationsFromPersonnelSystem(ArrayList al) {
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		String reg="^[1-9]{1,9}$";
		ArrayList<Person> aList=new ArrayList<Person>();
		try {
			con=Utils.getConnectionFunction();
			System.out.println("���ݿ����ӳɹ�");
			StringBuilder sql=new StringBuilder("select * from person where 1=1 ");//Ҫ�޸ģ�
			st=con.createStatement();
			if(al.get(0)!=null&&!((String)al.get(0)).trim().equals("")) {
				if(((String)al.get(0)).matches(reg)) {
					sql.append(" and id="+Integer.parseInt((String)al.get(0)));
				}else {
					JOptionPane.showMessageDialog(null, "��ű���Ϊ�����Ҳ���Ϊ0");
				}
			}
			if(al.get(1)!=null&&!((String)al.get(1)).trim().equals("")) {
				sql.append(" and name like '%"+(String)al.get(1)+"%'");
			}
			if(al.get(2)!=null&&!((String)al.get(2)).trim().equals("")) {
				int sex=0;
				if("��".equals((String)al.get(2))) {
					sex=1;
				}
				sql.append(" and sex="+sex);
			}
			if(al.get(3)!=null&&!((String)al.get(3)).trim().equals("")) {
				if(((String)al.get(3)).matches(reg)) {
					sql.append(" and age="+Integer.parseInt((String)al.get(3)));
				}else {
					JOptionPane.showMessageDialog(null, "�������Ϊ�����Ҳ���Ϊ0");
				}
				
			}
			if(al.get(4)!=null&&!((String)al.get(4)).trim().equals("")) {
				sql.append(" and department='"+(String)al.get(4)+"'");
				System.out.println(al.get(4));
			}
			if(al.get(5)!=null&&!((String)al.get(5)).trim().equals("")) {
				if(((String)al.get(5)).matches(reg)) {
					sql.append(" and salary="+Integer.parseInt((String)al.get(5)));
				}else {
					JOptionPane.showMessageDialog(null, "���ʱ���Ϊ�����Ҳ���Ϊ0");
				}	
			}
			rs=st.executeQuery(sql.toString());
			while(rs.next()) {
				int id=rs.getInt("id");
				String name=rs.getString("name");
				int sex=rs.getInt("sex");
				int age=rs.getInt("age");
				String department=rs.getString("department");
				int salary=rs.getInt("salary");
				Person p=new Person(id,name,sex,age,department,salary);
				aList.add(p);

			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				Utils.closeResources(con, rs, null);
				st.close();
				System.out.println("��Դ�ѹر�");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return aList;
		
	}
	/**
	 * ��ѯ���ݿ������е�Id��
	 */
	@Override
	public ArrayList getIdsFromDatabases() {
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		ArrayList idList=new ArrayList();
		try {
			con=Utils.getConnectionFunction();
			System.out.println("���ݿ����ӳɹ�");
			String sql="select * from person";
			ps=con.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()) {
				int id=rs.getInt("id");
				idList.add(id);
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				Utils.closeResources(con, rs, ps);
				System.out.println("��Դ�ѹر�");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return idList;
	}
	
	
	
	
	

}
