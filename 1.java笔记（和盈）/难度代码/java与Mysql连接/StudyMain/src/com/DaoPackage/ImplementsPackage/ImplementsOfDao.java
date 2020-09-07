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
			System.out.println("���ӳɹ�");
			String sql="insert into student values(?,?,?,?)";
			ps=con.prepareStatement(sql);//ע�������Statement������.
			ps.setInt(1,st.getId() );//values()�������ʺŷֱ��Ӧ��id,name,sex,weight.
			ps.setString(2, st.getName());//���˳������.
			ps.setString(3, st.getSex());
			ps.setString(4, st.getWeight());
			int row=ps.executeUpdate();//������д��ps.executeUpdate(sql)�����ᱨ��.
			if(row>0) {
				System.out.println("����ɹ�");
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}finally {
			try {//ע����������Ҫ��ӡ�������Դ�ѹرա��������鿴�Ƿ�ر�����Դ.
				Utils.closeResources(con, null, ps);
				System.out.println("��Դ�ѹر�");
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
			System.out.println("���ӳɹ�");
			String sql="select * from student where sex=?";
			ps=con.prepareStatement(sql);
			ps.setString(1,ssex);//������������Ĳ���sex����Ӧ�ĵ�����λ��.
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
			try {//ע����������Ҫ��ӡ�������Դ�ѹرա��������鿴�Ƿ�ر�����Դ.
				Utils.closeResources(con, rs, ps);
				System.out.println("��Դ�ѹر�");
			} catch (SQLException e) {
				e.printStackTrace();
			}}
		return l;
	}}
