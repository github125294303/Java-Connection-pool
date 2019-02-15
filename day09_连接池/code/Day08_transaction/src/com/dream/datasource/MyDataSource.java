package com.dream.datasource;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;
import java.util.LinkedList;

import javax.sql.DataSource;

import com.dream.utils.DBUtils;

public class MyDataSource implements DataSource {
	
	// ��������ϵ�����������addLast������removeFirst����
	private static LinkedList<Connection> pool = (LinkedList<Connection>) Collections.synchronizedList(new LinkedList<Connection>()) ;
	// ��ʼ��10������
	static {
		for (int i = 1; i <= 10; i++) {
			try {
				// �����ӷ������ӳ�
				Connection conn = DBUtils.getConnection();
				pool.add(conn);
			} catch (Exception e) {
				throw new ExceptionInInitializerError("��ʼ������ʧ��,���������ļ��Ƿ���ȷ");
			}
		}
	}

	@Override
	public Connection getConnection() throws SQLException {
		Connection conn = null;
		if (pool.size() > 0) {
			conn = pool.removeFirst();// �Ƴ����ҷ��ص�һ��
		} else {
			// 1���ȴ�
			// 2������һ���µ�����,�����˿���ֱ�ӹر�
			throw new RuntimeException("������(���)��æ,���Ժ�����");
		}
		return conn;
	}

	// �ͷ���Դ--�����ӷŻص����ӳض����ǹر�
	public static void release(Connection conn) {
		pool.addLast(conn);
	}

	@Override
	public PrintWriter getLogWriter() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setLogWriter(PrintWriter out) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void setLoginTimeout(int seconds) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public int getLoginTimeout() throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public <T> T unwrap(Class<T> iface) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isWrapperFor(Class<?> iface) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Connection getConnection(String username, String password)
			throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
}
