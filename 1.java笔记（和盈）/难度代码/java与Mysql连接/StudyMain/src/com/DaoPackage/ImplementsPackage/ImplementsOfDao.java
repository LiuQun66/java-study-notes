package com.DaoPackage.ImplementsPackage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.DaoPackage.Dao;
import com.ModelPackage.Student;
import com.UtilsPackage.Utils;

public class ImplementsOfDao  implements Dao{
	@Override
	public void insertFunction(Student st) {
		Connection con=null;
		PreparedStatement ps=null;
		try {
			con=Utils.getConnectionFunction();
			System.out.println("连接成功");
			String sql="insert into student values(?,?,?,?)";
			ps=con.prepareStatement(sql);//注意这里和Statement的区别.
			ps.setInt(1,st.getId() );//values()括号中问号分别对应着id,name,sex,weight.
			ps.setString(2, st.getName());//这个顺序不能乱.
			ps.setString(3, st.getSex());
			ps.setString(4, st.getWeight());
			int row=ps.executeUpdate();//这里若写成ps.executeUpdate(sql)则最后会报错.
			if(row>0) {
				System.out.println("插入成功");
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}finally {
			try {//注意这里下面要打印输出“资源已关闭”，方法查看是否关闭了资源.
				Utils.closeResources(con, null, ps);
				System.out.println("资源已关闭");
			} catch (SQLException e) {
				e.printStackTrace();
			}}}

	@Override
	public List findAllFunction(String ssex) {
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		List<Student> l=new ArrayList<Student>();
		try {
			con=Utils.getConnectionFunction();
			System.out.println("连接成功");
			String sql="select * from student where sex=?";
			ps=con.prepareStatement(sql);
			ps.setString(1,ssex);//这里括号中填的不是sex所对应的第三个位置.
			rs=ps.executeQuery();
			while(rs.next()){
				int id=rs.getInt("id");
				String name=rs.getString("name");
				String sex=rs.getString("sex");
				String weight=rs.getString("weight");
				Student s=new Student(id,name,sex,weight);
				l.add(s);
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}finally {
			try {//注意这里下面要打印输出“资源已关闭”，方法查看是否关闭了资源.
				Utils.closeResources(con, rs, ps);
				System.out.println("资源已关闭");
			} catch (SQLException e) {
				e.printStackTrace();
			}}
		return l;
	}}
