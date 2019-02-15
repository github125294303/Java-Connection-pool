package com.dream.datasource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JDBCTest {
	
	public static void main(String[] args) {
		Connection conn = null;
		PreparedStatement ps = null;
		MyDataSource ds = new MyDataSource();
		try {
			conn = ds.getConnection();//从连接池里取出的连接
			conn.prepareStatement("....");
			
			// ...
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
