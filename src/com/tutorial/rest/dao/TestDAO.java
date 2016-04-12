package com.tutorial.rest.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import javax.naming.*;
import javax.sql.*;

public class TestDAO {
	private static Connection conn=null;
	private static Context context = null;

	public static Connection getConn() throws Exception{
		if (conn == null){
			try	{
				Class.forName("oracle.jdbc.driver.OracleDriver");

				String URL = "jdbc:oracle:thin:@tushar-Satellite-C55-C:1521:XE";
				String USER = "test";
				String PASS = "test";
				conn = DriverManager.getConnection(URL, USER, PASS);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		/*System.out.println("Creating statement...");
		Statement stmt = conn.createStatement();
		String sql;
		sql = "SELECT id, name FROM emp";*/

		return conn;
	}
}
