package com.dream.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.junit.Test;

import com.dream.utils.DBUtils;

public class JDBCTest {
	@Test
	public void tset(){
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			conn = DBUtils.getConnection();
			// �������񼶱�,mysqlĬ����:TRANSACTION_REPEATABLE_READ
			conn.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);
			conn.setAutoCommit(false);
			ps = conn.prepareStatement("update account set money=money-100 where name='aaa'");
			ps.executeUpdate();
			//int i = 10/0;
			ps = conn.prepareStatement("update account set money=money+100 where name='bbb'");
			ps.executeUpdate();
			conn.commit();
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}finally{
			// �ر���Դ
			DBUtils.closeAll(null, ps, conn);
		}
	}
}
