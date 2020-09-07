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
	 * 输入用户名和密码查询数据库：
	 */
	@Override
	public UserAdmin searchUserAdmin(String userName,String userPassword) {
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		UserAdmin user=null;
		try {
			con=Utils.getConnectionFunction();
			System.out.println("数据库连接成功");
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
				System.out.println("资源已关闭");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return user;	
	}
	/**
	 * 获得数据库中导入人事系统的个人信息
	 */

	@Override
	public ArrayList<Person> getPersonInformations() {
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		ArrayList<Person> aList=new ArrayList<Person>();
		try {
			con=Utils.getConnectionFunction();
			System.out.println("数据库连接成功");
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
				System.out.println("资源已关闭");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return aList;
		
	}
	/**
	 * 将获得的个人信息导入人事系统中
	 */
	public void importInformationsToSystem(JTable table2) {
		DefaultTableModel dtm=(DefaultTableModel)table2.getModel();
		ArrayList<Person> aList = getPersonInformations();
		for(Person p:aList) {
			Utils.addSoloInformationsToSystem(p, dtm);
		}
	}
	/**
	 * 将人事系统中增加的信息导入数据库中
	 */
	@Override
	public void addPersonInformationsToDatabases(Person p) {
		Connection con=null;
		PreparedStatement ps=null;
		try {
			con=Utils.getConnectionFunction();
			System.out.println("数据库连接成功");
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
				System.out.println("插入成功");
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				Utils.closeResources(con, null, ps);
				System.out.println("资源已关闭");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	/**
	 * (选中多个进行删除)人事系统中数据删除之后，将数据库中的数据进行删除
	 */
	@Override
	public void deleteManySystemAndDatabasesInformations(int[] deleteIds) {
		Connection con=null;
		PreparedStatement ps=null;
		try {
			con=Utils.getConnectionFunction();
			con.setAutoCommit(false);//取消自动提交
			System.out.println("数据库连接成功");
			String sql="delete from person where id=? ";
			ps=con.prepareStatement(sql);
			for(int i=0;i<deleteIds.length;i++) {
				ps.setInt(1, deleteIds[i]);
				ps.addBatch();//批处理
			}
			ps.executeBatch();//执行批处理
			con.commit();//手动提交
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			try {
				con.rollback();//若操作失败则回滚
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}finally {
			try {
				Utils.closeResources(con, null, ps);
				System.out.println("资源已关闭");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
	/**
	 * 查询人事管理系统中的数据：
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
			System.out.println("数据库连接成功");
			StringBuilder sql=new StringBuilder("select * from person where 1=1 ");//要修改：
			st=con.createStatement();
			if(al.get(0)!=null&&!((String)al.get(0)).trim().equals("")) {
				if(((String)al.get(0)).matches(reg)) {
					sql.append(" and id="+Integer.parseInt((String)al.get(0)));
				}else {
					JOptionPane.showMessageDialog(null, "编号必须为整数且不能为0");
				}
			}
			if(al.get(1)!=null&&!((String)al.get(1)).trim().equals("")) {
				sql.append(" and name like '%"+(String)al.get(1)+"%'");
			}
			if(al.get(2)!=null&&!((String)al.get(2)).trim().equals("")) {
				int sex=0;
				if("男".equals((String)al.get(2))) {
					sex=1;
				}
				sql.append(" and sex="+sex);
			}
			if(al.get(3)!=null&&!((String)al.get(3)).trim().equals("")) {
				if(((String)al.get(3)).matches(reg)) {
					sql.append(" and age="+Integer.parseInt((String)al.get(3)));
				}else {
					JOptionPane.showMessageDialog(null, "年龄必须为整数且不能为0");
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
					JOptionPane.showMessageDialog(null, "工资必须为整数且不能为0");
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
				System.out.println("资源已关闭");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return aList;
		
	}
	/**
	 * 查询数据库中所有的Id：
	 */
	@Override
	public ArrayList getIdsFromDatabases() {
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		ArrayList idList=new ArrayList();
		try {
			con=Utils.getConnectionFunction();
			System.out.println("数据库连接成功");
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
				System.out.println("资源已关闭");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return idList;
	}
	
	
	
	
	

}
