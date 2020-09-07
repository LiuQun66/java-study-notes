package com.UtilsPackage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Utils {
	//获得连接：
	public static Connection getConnectionFunction() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		return DriverManager.getConnection("jdbc:mysql://localhost:3306/school","root","LQ3217103676");
	}
	//关闭资源：
	public static void closeResources(Connection con,ResultSet rs,PreparedStatement ps) throws SQLException {
		if(con!=null) {
			con.close();
		}
		if(rs!=null) {
			rs.close();
		}
		if(ps!=null) {
			ps.close();
		}}}
